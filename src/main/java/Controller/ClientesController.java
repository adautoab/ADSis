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
import Model.Clientes;
import javax.persistence.EntityManager;

/**
 *
 * @author Adauto
 */
public class ClientesController {

    private final ClientesHelper helper;

    public ClientesController(FrmClientes view) {
        this.helper = new ClientesHelper(view);
    }

    public Clientes Salvar() {
        Clientes cliente_a_salvar = this.helper.obterModelo();

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        Clientes cliente_salvo = new ClientesDAO(em).insert(cliente_a_salvar);
        em.getTransaction().commit();
        em.close();
        return cliente_salvo;

    }

}
