/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client_app1.client;

import com.mycompany.client_app1.entity.CustomerMaster;
import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author LENOVO
 */
@RegisterRestClient(baseUri = "http://localhost:9090/servicesApp1/rest/test")
public interface customerClient {
    @GET
    @Path("getCustomerMaster")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CustomerMaster> getCustomerMaster();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getCustomersWithRating/{condition}/{rating}")
    public Collection<CustomerMaster> getCustomersWithRating(@PathParam("condition")String condition,@PathParam("rating") int rating);
    
}
