package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.entity.CustomerMaster;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class ExampleService {

    EntityManager em=Persistence.createEntityManagerFactory("examPU").createEntityManager();
    
    @GET
    @Path("getCustomerMaster")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CustomerMaster> getCustomerMaster() {     
        return em.createNamedQuery("CustomerMaster.findAll").getResultList();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getCustomersWithRating/{condition}/{rating}")
    public Collection<CustomerMaster> getCustomersWithRating(@PathParam("condition")String condition,@PathParam("rating") int rating){
        Collection<CustomerMaster> customerMasters;
        if("Greater".equals(condition))
        {
            customerMasters=em.createNamedQuery("CustomerMaster.findByRatingGreater").setParameter("rating",rating).getResultList();
        }
        else if("Less".equals(condition))
        {
            customerMasters=em.createNamedQuery("CustomerMaster.findByRatingLess").setParameter("rating",rating).getResultList();
        }
        else if("Greater Equal".equals(condition)){
            customerMasters=em.createNamedQuery("CustomerMaster.findByRatingGreaterThanEqual").setParameter("rating",rating).getResultList();
        }
        else if("Less Equal".equals(condition)){
            customerMasters=em.createNamedQuery("CustomerMaster.findByRatingLessThanEqual").setParameter("rating",rating).getResultList();
        }
        else{
            customerMasters=em.createNamedQuery("CustomerMaster.findByRating").setParameter("rating",rating).getResultList();
        }
        return customerMasters;
    }
    
}
