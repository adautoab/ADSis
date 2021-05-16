/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Helper;

import Model.Anuncio;
import Model.Cliente;
import View.FrmAnuncios;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author Adauto
 */
public class AnunciosHelper implements IHelper {

    private final FrmAnuncios view;
    int Enter = 0;

    public AnunciosHelper(FrmAnuncios view) {
        this.view = view;
    }

    @Override
    public Anuncio obterModelo() {

        Integer id;
        if (view.getjTextFieldID().getText().equals("")) {
            id = 0;
        } else {
            id = Integer.parseInt(view.getjTextFieldID().getText());
        }

        String nome = view.getjTextFieldNome().getText();
        Cliente cliente = obterCliente();
        String dataInicio = view.getjFormattedTextFieldDataInicio().getText();
        String dataTermino = view.getjFormattedTextFieldDataTermino().getText();
        Double valorInvestimento;
        valorInvestimento = Double.parseDouble(view.getjTextFieldInvestimento().getText());

        System.out.println(id);
        System.out.println(cliente.getNome());
        System.out.println(dataInicio);
        System.out.println(dataTermino);
        System.out.println(valorInvestimento);

        Anuncio anuncio;
        anuncio = new Anuncio(id, nome, cliente, dataInicio, dataTermino, valorInvestimento);
        return anuncio;
    }

    @Override
    public void limparTela() {
        this.view.getjTextFieldID().setText("");
        this.view.getjTextFieldNome().setText("");
        this.view.getjFormattedTextFieldDataInicio().setText("");
        this.view.getjFormattedTextFieldDataTermino().setText("");
        this.view.getjTextFieldInvestimento().setText("");
 
    }

    public void preencherCampos(Anuncio anuncio) {
        view.getjTextFieldID().setText(anuncio.getId() + "");
        view.getjTextFieldNome().setText(anuncio.getNome());
        view.getjComboBoxCliente().getModel().setSelectedItem(anuncio.getCliente().getNome());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        view.getjFormattedTextFieldDataInicio().setText(sdf.format(anuncio.getDataInicio()));
        view.getjFormattedTextFieldDataTermino().setText(sdf.format(anuncio.getDataFim()));
        view.getjTextFieldInvestimento().setText(anuncio.getInvestimento() + "");
    }

    private Cliente obterCliente() {
        return (Cliente) view.getjComboBoxCliente().getSelectedItem();
    }

    public void preencherClientes(List<Cliente> clientes) {
        DefaultComboBoxModel comboBoxModel = (DefaultComboBoxModel) view.getjComboBoxCliente().getModel();

        for (Cliente cliente : clientes) {
            comboBoxModel.addElement(cliente); //aqui está o truque           
        }

    }

    public void OcultarLista() {
        this.view.getjListPesquisa().setVisible(false);
        Enter = 1;
    }

    public String ObterString() {
        return this.view.getjTextFieldPesquisa().getText();

    }

    public void preencherListField(DefaultListModel lista) {
        if (Enter == 0) {
            this.view.getjListPesquisa().setModel(lista);
        } else {
            Enter = 0;
        }
    }

    public void mostrarJList() {
        this.view.getjListPesquisa().setVisible(true);
    }
}
