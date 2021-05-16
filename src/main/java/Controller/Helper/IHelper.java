/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Helper;

import Model.Cliente;

/**
 * Interface que obriga seus filhos a implementar
 * ObterModelo() e limparTela()
 * @author Adauto
 */
public interface IHelper {
    
    /**
     * Metodo abstrato para herança
     * @return 
     */
    public abstract Object obterModelo();
    
    /**
     * Metodo abstrato para herança
     */
    public abstract void limparTela();
    

}
