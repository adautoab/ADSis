/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Helper;

import View.FrmClientes;
import Model.Cliente;
import javax.swing.DefaultListModel;


/**
 *
 * @author User
 */
public class ClientesHelper implements IHelper {
    
    private final FrmClientes view;
    int Enter = 0;

    public ClientesHelper(FrmClientes view) {
        this.view = view;
    }
    
    
    

    @Override
    public Cliente obterModelo() {
        Integer id;
        if (view.getjTextFieldID().getText().equals("")){
            id = 0;
        }
        else
            id = Integer.parseInt(view.getjTextFieldID().getText());
        
        String nome = view.getjTextFieldNome().getText();
        String email = view.getjTextFieldEmail().getText();
        
        Cliente cliente;
        cliente = new Cliente(id,nome,email);
        return cliente;
    }

    @Override
    public void limparTela() {
        view.getjTextFieldPesquisa().setText("");
        view.getjTextFieldID().setText("");
        view.getjTextFieldNome().setText("");
        view.getjTextFieldEmail().setText("");
    }

    public void preencherCampos(Cliente cliente) {
        view.getjTextFieldID().setText(cliente.getId()+"");
        view.getjTextFieldNome().setText(cliente.getNome());
        view.getjTextFieldEmail().setText(cliente.getEmail());
        //view.getjListPesquisa().setModel(new DefaultListModel());
    }

    public String ObterString() {
        return this.view.getjTextFieldPesquisa().getText();
    }

    public void preencherListField(DefaultListModel lista_string) {
        if (Enter == 0)
            this.view.getjListPesquisa().setModel(lista_string);
        else
            Enter = 0;
        
    }

    public void mostrarJList() {
        this.view.getjListPesquisa().setVisible(true);
    }

    public void OcultarLista() {
        this.view.getjListPesquisa().setVisible(false);
        Enter = 1;
    }
    
}
