/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.FrmClientes;
import Controller.Helper.ClientesHelper;
import DAO.ClientesDAO;
import DAO.JPAUtil;
import Model.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Adauto
 */
public class ClientesController {

    private final ClientesHelper helper;

    public ClientesController(FrmClientes view) {
        this.helper = new ClientesHelper(view);
    }

    public void Salvar() {
        Cliente cliente_a_salvar = this.helper.obterModelo();

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        Cliente cliente_salvo;
        cliente_salvo = new ClientesDAO(em).insertOrUpdate(cliente_a_salvar);
        em.getTransaction().commit();
        em.close();
        this.helper.preencherCampos(cliente_salvo);

    }

    public void PesquisarCliente() {

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        List<Cliente> lista_clientes = new ClientesDAO(em).ListaDePesquisa(this.helper.ObterString());
        em.getTransaction().commit();
        em.close();
        JList lista_string = new JList();
        DefaultListModel lista = new DefaultListModel();
        for (Cliente c : lista_clientes){
            lista.addElement(c);
        }
        lista_string.setModel(lista);
        this.helper.preencherListField(lista);
        this.helper.mostrarJList();

    }

    public void OcultarLista() {
        this.helper.OcultarLista();
    }

    public void PreencherCampos(Cliente cliente) {
        System.out.println(cliente);
        this.helper.preencherCampos(cliente);
        this.helper.OcultarLista();
    }

    public void NovoCliente() {
        this.helper.limparTela();
    }






}
