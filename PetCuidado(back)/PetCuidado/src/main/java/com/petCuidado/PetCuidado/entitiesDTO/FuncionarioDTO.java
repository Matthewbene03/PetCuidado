package com.petCuidado.PetCuidado.entitiesDTO;

import com.petCuidado.PetCuidado.entities.Funcionario;
import com.petCuidado.PetCuidado.entities.Pessoa;
import com.petCuidado.PetCuidado.enuns.EnumCargo;

public class FuncionarioDTO{
	private Long id;
	private EnumCargo cargo;
	private String usuario;
	private String senha;
	private Pessoa pessoa;
	
	public FuncionarioDTO() {}
	
	public FuncionarioDTO(Funcionario funcionario){
		super();
		this.id = funcionario.getId();
		this.cargo = funcionario.getCargo();
		this.usuario = funcionario.getUsuario();
		this.senha = funcionario.getSenha();
		this.pessoa = funcionario.getPessoa();
	}

	public Long getId() {
		return id;
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

	public Pessoa getPessoa() {
		return pessoa;
	}	
}
