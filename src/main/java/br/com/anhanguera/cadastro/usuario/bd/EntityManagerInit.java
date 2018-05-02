package br.com.anhanguera.cadastro.usuario.bd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerInit {

    private static EntityManager entityManager;

    {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getEntityManager(){
        return entityManager;
    }
}
