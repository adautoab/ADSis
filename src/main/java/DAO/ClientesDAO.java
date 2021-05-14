/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Clientes;
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
    
    

    public Clientes insert(Clientes cliente_a_salvar) {
        // implementar metodo salvar cliente no banco de dados

        em.persist(cliente_a_salvar);
        return cliente_a_salvar;

    }
    
    public Clientes update(Clientes cliente) {
        em.merge(cliente);
        em.persist(cliente);
        return cliente;
    }

    public Clientes insertOrUpdate(Clientes cliente) {
        if (cliente.getId() > 0) {
            return this.update(cliente);
        }
        return insert(cliente);
    }

    public void delete(Clientes cliente) {
        em.merge(cliente);
        em.remove(cliente);
    }

    public Clientes selectPorId(Clientes cliente) {
        return em.find(Clientes.class, cliente);
    }

    /**
     *
     * @return
     */
    public List<Clientes> selectAll() {
        String jpql = "select u from Clientes as u";
        Query query = em.createQuery(jpql);
        return retornaListaComBaseNaConsulta(query);
    }

    public boolean existeNoBancoPorUsuarioESenha(Clientes clienteNovo) {
        String jpql = "from Clientes c where c.Nome = :pNome and c.Email = :pEmail";

        Query query = em.createQuery(jpql);
        query.setParameter("pNome", clienteNovo.getNome());
        query.setParameter("pEmail", clienteNovo.getEmail());

        return !retornaListaComBaseNaConsulta(query).isEmpty();

    }

    private ArrayList<Clientes> retornaListaComBaseNaConsulta(Query query) {
        ArrayList<Clientes> clientes;
        try {
            clientes = (ArrayList) query.getResultList();
        } catch (NoResultException e) {
            clientes = new ArrayList<>();
        }

        return clientes;
    }
    
    public ArrayList<Clientes> ListaDePesquisa(String pesquisar){

        String jpql = "from Clientes c where lower(c.nome) like lower('" + pesquisar + "%') order by c.nome";
        Query query = em.createQuery(jpql);
        return retornaListaComBaseNaConsulta(query);

    }
    
    public void cancel(){
    
    }
    

}
