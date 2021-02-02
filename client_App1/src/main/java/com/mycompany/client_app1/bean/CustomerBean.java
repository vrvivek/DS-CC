/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client_app1.bean;

import com.mycompany.client_app1.client.customerClient;
import com.mycompany.client_app1.entity.CustomerMaster;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author LENOVO
 */
@Named(value = "customerBean")
@RequestScoped
public class CustomerBean {

    @Inject @RestClient customerClient cc;
    Collection<CustomerMaster> customerMasters;
    String condition,rating;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Collection<CustomerMaster> getCustomerMasters() {
        
        return customerMasters;
    }

    public void setCustomerMasters(Collection<CustomerMaster> customerMasters) {
        this.customerMasters = customerMasters;
    }
    /**
     * Creates a new instance of CustomerBean
     */
    public CustomerBean() {
        try{
        customerMasters=cc.getCustomerMaster();
        }catch(Exception e){ e.printStackTrace(); }
    }
    
    public void customerMasterRating()
    {
        customerMasters=null;
        customerMasters=cc.getCustomersWithRating(condition,Integer.parseInt(rating));
        //return "index.xhtml";
    }
}
