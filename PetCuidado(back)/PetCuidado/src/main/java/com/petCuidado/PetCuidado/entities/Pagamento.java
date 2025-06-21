package com.petCuidado.PetCuidado.entities;

import java.util.Objects;

import com.petCuidado.PetCuidado.enuns.Metodo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String dataVencimento;
	
	@Column(nullable = true)
	private String dataPagamento;
	
	@Column(nullable = true)
	private float valor;
	
	@Column(nullable = true)
	private Metodo metodo;
	
	@ManyToOne
	@JoinColumn(name = "id_agendamento", nullable = false)
	private Agendamento agendamento;
	
	public Pagamento() {
		
	}
	
	public Pagamento(long id, String dataVencimento, String dataPagamento, float valor, Metodo metodo, Agendamento agendamento) {
		this.id = id;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		this.metodo = metodo;
		this.agendamento = agendamento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Metodo getMetodo() {
		return metodo;
	}

	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(agendamento, dataPagamento, dataVencimento, id, metodo, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(agendamento, other.agendamento) && Objects.equals(dataPagamento, other.dataPagamento)
				&& Objects.equals(dataVencimento, other.dataVencimento) && id == other.id && metodo == other.metodo
				&& Float.floatToIntBits(valor) == Float.floatToIntBits(other.valor);
	}
	
}
