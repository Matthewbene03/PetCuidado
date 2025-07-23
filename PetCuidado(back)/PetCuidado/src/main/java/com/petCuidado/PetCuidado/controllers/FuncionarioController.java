package com.petCuidado.PetCuidado.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petCuidado.PetCuidado.entitiesDTO.FuncionarioDTO;
import com.petCuidado.PetCuidado.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
		FuncionarioDTO funcionarioDTO = funcionarioService.findById(id);
		return ResponseEntity.ok(funcionarioDTO);
	}

	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> findAll() {
		List<FuncionarioDTO> funcionarioDTOs = funcionarioService.findAll();
		return ResponseEntity.ok(funcionarioDTOs);
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity<?> autenticar(@RequestBody Map<String, String> login) {
	    String usuario = login.get("usuario");
	    String senha = login.get("senha");

	    FuncionarioDTO funcionario = funcionarioService.autenticar(usuario, senha);
	    if (funcionario != null) {
	        return ResponseEntity.ok(funcionario);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
	    }
	}
	
	@PostMapping
	public ResponseEntity<FuncionarioDTO> create(@RequestBody FuncionarioDTO funcionarioDTO) {
		FuncionarioDTO novoFuncionario = funcionarioService.insert(funcionarioDTO);
		return ResponseEntity.status(201).body(novoFuncionario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioDTO> update(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
		FuncionarioDTO funcionarioAtualizado = funcionarioService.update(id, funcionarioDTO);
		return ResponseEntity.ok(funcionarioAtualizado);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		funcionarioService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
