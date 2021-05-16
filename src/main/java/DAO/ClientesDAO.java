/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author User
 */
public class ClientesDAO {
    
    private final EntityManager em;

    public ClientesDAO(EntityManager em) {
        this.em = em;
    }
    
    

    public Cliente insert(Cliente cliente_a_salvar) {
        // implementar metodo salvar cliente no banco de dados

        em.persist(cliente_a_salvar);
        return cliente_a_salvar;

    }
    
    public Cliente update(Cliente cliente) {
        em.merge(cliente);
        return cliente;
    }

    public Cliente insertOrUpdate(Cliente cliente) {
        if (cliente.getId() > 0) {
            return this.update(cliente);
        }
        return insert(cliente);
    }

    public void delete(Cliente cliente) {
        em.merge(cliente);
        em.remove(cliente);
    }

    public Cliente selectPorId(Cliente cliente) {
        return em.find(Cliente.class, cliente);
    }

    /**
     *
     * @return
     */
    public List<Cliente> selectAll() {
        String jpql = "select u from Cliente as u";
        Query query = em.createQuery(jpql);
        return retornaListaComBaseNaConsulta(query);
    }

    public boolean existeNoBancoPorUsuarioESenha(Cliente clienteNovo) {
        String jpql = "from Cliente c where c.Nome = :pNome and c.Email = :pEmail";

        Query query = em.createQuery(jpql);
        query.setParameter("pNome", clienteNovo.getNome());
        query.setParameter("pEmail", clienteNovo.getEmail());

        return !retornaListaComBaseNaConsulta(query).isEmpty();

    }

    private ArrayList<Cliente> retornaListaComBaseNaConsulta(Query query) {
        ArrayList<Cliente> clientes;
        try {
            clientes = (ArrayList) query.getResultList();
        } catch (NoResultException e) {
            clientes = new ArrayList<>();
        }

        return clientes;
    }
    
    public ArrayList<Cliente> ListaDePesquisa(String pesquisar){

        String jpql = "from Cliente c where lower(c.nome) like lower('" + pesquisar + "%') order by c.nome";
        Query query = em.createQuery(jpql);
        return retornaListaComBaseNaConsulta(query);

    }
    
    public void cancel(){
    
    }
    

}
