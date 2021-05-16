/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.ClientesDAO;
import DAO.JPAUtil;
import Model.Cliente;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author User
 */
public class testePesquisa {
    
    public static void main(String[] args) {
        
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
                    List<Cliente> clientes = new ClientesDAO(em).ListaDePesquisa("Ad");
        em.getTransaction().commit();
        em.close();
        
        System.out.println(clientes);
        //System.out.println(clientes.get(1).getNome());
  

    }
    
}
