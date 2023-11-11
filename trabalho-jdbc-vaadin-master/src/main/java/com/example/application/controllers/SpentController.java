package com.example.application.controllers;

import java.util.Date;

public class SpentController {
    private Integer id_spent;
    private String tipo;
    private Date data;
    private double valor;
    private String formaPagamento;

    public SpentController() {
    }

    public SpentController(Integer id_spent, String tipo, Date date, double valor, String formaPagamento) {
        this.id_spent = id_spent;
        this.tipo = tipo;
        this.data = date;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }

    public Integer getId() {
        return id_spent;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}