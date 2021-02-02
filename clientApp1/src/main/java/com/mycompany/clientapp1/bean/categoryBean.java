/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientapp1.bean;

import com.mycompany.clientapp1.client.categoryClient;
import com.mycompany.clientapp1.entity.Category;
import java.io.Serializable;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import token.MyCredentials;
/**
 *
 * @author LENOVO
 */
@Named(value = "categoryBean")
@SessionScoped
public class categoryBean implements Serializable{

    @Inject @RestClient categoryClient cc; 
    Collection<Category> categorys;
    Category c=new Category();
    String message="";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Category getC() {
        return c;
    }

    public void setC(Category c) {
        this.c = c;
    }

    public Collection<Category> getCategorys() {
        categorys = cc.getAllCategory();
        return categorys;
    }

    public void setCategorys(Collection<Category> categorys) {
        this.categorys = categorys;
    }
 
    /**
     * Creates a new instance of categoryBean
     */
    public categoryBean() {
        message=null;
    }


    public void addCategory()
    {
        try {
            cc.addCategory(c);
            message= "Category Added..";
            //return "/admin/category.jsf?faces-redirect=true;";
        }catch(Exception e) {
            message = MyCredentials.getSubject()+" You're not authorized ";
            //return "/admin/category.jsf?faces-redirect=true;&msg=";
	}        
    }
    
    public String updateCategory(Category category)
    {
        try {
            System.out.println("id "+ category.getCategoryid() + " " + category.getCategoryname());
            if(MyCredentials.getSubject() == null) throw new Exception();
            c.setCategoryid(category.getCategoryid());
            c.setCategoryname(category.getCategoryname());
            message= "Now Here You Can Update Category..";
            return "/admin/category.jsf";
        }catch(Exception e) {
            message = MyCredentials.getSubject()+" You're not authorized ";
            return "/admin/category.jsf";
	}
    }
    
    public String editCategory()
    {
        System.out.println("dd");
        System.out.println(c.getCategoryid() + " " + c.getCategoryname());
        try {
            if(MyCredentials.getSubject() == null) throw new Exception();
            cc.editCategory(c);
            c.setCategoryname("");
            c.setCategoryid(0);
            message= "Category Edit Successfully..";
            return "/admin/category.jsf?faces-redirect=true;";
        }catch(Exception e) {
            message = MyCredentials.getSubject()+" You're not authorized ";
            c.setCategoryname("");
            return "/admin/category.jsf?faces-redirect=true;";
        }
    }
    
    public void deleteCategory(Integer id)
    {
        try {
            cc.deleteCategory(id);
            message= "Category Deleted..";
            //return "/admin/category.jsf?faces-redirect=true;";
        }catch(Exception e) {
            message = MyCredentials.getSubject()+" You're not authorized ";
            //return "/admin/category.jsf?faces-redirect=true;&msg=";
	} 
    }
    
    public String clearMessage()
    {
        message=null;
        return "/admin/category.jsf?faces-redirect=true;";
    }
}
