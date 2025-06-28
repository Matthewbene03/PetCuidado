package com.petCuidado.PetCuidado.entities;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_servico")
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private float preco;
	
	@Column(nullable = false)
	private int duracao;
	
	public Servico() {
	}
	
	public Servico(long id, String descricao, float preco, int duracao) {
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.duracao = duracao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, duracao, id, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;
		return Objects.equals(descricao, other.descricao) && duracao == other.duracao && id == other.id
				&& Float.floatToIntBits(preco) == Float.floatToIntBits(other.preco);
	}
	
}
