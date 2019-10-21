package com.iait.clase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
    
    public void run() {
        
        emf = Persistence.createEntityManagerFactory("data-access-hibernate");
        em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.getTransaction().commit();
    }
    
}
