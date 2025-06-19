package com.petCuidado.PetCuidado.entitiesDTO;

import com.petCuidado.PetCuidado.entities.Servico;

public class ServicoDTO {
	
	private long id;
	private String descricao;
	private float preco;
	
	public ServicoDTO() {
	}
	
	public ServicoDTO(Servico servico) {
		this.id = servico.getId();
		this.descricao = servico.getDescricao();
		this.preco = servico.getPreco();
	}

	public long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public float getPreco() {
		return preco;
	}
	
}
