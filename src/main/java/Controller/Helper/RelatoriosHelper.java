/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Helper;

import Model.Anuncio;
import View.FrmRelatorios;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

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
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            tableModel.addRow(new Object[]{
                anuncio.getId(),
                anuncio.getNome(),
                anuncio.getCliente().getNome(),
                sdf.format(anuncio.getDataInicio()), 
                sdf.format(anuncio.getDataFim()),
                "   R$: "+(int)anuncio.getInvestimento()+",00"

            });

        }

    }

    public Object obterIdSelecionado() {
        int linha;
        linha = view.getjTableAnuncios().getSelectedRow();
        return (view.getjTableAnuncios().getModel().getValueAt(linha, 0));

    }

    public void exibeResultado(int dias, Double valorTotal, int cliques, int compartilhamentos, int visualizacoes) {
        view.getjTextFieldDiasTotais().setText(dias + "");
        view.getjTextFieldValorTotalInvestido().setText("R$ " + valorTotal + "");
        view.getjTextFieldQtdeMaxCliques().setText(cliques + "");
        view.getjTextFieldQtdeMaxCompatilhamentos().setText(compartilhamentos + "");
        view.getjTextFieldQtdeMaxVisualizacoes().setText(visualizacoes + "");
    }

    public String ObterString() {
        return view.getjTextFieldPesquisaPorCliente().getText();
    }

    public DefaultTableModel pegaTableModel() {
        return (DefaultTableModel) view.getjTableAnuncios().getModel();
    }

    public void setaRowSorter(TableRowSorter<DefaultTableModel> tr) {
        view.getjTableAnuncios().setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + view.getjTextFieldPesquisaPorCliente().getText().trim()));

    }

}
