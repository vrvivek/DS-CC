/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientapp1.bean;

import com.mycompany.clientapp1.client.productClient;
import com.mycompany.clientapp1.entity.Category;
import com.mycompany.clientapp1.entity.Product;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import token.MyCredentials;

/**
 *
 * @author LENOVO
 */
@Named(value = "productBean")
@RequestScoped
public class productBean {

    @Inject @RestClient productClient pc;
    
    Collection<Product> products;
    static Product p=new Product();
    String message;
    String cid;
    

    public Collection<Product> getProducts() {
        products=pc.getAllProduct();
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    
    /**
     * Creates a new instance of ProductBean
     */
    public productBean() {
    }
    
    public void addProduct()
    {
        try {
            System.err.println(cid);
            Category c=new Category();
            c.setCategoryid(Integer.parseInt(cid));
            p.setCategoryid(c);
            pc.addProduct(p);
            p=null;
            message= "Product Added..";
            //return "/admin/category.jsf?faces-redirect=true;";
        }catch(Exception e) {
            message = MyCredentials.getSubject()+" You're not authorized ";
            //return "/admin/category.jsf?faces-redirect=true;&msg=";
	}
    }
    
    public String updateProduct(Product p1)
    {
        try {
            if(MyCredentials.getSubject() == null) throw new Exception();
            System.out.println("Update");
            System.out.println("cid "+p1.getCategoryid().getCategoryid() + " "+p1.getCategoryid().getCategoryname() );
            this.cid= p1.getCategoryid().getCategoryid().toString();
            cid = p1.getCategoryid().getCategoryid().toString();
            this.p=p1;
            message= "Now Here You Can Update Product...";
            return "/admin/editproduct.jsf?faces-redirect=true;";
        }catch(Exception e) {
            message = MyCredentials.getSubject()+" You're not authorized ";
            return "/admin/product.jsf";
	} 
    }
    
    public String editProduct()
    {
        try {
            if(MyCredentials.getSubject() == null) throw new Exception();
            System.out.println("Edit");
            System.out.println("cid "+  " "+p.getCategoryid().getCategoryid() );
            //Category c=new Category();
            //c.setCategoryid(p.getCategoryid().getCategoryid());
            //p.setCategoryid(c);
            pc.editProduct(p);
            p=null;
            message= "Product Updated...";
            return "/admin/product.jsf?faces-redirect=true;";
        }catch(Exception e) {
            message = MyCredentials.getSubject()+" You're not authorized ";
            return "/admin/product.jsf";
	}
    }
    
    public String deleteProduct(Integer id)
    {
        try {
            pc.deleteProduct(id);
            message= "Product Deleted..";
            return "/admin/product.jsf";
        }catch(Exception e) {
            message = MyCredentials.getSubject()+" You're not authorized ";
            return "/admin/product.jsf";
	} 
    }
    
    public String clearMessage()
    {
        message=null;
        return "/admin/category.jsf?faces-redirect=true;";
    }
}
