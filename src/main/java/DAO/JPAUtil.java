/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Adauto
 */
public class JPAUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");


    /**
     * Retorna um Entity Manager de Conexao com o banco de dados
     * @return
     */
    public EntityManager getEntityManager() {
            return emf.createEntityManager();
    }
}
