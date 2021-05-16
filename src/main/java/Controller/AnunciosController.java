/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Helper.AnunciosHelper;
import DAO.AnunciosDAO;
import DAO.ClientesDAO;
import DAO.JPAUtil;
import Model.Anuncio;
import Model.Cliente;
import View.FrmAnuncios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Adauto
 */
public class AnunciosController {
    
    private final AnunciosHelper helper;

    public AnunciosController(FrmAnuncios view) {
        this.helper = new AnunciosHelper(view);
    }    

    public void Salvar() {
        // obter modelo
        Anuncio anuncio_a_salvar;
        anuncio_a_salvar = this.helper.obterModelo();
        
        // salvar no banco
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        Anuncio anuncio_salvo;
        anuncio_salvo = new AnunciosDAO(em).insert(anuncio_a_salvar);
        em.getTransaction().commit();
        em.close();
        this.helper.preencherCampos(anuncio_salvo);        
        
    }

    public void NovoAnuncio() {
        this.helper.limparTela();
        preencherClientes();
    }

    public void preencherClientes() {
        
        /**
         * buscar clientes no banco de dados
         */

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        //Anuncio anuncio_salvo;
        //anuncio_salvo = new AnunciosDAO(em).insertOrUpdate(anuncio_a_salvar);
        ClientesDAO clientesDAO = new ClientesDAO(em);        
        List<Cliente> clientes = clientesDAO.selectAll();        
        
        em.getTransaction().commit();
        em.close();
        
        /**
         * exibir clientes no combobox clientes
         */
        helper.preencherClientes(clientes);
       
    }

    public void OcultarLista() {
        this.helper.OcultarLista();
    }

    public void PesquisarCliente() {

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        List<Anuncio> lista_anuncios = new AnunciosDAO(em).ListaDePesquisa(this.helper.ObterString());
        em.getTransaction().commit();
        em.close();
        JList lista_string = new JList();
        DefaultListModel lista = new DefaultListModel();
        for (Anuncio a : lista_anuncios){
            lista.addElement(a);
        }
        lista_string.setModel(lista);
        this.helper.preencherListField(lista);
        this.helper.mostrarJList();        
        
    }

    public void PreencherCampos(Anuncio anuncio) {
        System.out.println(anuncio);
        this.helper.preencherCampos(anuncio);
        this.helper.OcultarLista();       
    }
    
}
