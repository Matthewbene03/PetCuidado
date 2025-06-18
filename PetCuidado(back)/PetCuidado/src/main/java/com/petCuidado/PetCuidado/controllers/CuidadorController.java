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

import com.petCuidado.PetCuidado.entitiesDTO.CuidadorDTO;
import com.petCuidado.PetCuidado.services.CuidadorService;

@RestController
@RequestMapping("/cuidador")
public class CuidadorController {
	
	@Autowired
	private CuidadorService cuidadorService;

	@GetMapping("/{id}")
	public ResponseEntity<CuidadorDTO> findById(@PathVariable Long id) {
		CuidadorDTO cuidadorDTO = cuidadorService.findById(id);
		return ResponseEntity.ok(cuidadorDTO);
	}

	@GetMapping
	public ResponseEntity<List<CuidadorDTO>> findAll() {
		List<CuidadorDTO> cuidadorDTOs = cuidadorService.findAll();
		return ResponseEntity.ok(cuidadorDTOs);
	}
	
	@PostMapping
	public ResponseEntity<CuidadorDTO> create(@RequestBody CuidadorDTO cuidadorDTO) {
		CuidadorDTO novoCuidador = cuidadorService.insert(cuidadorDTO);
		return ResponseEntity.status(201).body(novoCuidador);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CuidadorDTO> update(@PathVariable Long id, @RequestBody CuidadorDTO cuidadorDTO) {
		CuidadorDTO carroAtualizado = cuidadorService.update(id, cuidadorDTO);
		return ResponseEntity.ok(carroAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		cuidadorService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
