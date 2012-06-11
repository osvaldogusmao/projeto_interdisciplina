package br.com.feob.indices;

import java.util.ArrayList;
import java.util.List;

public class Indice {

    private String descricao;
    private Double valor;
    private Integer ano;

    public Indice(int ano, double valor) {
	this.ano = ano;
	this.valor = valor;
    }
    
    public Indice(){
	
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public Double getValor() {
	return valor;
    }

    public void setValor(Double valor) {
	this.valor = valor;
    }

    public Integer getAno() {
	return ano;
    }

    public void setAno(Integer ano) {
	this.ano = ano;
    }

}