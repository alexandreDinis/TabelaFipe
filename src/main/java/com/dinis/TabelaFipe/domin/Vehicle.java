package com.dinis.TabelaFipe.domin;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {

    @JsonAlias("Valor") private String valor;
    @JsonAlias("Marca") private String marca;
    @JsonAlias("Modelo") private String modelo;
    @JsonAlias("AnoModelo") private String anoModelo;
    @JsonAlias("Combustivel") private String combustivel;
    @JsonAlias("MesReferencia") private String mesReferencia;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(String anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }


    @Override
    public String toString() {
        return
                ", valor='" + valor + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", anoModelo='" + anoModelo + '\'' +
                ", combustivel='" + combustivel + '\'' +
                ", mesReferencia='" + mesReferencia + '\'';
    }
}
