/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.FrmAnuncios;
import View.FrmClientes;
import View.FrmPrincipal;

/**
 *
 * @author User
 */
public class PrincipalController {

    private final FrmPrincipal view;

    public PrincipalController(FrmPrincipal view) {
        this.view = view;
        
    }

    public void navegarParaClientes() {
        FrmClientes frmClientes = new FrmClientes();
        frmClientes.setVisible(true);
    }

    public void navegarParaAnuncios() {
        FrmAnuncios frmAnuncios = new FrmAnuncios();
        frmAnuncios.setVisible(true);
    }

    public void navegarRelatorios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
