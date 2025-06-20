package com.petCuidado.PetCuidado.entitiesDTO;

import com.petCuidado.PetCuidado.entities.Agendamento;
import com.petCuidado.PetCuidado.entities.Pagamento;
import com.petCuidado.PetCuidado.enuns.Metodo;

public class PagamentoDTO {

	private long id;
	private String dataVencimento;
	private String dataPagamento;
	private float valor;
	private Metodo metodo;
	private Agendamento agendamento;
	
	public PagamentoDTO() {
		
	}
	
	public PagamentoDTO(Pagamento pagamento) {
		this.id = pagamento.getId();
		this.dataVencimento = pagamento.getDataVencimento();
		this.dataPagamento = pagamento.getDataPagamento();
		this.valor = pagamento.getValor();
		this.metodo = pagamento.getMetodo();
		this.agendamento = pagamento.getAgendamento();
	}

	public long getId() {
		return id;
	}

	public String getDataVencimento() {
		return dataVencimento;
	}

	public String getDataPagamento() {
		return dataPagamento;
	}

	public float getValor() {
		return valor;
	}

	public Metodo getMetodo() {
		return metodo;
	}

	public Agendamento getAgendamento() {
		return agendamento;
	}
	
}
