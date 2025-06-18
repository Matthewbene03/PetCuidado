package com.petCuidado.PetCuidado.entitiesDTO;

import com.petCuidado.PetCuidado.entities.Pessoa;

public class PessoaDTO {

	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String telefone;
	
	public PessoaDTO() {}

	public PessoaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.email = pessoa.getEmail();
		this.telefone = pessoa.getTelefone();
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

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}
}
