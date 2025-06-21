package com.petCuidado.PetCuidado.entities;

import java.util.Objects;

import com.petCuidado.PetCuidado.enuns.EnumCargo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {

	/*
	 * @Column(name = "nome", nullable = false)
	 * 
	 * @Column(name = "nome", nullable = false, unique = true)
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private EnumCargo cargo;
	
	@Column(nullable = false, unique = true)
	private String usuario;
	
	@Column(nullable = false)
	private String senha;
	
	@ManyToOne
	@JoinColumn(name = "id_pessoa", nullable = false)
	private Pessoa pessoa;

	public Funcionario() {
	}

	public Funcionario(EnumCargo cargo, String usuario, String senha, Pessoa pessoa) {
		this.usuario = usuario;
		this.senha = senha;
		this.cargo = cargo;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public EnumCargo getCargo() {
		return cargo;
	}

	public void setCargo(EnumCargo cargo) {
		this.cargo = cargo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cargo, id, pessoa, senha, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return Objects.equals(cargo, other.cargo) && Objects.equals(id, other.id)
				&& Objects.equals(pessoa, other.pessoa) && Objects.equals(senha, other.senha)
				&& Objects.equals(usuario, other.usuario);
	}
}
