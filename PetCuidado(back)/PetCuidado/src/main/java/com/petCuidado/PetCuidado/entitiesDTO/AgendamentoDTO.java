package com.petCuidado.PetCuidado.entitiesDTO;

import java.time.LocalDateTime;

import com.petCuidado.PetCuidado.entities.Agendamento;
import com.petCuidado.PetCuidado.entities.Funcionario;
import com.petCuidado.PetCuidado.entities.Pet;
import com.petCuidado.PetCuidado.entities.Servico;

public class AgendamentoDTO {

	private long id;
	private LocalDateTime data;
	private Pet pet;
	private Servico servico;
	private Funcionario funcionario;
	
	public AgendamentoDTO() {
		
	}
	
	public AgendamentoDTO(Agendamento agendamento) {
		this.id = agendamento.getId();
		this.data = agendamento.getData();
		this.pet = agendamento.getPet();
		this.servico = agendamento.getServico();
		this.funcionario = agendamento.getFuncionario();
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getData() {
		return data;
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
