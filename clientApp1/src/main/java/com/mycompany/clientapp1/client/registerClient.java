/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientapp1.client;

import com.mycompany.clientapp1.entity.User;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author LENOVO
 */
@RegisterRestClient(baseUri = "http://localhost:9090/servicesApp/rest/test")
public interface registerClient {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(User u);
}
