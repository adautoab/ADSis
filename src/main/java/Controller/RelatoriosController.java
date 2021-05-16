/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Helper.RelatoriosHelper;
import DAO.AnunciosDAO;
import DAO.JPAUtil;
import Model.Anuncio;
import View.FrmRelatorios;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author User
 */
public class RelatoriosController {

    private final RelatoriosHelper helper;

    public RelatoriosController(FrmRelatorios view) {
        this.helper = new RelatoriosHelper(view);
    }

    public void atualizarTabela() {

        /**
         * buscar anuncios no banco de dados
         */
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        AnunciosDAO anunciosDAO = new AnunciosDAO(em);
        List<Anuncio> anuncios = anunciosDAO.selectAll();

        em.getTransaction().commit();
        em.close();

        // exibir lista na tabela
        helper.preencherTabela(anuncios);
    }

    public void calcular() {

        Object obj = this.helper.obterIdSelecionado();
        int id = Integer.parseInt(obj.toString());

        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        AnunciosDAO anunciosDAO = new AnunciosDAO(em);
        Anuncio anuncio = anunciosDAO.selectPorId(id);

        em.getTransaction().commit();
        em.close();

        System.out.println("Id: " + anuncio.getId());
        System.out.println("Nome: " + anuncio.getNome());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dInicio = sdf.format(anuncio.getDataInicio());
        String dFim = sdf.format(anuncio.getDataFim());
        int dias = getDaysOfInterval(dInicio, dFim);
        Double valorTotal = anuncio.getInvestimento()*dias;

        System.out.println("Dias: " + dias);
        System.out.println("Valor total investido: " + valorTotal);
        
        int cliques = new Calculadora(valorTotal).getCliquesTotal();
        int visualizacoes = new Calculadora(valorTotal).getVisualizacoesTotal();
        int compartilhamentos = new Calculadora(valorTotal).getCompartilhamentosTotal();
        
        this.helper.exibeResultado(dias, valorTotal, cliques, compartilhamentos, visualizacoes);
        
        System.out.println("Cliques: " + cliques);
        System.out.println("Visualizacoes: " + visualizacoes);    
        System.out.println("compartilhamentos: " + compartilhamentos); 
        
        
    }

    public static int getDaysOfInterval(String dataInit, String dataFinish) {
        int[] data1 = new int[3];
        int[] data2 = new int[3];
        int dias = 0;

        //Desmembra dia, mes e ano das datas;
        data1[0] = Integer.parseInt(dataInit.substring(0, 2)); //dia;
        data1[1] = Integer.parseInt(dataInit.substring(3, 5)); //mes;
        data1[2] = Integer.parseInt(dataInit.substring(6, 10)); //ano;

        data2[0] = Integer.parseInt(dataFinish.substring(0, 2)); //dia;
        data2[1] = Integer.parseInt(dataFinish.substring(3, 5)); //mes;
        data2[2] = Integer.parseInt(dataFinish.substring(6, 10)); //ano;

        int m1 = getDaysMonth(data1[1], data1[2]);
        int m2 = getDaysMonth(data2[1], data2[2]);

        if (data2[2] == data1[2]) { // Calculo para o mesmo ano;
            if (data1[1] == data2[1]) {
                dias = data2[0] - data1[0];
            } else {
                int diasMes1 = (m1 - data1[0]) + 1; 	// numero de dias do primeiro mes (do dia x até o dia 30);
                int diasMes2 = m2 - (m2 - data2[0]);	// numero de dias do ultimo mes (do dia 1 até o dia x);
                int interMeses = (data2[1] - data1[1]);	// quantidade de meses entre o primeiro e o ultimo mes;

                if (interMeses == 0) {
                    dias = diasMes1 + diasMes2;
                } else {
                    for (int i = 1; i < interMeses; i++) {
                        dias += getDaysMonth(data1[1] + i, data1[2]);
                    }
                    dias += (diasMes1 + diasMes2);
                }
            }
        } else {//Calculo para anos diferentes
            int mesesAno1 = 12 - data1[1];	    	//Numero de Meses ate o fim do ano (O Mes1 não está incluído);
            int diasMes1 = m1 - data1[0];		//Numero de dias do Mes1(inicial);
            for (int i = 0; i < mesesAno1; i++) {
                dias += getDaysMonth(data1[1] + i, data1[2]);
            }
            dias += diasMes1;

            int diasMes2 = m2 - (m2 - data2[0]);	    //Numero de dias do ultimo mes do periodo;
            int mesesAno2 = data2[1] - 1;	            //Numero de meses do mes 1 ate o mes x do novo ano;
            for (int i = 0; i < mesesAno2; i++) {
                dias += getDaysMonth(data2[1], data2[2]);
            }
            dias += diasMes2;

            if (data2[2] - data1[2] > 1) {
                dias += 365 * (data2[2] - data1[2] - 1);    // quantidade de anos entre o primeiro e o ultimo ano;
            }
        }
        return dias;
    }

    public static boolean yearIsBisext(int year) {
        //Ano Bisexto (Regras do Calendário Gregoriano):
        // 1 - Todo ano divisível por 4 é bissexto
        // 2 - Todo ano divisível por 100 não é bissexto
        // 3 - Mas se o ano for também divisível por 400 é bissexto
        // FONTE: http://blog.webcalc.com.br/2006/04/29/o-que-e-ano-bissexto/
        boolean retorno;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    retorno = true;
                } else {
                    retorno = false;
                }
            } else {
                retorno = true;
            }
        } else {
            retorno = false;
        }
        return retorno;
    }

    public static int getDaysMonth(int month, int year) {
        //O ano valor do ano será usado para verificar se o ano é bisexto
        int[] mes = new int[12];

        mes[0] = 31;

        if (yearIsBisext(year)) {
            mes[1] = 29;
        } else {
            mes[1] = 28;
        }

        mes[2] = 31;

        mes[3] = 30;

        mes[4] = 31;

        mes[5] = 30;

        mes[6] = 31;

        mes[7] = 31;

        mes[8] = 30;

        mes[9] = 31;

        mes[10] = 30;

        mes[11] = 31;

        return mes[month - 1];
    }

}
