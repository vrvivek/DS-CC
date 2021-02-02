/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servicesapp.client;

import com.mycompany.servicesapp.entity.Category;
import com.mycompany.servicesapp.entity.Product;
import com.mycompany.servicesapp.entity.User;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author LENOVO
 */
@RegisterRestClient(baseUri = "http://localhost:9090/servicesApp/rest/test")
public interface testClient {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(User u);
    
    @GET
    @Path("/getAllCategory")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Category> getAllCategory();
    
    @POST
    @Path("addCategory")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCategory(Category c);
    
    @PUT
    @Path("editCategory")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editCategory(Category c);
    
    @DELETE
    @Path("deleteCategory/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteCategory(@PathParam("id") int id);
    
    @GET
    @Path("/getAllProduct")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Product> getAllProduct();
    
    @POST
    @Path("addProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct(Product p);
    
    @PUT
    @Path("editProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editProduct(Product p);
    
    @DELETE
    @Path("deleteProduct/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProduct(@PathParam("id") int id);
}
