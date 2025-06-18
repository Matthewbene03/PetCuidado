package com.petCuidado.PetCuidado.entitiesDTO;

import com.petCuidado.PetCuidado.entities.Pessoa;
import com.petCuidado.PetCuidado.entities.Pet;

public class PetDTO {
	private Long id;	
	private String nome;
	private String especie;
	private String raca;
	private int idade;
	
	private Pessoa pessoaDono;
	
	public PetDTO() {}

	public PetDTO(Pet pet) {
		super();
		this.id = pet.getId();
		this.nome = pet.getNome();
		this.especie = pet.getEspecie();
		this.raca = pet.getRaca();
		this.idade = pet.getIdade();
		this.pessoaDono = pet.getPessoa();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEspecie() {
		return especie;
	}

	public String getRaca() {
		return raca;
	}

	public int getIdade() {
		return idade;
	}

	public Pessoa getPessoaDono() {
		return pessoaDono;
	}
}
