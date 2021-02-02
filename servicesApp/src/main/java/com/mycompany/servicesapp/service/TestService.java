package com.mycompany.servicesapp.service;

import com.mycompany.servicesapp.entity.Category;
import com.mycompany.servicesapp.entity.Product;
import com.mycompany.servicesapp.entity.User;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestService {

    EntityManager em=Persistence.createEntityManagerFactory("test1PU").createEntityManager();
    
    @GET
    public Response get() {
        return Response.ok("Hello, world!").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(User u)
    {
        em.getTransaction().begin();
        User u1=new User(null, u.getUsername(),u.getPassword(), u.getEmail(), u.getContactno(),u.getRole());
        em.persist(u1);
        em.getTransaction().commit();
    }
    
    @GET
    @Path("/getAllCategory")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Category> getAllCategory()
    {
        return em.createNamedQuery("Category.findAll").getResultList();
    }
    
    @POST
    @RolesAllowed("Admin")
    @Path("addCategory")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addCategory(Category c)
    {
        em.getTransaction().begin();
        Category c1=new Category(null,c.getCategoryname());
        em.persist(c1);
        em.getTransaction().commit();
    }
    
    @PUT
    @RolesAllowed("Admin")
    @Path("editCategory")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editCategory(Category c)
    {
        System.out.println(c.getCategoryid()+" "+c.getCategoryname());
        em.getTransaction().begin();
        Category c1=em.find(Category.class, c.getCategoryid());
        c1.setCategoryname(c.getCategoryname());
        em.merge(c1);
        em.getTransaction().commit();
    }
    
    @DELETE
    @RolesAllowed("Admin")
    @Path("deleteCategory/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteCategory(@PathParam("id") int id)
    {
        em.getTransaction().begin();
        em.remove(em.find(Category.class, id));
        em.getTransaction().commit();
    }
    
    @GET
    @Path("/getAllProduct")
    @Produces(MediaType.APPLICATION_XML)
    public Collection<Product> getAllProduct()
    {
        return (Collection<Product>) em.createNamedQuery("Product.findAll").getResultList();
    }
    
    @POST
    @RolesAllowed("Admin")
    @Path("addProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct(Product p)
    {
        em.getTransaction().begin();
        Product p1=new Product(null, p.getPname(), p.getPrice(),p.getDescription());
        Category c= em.find(Category.class, p.getCategoryid().getCategoryid());
        p1.setCategoryid(c);
        em.merge(c);
        em.persist(p1);
        em.getTransaction().commit();
    }
    
    @PUT
    @RolesAllowed("Admin")
    @Path("editProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editProduct(Product p)
    {
        try{
            em.getTransaction().begin();
            Product p1=em.find(Product.class, p.getProductid());
            Category c= em.find(Category.class, p.getCategoryid().getCategoryid());
            p1.setCategoryid(c);
            p1.setPname(p.getPname());
            p1.setPrice(p.getPrice());
            p1.setDescription(p.getDescription());
            p1.setCategoryid(c);
            em.merge(c);
            em.merge(p1);
            em.getTransaction().commit();
        }
        catch(Exception e){e.printStackTrace();}
        
    }
    
    @DELETE
    @RolesAllowed("Admin")
    @Path("deleteProduct/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProduct(@PathParam("id") int id)
    {
        em.getTransaction().begin();
        em.remove(em.find(Product.class, id));
        em.getTransaction().commit();
    }
}
