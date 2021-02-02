/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientapp1.client;

import com.mycompany.clientapp1.entity.Category;
import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import token.GenerateToken;

/**
 *
 * @author LENOVO
 */
@RegisterRestClient(baseUri = "http://localhost:9090/servicesApp/rest/test")
public interface categoryClient {
    
    @GET
    @Path("/getAllCategory")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Category> getAllCategory();
    
    @POST
    @Path("addCategory")
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCategory(Category c);
    
    @PUT
    @Path("editCategory")
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editCategory(Category c);
    
    @DELETE
    @Path("deleteCategory/{id}")
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteCategory(@PathParam("id") int id);
    
    default String generateJWTToken() {
        String token ="Bearer "+ GenerateToken.generateJWT();
        System.out.println("Token = "+token);
        return token;
    }
}
