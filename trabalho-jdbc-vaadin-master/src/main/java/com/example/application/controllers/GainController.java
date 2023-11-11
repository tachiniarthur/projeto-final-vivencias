package com.example.application.controllers;

import java.util.Date;

public class GainController {
    private Integer id_gain;
    private String tipo;
    private Date data;
    private double valor;

    public GainController() {
    }

    public GainController(Integer id_gain, String tipo, Date date, double valor) {
        this.id_gain = id_gain;
        this.tipo = tipo;
        this.data = date;
        this.valor = valor;
    }

    public Integer getId() {
        return id_gain;
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
}