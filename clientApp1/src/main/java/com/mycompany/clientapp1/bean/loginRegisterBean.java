/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientapp1.bean;

import com.mycompany.clientapp1.client.registerClient;
import com.mycompany.clientapp1.entity.User;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import token.MyCredentials;

/**
 *
 * @author LENOVO
 */
@Named(value = "loginRegisterBean")
@SessionScoped
public class loginRegisterBean implements Serializable {

    @Inject private IdentityStoreHandler identityStoreHandler;
    @Inject private MyCredentials mycredentials;
    
    Pbkdf2PasswordHashImpl pbkd;
    @Inject @RestClient registerClient rc;
    User u=new User();
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if(this.message!= null){ this.message=null; }
        this.message = message;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    private  <T> List<T> convertSetToList(Set<T> set) {
        List<T> list = new ArrayList<>(); 
        for (T t : set) 
            list.add(t);
        return list; 
    }
    
    /**
     * Creates a new instance of loginRegisterBean
     */
    public loginRegisterBean() {
        message="";
    }
    
    public String register()
    {
        pbkd = new Pbkdf2PasswordHashImpl();
        String pwd = pbkd.generate(u.getPassword().toCharArray());
        System.out.println("pwd "+pwd+" name "+u.getUsername());
        u.setPassword(pwd);
        u.setRole("User");
        rc.register(u);
        u=null;
        //message="Register SuccessFully...";
        return "/index.jsf?faces-redirect=true&msg=Register%20SuccessFully...";
    }
    
    public String login()// throws IOException
    {
        FacesContext context= FacesContext.getCurrentInstance();
        HttpServletRequest request= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession h=request.getSession();
        
        Credential credential=new UsernamePasswordCredential(u.getUsername(),new Password(u.getPassword()));
        CredentialValidationResult result = identityStoreHandler.validate(credential);
        System.out.println(result.getStatus());
        if(result.getStatus() == CredentialValidationResult.Status.VALID)
        {
            System.out.println(result.getCallerGroups().toString());
            mycredentials.setSubject(u.getUsername());
            mycredentials.setGroups(convertSetToList(result.getCallerGroups()));
            mycredentials.setLoginStatus("Login_Success");
            mycredentials.setStausMessage("Hello " + result.getCallerGroups().toString()+" Login Success !!");
            
            context.responseComplete();
            if(result.getCallerGroups().contains("Admin"))
            {
                //FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
                return "/admin/category.jsf?faces-redirect=true;";
            }
            else if(result.getCallerGroups().contains("User"))
            {
                //FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
                return "/user/index.jsf?faces-redirect=true;";
            }
            else
                return "/login.jsf?faces-redirect=true;";
        }
        else
            return "/login.jsf?faces-redirect=true&msg=Login%20Fail...";
    }
    
    public String logout() throws ServletException {
        System.out.println("In Log out");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute("logged-group");
        request.getSession().removeAttribute("businessid");
        
        request.logout();
        request.getSession().invalidate();  
        //return "/user/Home.jsf";
        return "/login.jsf?faces-redirect=true";
    }
}
