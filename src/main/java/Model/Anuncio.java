/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Adauto
 */

@Entity
public class Anuncio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    private Double investimento;

    public Anuncio() {
    }

    public Anuncio(int id, String nome, Cliente cliente, String dataInicio, String dataFim, double investimento) {
        this.id = id;
        this.nome = nome;
        this.cliente = cliente;
        try {
            this.dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(dataInicio);
        } catch (ParseException ex) {
            Logger.getLogger(Anuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(dataFim);
        } catch (ParseException ex) {
            Logger.getLogger(Anuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.investimento = investimento;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public double getInvestimento() {
        return investimento;
    }

    public void setInvestimento(Double investimento) {
        this.investimento = investimento;
    }

  
}
