package com.petCuidado.PetCuidado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.petCuidado.PetCuidado.entitiesDTO.PetDTO;
import com.petCuidado.PetCuidado.services.PetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pet")
@Tag(name = "Pet", description = "Operações relacionadas a um pet")
public class PetController {
	
	@Autowired
	private PetService petService;

	@GetMapping("/{id}")
	@Operation(summary = "Busca pet por ID", description = "Retorna os dados de um pet em específico")
	public ResponseEntity<PetDTO> findById(
			@Parameter(description = "ID do pet a ser buscado", example = "1")
			@PathVariable Long id) {
		PetDTO petDTO = petService.findById(id);
		return ResponseEntity.ok(petDTO);
	}

	@GetMapping
	@Operation(summary = "Busca todos os pets", description = "Retorna a lista de todos os pets cadastrados")
	public ResponseEntity<List<PetDTO>> findAll() {
		List<PetDTO> petDTOs = petService.findAll();
		return ResponseEntity.ok(petDTOs);
	}
	
	@PostMapping
	@Operation(summary = "Cria pet", description = "Cria um novo cadastro de pet no banco")
	public ResponseEntity<PetDTO> create(
			@Parameter(description = "Objeto DTO de um pet")
			@RequestBody PetDTO petDTO) {
		PetDTO novoPet = petService.insert(petDTO);
		return ResponseEntity.status(201).body(novoPet);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um pet", description = "Atualiza um pet já existente")
	public ResponseEntity<PetDTO> update(
			@Parameter(description = "ID do pet que você quer atualizar mais o objeto DTO com os dados do pet atualizados")
			@PathVariable Long id, @RequestBody PetDTO petDTO) {
		PetDTO petAtualizado = petService.update(id, petDTO);
		return ResponseEntity.ok(petAtualizado);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um pet", description = "Deleta um pet com base em seu ID")
	public ResponseEntity<Void> delete(
			@Parameter(description = "ID do serviço a ser deletado", example = "1")
			@PathVariable Long id) {
		petService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
