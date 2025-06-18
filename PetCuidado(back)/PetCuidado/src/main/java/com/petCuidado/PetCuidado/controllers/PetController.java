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

@RestController
@RequestMapping("/pet")
public class PetController {
	
	@Autowired
	private PetService petService;

	@GetMapping("/{id}")
	public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
		PetDTO petDTO = petService.findById(id);
		return ResponseEntity.ok(petDTO);
	}

	@GetMapping
	public ResponseEntity<List<PetDTO>> findAll() {
		List<PetDTO> petDTOs = petService.findAll();
		return ResponseEntity.ok(petDTOs);
	}
	
	@PostMapping
	public ResponseEntity<PetDTO> create(@RequestBody PetDTO petDTO) {
		PetDTO novoPet = petService.insert(petDTO);
		return ResponseEntity.status(201).body(novoPet);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PetDTO> update(@PathVariable Long id, @RequestBody PetDTO petDTO) {
		PetDTO petAtualizado = petService.update(id, petDTO);
		return ResponseEntity.ok(petAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		petService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
