package br.com.anhanguera.cadastro.usuario.bd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CadastroEMFactory {

    private static EntityManager entityManager;

    public static EntityManager getEntityManager(){
       if(entityManager == null){
           EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
           entityManager = entityManagerFactory.createEntityManager();
       }
       return entityManager;
    }
}
