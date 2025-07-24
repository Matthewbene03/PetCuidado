package com.petCuidado.PetCuidado.controllers;

import java.util.List;
import java.util.Map;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/funcionario")
@Tag(name = "Funcionário", description = "Operações relacionadas a Funcionário")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping("/{id}")
	@Operation(summary = "Busca funcionario por ID", description = "Retorna os dados de um funcionário em específico")
	public ResponseEntity<FuncionarioDTO> findById(
			@Parameter(description = "ID do funcionário a ser buscado", example = "1")
			@PathVariable Long id) {
		FuncionarioDTO funcionarioDTO = funcionarioService.findById(id);
		return ResponseEntity.ok(funcionarioDTO);
	}

	@GetMapping
	@Operation(summary = "Busca todos os funcionários", description = "Retorna a lista de todos os funcionários cadastrados")
	public ResponseEntity<List<FuncionarioDTO>> findAll() {
		List<FuncionarioDTO> funcionarioDTOs = funcionarioService.findAll();
		return ResponseEntity.ok(funcionarioDTOs);
	}
	
	@PostMapping("/autenticar")
	@Operation(summary = "Autentica funcionário", description = "Verifica se o login do funcionário existe e se está correto")
	public ResponseEntity<?> autenticar(
			@Parameter(description = "String login contendo usuário e senha")
			@RequestBody Map<String, String> login) {
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
	@Operation(summary = "Cria funcionário", description = "Cria um novo cadastro de funcionário no banco")
	public ResponseEntity<FuncionarioDTO> create(
			@Parameter(description = "Objeto DTO de um funcionário")
			@RequestBody FuncionarioDTO funcionarioDTO) {
		FuncionarioDTO novoFuncionario = funcionarioService.insert(funcionarioDTO);
		return ResponseEntity.status(201).body(novoFuncionario);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um funcionário", description = "Atualiza um funcionário já existente")
	public ResponseEntity<FuncionarioDTO> update(
			@Parameter(description = "ID do funcionário que você quer atualizar mais o objeto DTO com os dados do funcionário atualizados")
			@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
		FuncionarioDTO funcionarioAtualizado = funcionarioService.update(id, funcionarioDTO);
		return ResponseEntity.ok(funcionarioAtualizado);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um funcionário", description = "Deleta um funcionário com base em seu ID")
	public ResponseEntity<Void> delete(
			@Parameter(description = "ID do agendamento a ser deletado", example = "1")
			@PathVariable Long id) {
		funcionarioService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
