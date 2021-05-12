/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Helper;

import View.FrmClientes;
import Model.Clientes;

/**
 *
 * @author User
 */
public class ClientesHelper implements IHelper {
    
    private final FrmClientes view;

    public ClientesHelper(FrmClientes view) {
        this.view = view;
    }
    
    
    

    @Override
    public Clientes obterModelo() {
        String nome = view.getjTextFieldNome().getText();
        String email = view.getjTextFieldEmail().getText();
        
        Clientes cliente = new Clientes(0,nome,email);
        return cliente;
    }

    @Override
    public void limparTela() {
        view.getjTextFieldID().setText("");
        view.getjTextFieldNome().setText("");
        view.getjTextFieldEmail().setText("");
    }
    
}
