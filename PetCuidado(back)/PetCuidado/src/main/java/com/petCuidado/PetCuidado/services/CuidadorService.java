package com.petCuidado.PetCuidado.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCuidado.PetCuidado.entitiesDTO.CuidadorDTO;
import com.petCuidado.PetCuidado.repositories.CuidadorRepository;
import com.petCuidado.PetCuidado.entities.Cuidador;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CuidadorService {

	@Autowired
	private CuidadorRepository cuidadorRepo;

	// Buscar todos
	public List<CuidadorDTO> findAll() {
		List<Cuidador> listaCuidador = cuidadorRepo.findAll();
		return listaCuidador.stream().map(CuidadorDTO::new).toList();
	}

	// Buscar por ID
	public CuidadorDTO findById(Long id) {
		Cuidador cuidador = cuidadorRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cuidador não encontrado com ID: " + id));
		return new CuidadorDTO(cuidador);
	}

	// Inserir Cuidador
	public CuidadorDTO insert(CuidadorDTO cuidadorDTO) {
		Cuidador cuidador = new Cuidador();
		
		//Set os atributos do cuidadorDTO para o cuidador
		cuidador.setId(cuidadorDTO.getId());
		
		//Salva no banco
		Cuidador cuidadorSalvo = cuidadorRepo.save(cuidador);
		return new CuidadorDTO(cuidadorSalvo);
	}

	// Atualizar Cuidador
	public CuidadorDTO update(Long id, CuidadorDTO novoCuidadorDTO) {
		Cuidador cuidador = cuidadorRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cuidador não encontrado com ID: " + id));
		//Set os atributos atualizado do cuidador
		cuidador.setNome(novoCuidadorDTO.getNome());
		
		//Salva no banco
		Cuidador atualizado = cuidadorRepo.save(cuidador);
		return new CuidadorDTO(atualizado);
	}

	// Remover por ID
	public void delete(Long id) {
		if (!cuidadorRepo.existsById(id)) {
			throw new EntityNotFoundException("Cuidador não encontrado com ID: " + id);
		}
		cuidadorRepo.deleteById(id);
	}
}
