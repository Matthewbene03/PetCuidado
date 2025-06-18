package com.petCuidado.PetCuidado.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCuidado.PetCuidado.entitiesDTO.PetDTO;
import com.petCuidado.PetCuidado.repositories.PetRepository;
import com.petCuidado.PetCuidado.entities.Pet;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepo;

	// Buscar todos
	public List<PetDTO> findAll() {
		List<Pet> listaPet = petRepo.findAll();
		return listaPet.stream().map(PetDTO::new).toList();
	}

	// Buscar por ID
	public PetDTO findById(Long id) {
		Pet pet = petRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com ID: " + id));
		return new PetDTO(pet);
	}

	// Inserir Pet
	public PetDTO insert(PetDTO petDTO) {
		Pet pet = new Pet();
		
		//Set os atributos do petDTO para o pet
		pet.setNome(petDTO.getNome());
		pet.setEspecie(petDTO.getEspecie());
		pet.setRaca(petDTO.getRaca());
		pet.setIdade(petDTO.getIdade());
		pet.setPessoa(petDTO.getPessoaDono());
		
		//Salva no banco
		Pet petSalvo = petRepo.save(pet);
		return new PetDTO(petSalvo);
	}

	// Atualizar Pet
	public PetDTO update(Long id, PetDTO novoPetDTO) {
		Pet pet = petRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet não encontrado com ID: " + id));

		//Set os atributos atualizado do Pet
		pet.setNome(novoPetDTO.getNome());
		pet.setIdade(novoPetDTO.getIdade());
		pet.setPessoa(novoPetDTO.getPessoaDono());
		
		//Salva no banco
		Pet petAtualizado = petRepo.save(pet);
		return new PetDTO(petAtualizado);
	}

	// Remover por ID
	public void delete(Long id) {
		if (!petRepo.existsById(id)) {
			throw new EntityNotFoundException("Pet não encontrado com ID: " + id);
		}
		petRepo.deleteById(id);
	}
}
