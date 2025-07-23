package com.petCuidado.PetCuidado.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private LocalDateTime data;
	
	@ManyToOne
	@JoinColumn(name = "id_pet", nullable = false)
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "id_servico", nullable = false)
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "id_funcionario", nullable = false)
	private Funcionario funcionario;
	
	public Agendamento() {
		super();
	}
	
	public Agendamento(long id, LocalDateTime data, Pet pet, Servico servico, Funcionario funcionario) {
		super();
		this.id = id;
		this.data = data;
		this.pet = pet;
		this.servico = servico;
		this.funcionario = funcionario;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Pet getPet() {
		return pet;
	}
	public void setPet(Pet pet) {
		this.pet = pet;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	@Override
	public int hashCode() {
		return Objects.hash(data, funcionario, id, pet, servico);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		return Objects.equals(data, other.data) && Objects.equals(funcionario, other.funcionario)
				&& id == other.id && Objects.equals(pet, other.pet)
				&& Objects.equals(servico, other.servico);
	}

}
