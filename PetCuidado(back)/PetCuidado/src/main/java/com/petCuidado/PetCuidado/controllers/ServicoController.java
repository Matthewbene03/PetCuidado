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

import com.petCuidado.PetCuidado.entitiesDTO.ServicoDTO;
import com.petCuidado.PetCuidado.services.ServicoService;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

	@Autowired
	private ServicoService servicoService;
	
	@GetMapping("/{id}") 
	public ResponseEntity<ServicoDTO> findById(@PathVariable Long id) { 
		ServicoDTO servicoDTO = servicoService.findById(id); 
		return ResponseEntity.ok(servicoDTO); 
	}
	
	@GetMapping public ResponseEntity<List<ServicoDTO>> findAll() {
		List<ServicoDTO> servicoDTO = servicoService.findAll(); 
		return ResponseEntity.ok(servicoDTO); 
	}
	
	@PostMapping 
	public ResponseEntity<ServicoDTO> create(@RequestBody ServicoDTO servicoDTO) { 
		ServicoDTO novoServico = servicoService.insert(servicoDTO); 
		return ResponseEntity.status(201).body(novoServico); 
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<ServicoDTO> update(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) { 
		ServicoDTO servicoAtualizado = servicoService.update(id, servicoDTO); 
		return ResponseEntity.ok(servicoAtualizado); 
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Void> delete(@PathVariable Long id) { 
		servicoService.delete(id); 
		return ResponseEntity.noContent().build(); 
	}
}
