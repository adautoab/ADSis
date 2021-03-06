/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author User
 */
public class Calculadora {

    private final double valor;
    private final int visualizaOriginal;
    private int visualizacoesTotal;
    private final int visualiza2;
    private final int visualiza3;
    private final int visualiza4;
    private final int cliquesOriginal;
    private final int cliques2;
    private final int cliques3;
    private final int cliques4;
    private int cliquesTotal;
    private final int compartilhamentosOriginal;
    private final int compartilhamentos2;
    private final int compartilhamentos3;
    private final int compartilhamentos4;
    private int compartilhamentosTotal;

    public Calculadora(double v) {
        valor = v;
        this.visualizaOriginal = (int) (valor * 30);
        this.visualizacoesTotal = visualizaOriginal;
        cliquesOriginal = cliques(visualizaOriginal);
        compartilhamentosOriginal = compartilhamentos(cliquesOriginal);
        visualiza2 = visualizacoes(compartilhamentosOriginal);
        visualizacoesTotal = visualizacoesTotal + visualiza2;

        cliques2 = cliques(visualiza2);
        compartilhamentos2 = compartilhamentos(cliques2);
        visualiza3 = visualizacoes(compartilhamentos2);
        visualizacoesTotal = visualizacoesTotal + visualiza3;
        cliquesTotal = cliquesOriginal + cliques2;
        compartilhamentosTotal = compartilhamentosOriginal + compartilhamentos2;

        cliques3 = cliques(visualiza3);
        compartilhamentos3 = compartilhamentos(cliques3);
        visualiza4 = visualizacoes(compartilhamentos3);
        visualizacoesTotal = visualizacoesTotal + visualiza4;
        cliquesTotal = cliquesTotal + cliques3;
        compartilhamentosTotal = compartilhamentosTotal = compartilhamentos3;

        cliques4 = cliques(visualiza4);
        compartilhamentos4 = compartilhamentos(cliques4);
        visualizacoesTotal = visualizacoesTotal + visualizacoes(compartilhamentos4);
        cliquesTotal = cliquesTotal + cliques4;
        compartilhamentosTotal = compartilhamentosTotal + compartilhamentos4;


    }


    /**
     * M??todo cliques, recebe um n??mero de visualiza??oes e retorna o n??mero
     * estimando de cliques
     *
     * @param visualizacoes
     * @return
     */
    private static int cliques(int visualizacoes) {
        return (12 * visualizacoes) / 100;
    }

    /**
     * M??todo compartilhamento, recebe um n??mero de cliques e retona uma
     * estimativa de quantos compartilhamentos teve
     *
     * @param cliques
     * @return
     */
    private static int compartilhamentos(int cliques) {
        return (3 * cliques) / 20;
    }

    /**
     * M??todo visualiza????es, recebe um n??mero de compartilhamentos e retorna
     * quantas visualiza????es fora geradas
     *
     * @param compartilhamentos
     * @return
     */
    private static int visualizacoes(int compartilhamentos) {
        return compartilhamentos * 40;
    }

    public int getCliquesTotal() {
        return cliquesTotal;

    }

    public int getCompartilhamentosTotal() {
        return compartilhamentosTotal;
    }

    public int getVisualizacoesTotal() {
        return visualizacoesTotal;
    }

}
