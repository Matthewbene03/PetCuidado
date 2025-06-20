package com.petCuidado.PetCuidado.entitiesDTO;

import com.petCuidado.PetCuidado.entities.Agendamento;
import com.petCuidado.PetCuidado.entities.Funcionario;
import com.petCuidado.PetCuidado.entities.Pet;
import com.petCuidado.PetCuidado.entities.Servico;

public class AgendamentoDTO {

	private long id;
	private String data;
	private String hora;
	private Pet pet;
	private Servico servico;
	private Funcionario funcionario;
	
	public AgendamentoDTO() {
		
	}
	
	public AgendamentoDTO(Agendamento agendamento) {
		this.id = agendamento.getId();
		this.data = agendamento.getData();
		this.hora = agendamento.getHora();
		this.pet = agendamento.getPet();
		this.servico = agendamento.getServico();
		this.funcionario = agendamento.getFuncionario();
	}

	public long getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public String getHora() {
		return hora;
	}

	public Pet getPet() {
		return pet;
	}

	public Servico getServico() {
		return servico;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	
}
