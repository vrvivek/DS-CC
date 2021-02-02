/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientapp1.client;

import com.mycompany.clientapp1.entity.Product;
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
public interface productClient {
    @GET
    @Path("/getAllProduct")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Product> getAllProduct();
    
    @POST
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Path("addProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct(Product p);
    
    @PUT
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Path("editProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editProduct(Product p);
    
    @DELETE
    @ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Path("deleteProduct/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProduct(@PathParam("id") int id);
    
    default String generateJWTToken() {
        String token ="Bearer "+ GenerateToken.generateJWT();
        System.out.println("Token = "+token);
        return token;
    }
}
