/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Helper;

import Model.Anuncio;
import View.FrmRelatorios;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class RelatoriosHelper {

    private final FrmRelatorios view;

    public RelatoriosHelper(FrmRelatorios view) {

        this.view = view;
    }

    public void preencherTabela(List<Anuncio> anuncios) {

        DefaultTableModel tableModel = (DefaultTableModel) view.getjTableAnuncios().getModel();
        tableModel.setNumRows(0);

        /**
         * percorrer a lista preenchendo o tableModel
         */
        for (Anuncio anuncio : anuncios) {

            tableModel.addRow(new Object[]{
                anuncio.getId(),
                anuncio.getNome(),
                anuncio.getCliente().getNome(),
                anuncio.getDataInicio(),
                anuncio.getDataFim(),
                anuncio.getInvestimento()

        });

    }

}

    public Object obterIdSelecionado() {
        int linha;
        linha = view.getjTableAnuncios().getSelectedRow();
        return ( view.getjTableAnuncios().getModel().getValueAt(linha, 0)); 

    }

    public void exibeResultado(int dias, Double valorTotal, int cliques, int compartilhamentos, int visualizacoes) {
        view.getjTextFieldDiasTotais().setText(dias+"");
        view.getjTextFieldValorTotalInvestido().setText("R$ "+valorTotal+"");
        view.getjTextFieldQtdeMaxCliques().setText(cliques+"");
        view.getjTextFieldQtdeMaxCompatilhamentos().setText(compartilhamentos+"");
        view.getjTextFieldQtdeMaxVisualizacoes().setText(visualizacoes+"");
    }

}
