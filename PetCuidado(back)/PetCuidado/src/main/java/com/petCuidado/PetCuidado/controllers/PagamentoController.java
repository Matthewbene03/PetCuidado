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

import com.petCuidado.PetCuidado.entitiesDTO.PagamentoDTO;
import com.petCuidado.PetCuidado.services.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@GetMapping("/{id}") 
	public ResponseEntity<PagamentoDTO> findById(@PathVariable Long id) { 
		PagamentoDTO pagamentoDTO = pagamentoService.findById(id); 
		return ResponseEntity.ok(pagamentoDTO); 
	}
	
	@GetMapping 
	public ResponseEntity<List<PagamentoDTO>> findAll() { 
		List<PagamentoDTO> PagamentoDTOs = pagamentoService.findAll(); 
		return ResponseEntity.ok(PagamentoDTOs); 
	}
	
	@PostMapping 
	public ResponseEntity<PagamentoDTO> create(@RequestBody PagamentoDTO pagamentoDTO) { 
		PagamentoDTO novoPagamento = pagamentoService.insert(pagamentoDTO); 
		return ResponseEntity.status(201).body(novoPagamento); 
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<PagamentoDTO> update(@PathVariable Long id, @RequestBody PagamentoDTO pagamentoDTO) { 
		PagamentoDTO pagamentoAtualizado = pagamentoService.update(id, pagamentoDTO); 
		return ResponseEntity.ok(pagamentoAtualizado); 
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Void> delete(@PathVariable Long id) { 
		pagamentoService.delete(id); 
		return ResponseEntity.noContent().build(); 
	}
}
