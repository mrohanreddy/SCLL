package com.scll.customer.registration.util;


import java.io.File;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (null != sessionFactory)
            return sessionFactory;
        
        Configuration configuration = new Configuration();
        File file = new File("resources/hibernate.cfg.xml");
        /*File file = new File("src/main/resources/hibernate.cfg.xml");*/
        
        
          
        configuration.configure(file);
        configuration.addAnnotatedClass(com.scll.customer.registration.model.Customer.class);
        configuration.addAnnotatedClass(com.scll.customer.registration.model.SignIn.class);
        
        
      
       // configuration.configure();
       // configuration.addClass(annotations.Users.class)
     
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        try {
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
        return sessionFactory;
    }
}
