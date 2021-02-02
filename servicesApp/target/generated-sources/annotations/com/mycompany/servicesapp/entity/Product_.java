package com.mycompany.servicesapp.entity;

import com.mycompany.servicesapp.entity.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-01-01T22:38:07")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> productid;
    public static volatile SingularAttribute<Product, String> pname;
    public static volatile SingularAttribute<Product, Integer> price;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Category> categoryid;

}