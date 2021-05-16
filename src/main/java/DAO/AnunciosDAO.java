/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Anuncio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author User
 */
public class AnunciosDAO {
    
    private final EntityManager em;

    public AnunciosDAO(EntityManager em) {
        this.em = em;
    }
    
    

    public Anuncio insert(Anuncio anuncio_a_salvar) {
        // implementar metodo salvar cliente no banco de dados

        em.persist(anuncio_a_salvar);
        return anuncio_a_salvar;

    }
    
    public Anuncio update(Anuncio anuncio) {
        em.merge(anuncio);
        return anuncio;
    }

    public Anuncio insertOrUpdate(Anuncio anuncio) {
        if (anuncio.getId() > 0) {
            return this.update(anuncio);
        }
        return insert(anuncio);
    }

    public void delete(Anuncio anuncio) {
        em.merge(anuncio);
        em.remove(anuncio);
    }

    public Anuncio selectPorId(int id) {
        return em.find(Anuncio.class, id);
    }

    /**
     *
     * @return
     */
    public List<Anuncio> selectAll() {
        String jpql = "select u from Anuncio as u";
        Query query = em.createQuery(jpql);
        return retornaListaComBaseNaConsulta(query);
    }


    private ArrayList<Anuncio> retornaListaComBaseNaConsulta(Query query) {
        ArrayList<Anuncio> anuncios;
        try {
            anuncios = (ArrayList) query.getResultList();
        } catch (NoResultException e) {
            anuncios = new ArrayList<>();
        }

        return anuncios;
    }
    
    public ArrayList<Anuncio> ListaDePesquisa(String pesquisar){

        String jpql = "from Anuncio a where lower(a.nome) like lower('" + pesquisar + "%') order by a.nome";
        Query query = em.createQuery(jpql);
        return retornaListaComBaseNaConsulta(query);

    }
    
    public void cancel(){
    
    }
    

}
