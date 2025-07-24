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

import com.petCuidado.PetCuidado.entitiesDTO.PessoaDTO;
import com.petCuidado.PetCuidado.services.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;

	@GetMapping("/{id}")
	public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
		PessoaDTO pessoaDTO = pessoaService.findById(id);
		return ResponseEntity.ok(pessoaDTO);
	}

	@GetMapping
	public ResponseEntity<List<PessoaDTO>> findAll() {
		List<PessoaDTO> pessoaDTOs = pessoaService.findAll();
		return ResponseEntity.ok(pessoaDTOs);
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<PessoaDTO> findByCpf(@PathVariable String cpf) {
		PessoaDTO pessoaDTOs = pessoaService.findByCpf(cpf);
		return ResponseEntity.ok(pessoaDTOs);
	}
	
	@GetMapping("/existeCpf/{cpf}")
	public ResponseEntity<Boolean> existsByCpf(@PathVariable String cpf){
		Boolean resposta = pessoaService.existsByCpf(cpf);
		return ResponseEntity.ok(resposta);
	}
	
	@PostMapping
	public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO pessoaDTO) {
		PessoaDTO novoPessoa = pessoaService.insert(pessoaDTO);
		return ResponseEntity.status(201).body(novoPessoa);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
		PessoaDTO pessoaAtualizado = pessoaService.update(id, pessoaDTO);
		return ResponseEntity.ok(pessoaAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pessoaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
