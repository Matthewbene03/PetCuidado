package com.petCuidado.PetCuidado.entitiesDTO;

import java.time.LocalDate;

import com.petCuidado.PetCuidado.entities.Cuidador;
import com.petCuidado.PetCuidado.enumCargos.EnumCargo;

public class CuidadorDTO {
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private String email;
	private String telefone;
	private EnumCargo cargo;
	private String usuario;
	private String senha;
	
	public CuidadorDTO() {}
	
	public CuidadorDTO(Cuidador cuidador){
		this.id = cuidador.getId();
		this.nome = cuidador.getNome();
		this.cpf = cuidador.getCpf();
		this.dataNascimento = cuidador.getDataNascimento();
		this.email = cuidador.getEmail();
		this.telefone = cuidador.getTelefone();
		this.cargo = cuidador.getCargo();
		this.usuario = cuidador.getUsuario();
		this.senha = cuidador.getSenha();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public EnumCargo getCargo() {
		return cargo;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getSenha() {
		return senha;
	}
}
