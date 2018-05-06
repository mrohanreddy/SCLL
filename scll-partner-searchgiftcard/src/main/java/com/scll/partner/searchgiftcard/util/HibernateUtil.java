package com.scll.partner.searchgiftcard.util;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.scll.partner.searchgiftcard.model.Customer;
import com.scll.partner.searchgiftcard.model.CustomerRedemption;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (null != sessionFactory)
            return sessionFactory;
        
        Configuration configuration = new Configuration();

          
        configuration.configure("resources/hibernate.cfg.xml");
        configuration.addAnnotatedClass(CustomerRedemption.class);
        configuration.addAnnotatedClass(Customer.class);
        
      
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
