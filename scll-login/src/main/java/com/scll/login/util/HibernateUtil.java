package com.scll.login.util;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (null != sessionFactory)
            return sessionFactory;
        
        Configuration configuration = new Configuration();

          
        configuration.configure("/hibernate.cfg.xml");
        configuration.addAnnotatedClass(com.scll.login.model.SignIn.class);
        configuration.addAnnotatedClass(com.scll.login.model.Partner.class);
        configuration.addAnnotatedClass(com.scll.login.model.CustomerRedemption.class);
        
      
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
