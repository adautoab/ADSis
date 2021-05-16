/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Helper.RelatoriosHelper;
import DAO.AnunciosDAO;
import DAO.JPAUtil;
import Model.Anuncio;
import View.FrmRelatorios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author User
 */
public class RelatoriosController {
    
    private final RelatoriosHelper helper;

    public RelatoriosController(FrmRelatorios view) {
        this.helper = new RelatoriosHelper(view);
    }

    public void atualizarTabela() {

        /**
         * buscar anuncios no banco de dados
         */

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        AnunciosDAO anunciosDAO = new AnunciosDAO(em);        
        List<Anuncio> anuncios = anunciosDAO.selectAll();        
        
        em.getTransaction().commit();
        em.close();
        
        
        // exibir lista na tabela
        helper.preencherTabela(anuncios);        
    }

    public void calcular() {
        
        Object obj = this.helper.obterIdSelecionado();
        int id = Integer.parseInt(obj.toString());
        System.out.println(obj);
        System.out.println(id);
    }
    
    
    
}
