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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviço", description = "Operações relacionadas a um Serviço")
public class ServicoController {

	@Autowired
	private ServicoService servicoService;
	
	@GetMapping("/{id}") 
	@Operation(summary = "Busca serviço por ID", description = "Retorna os dados de um serviço em específico")
	public ResponseEntity<ServicoDTO> findById(
			@Parameter(description = "ID do serviço a ser buscado", example = "1")
			@PathVariable Long id) { 
		ServicoDTO servicoDTO = servicoService.findById(id); 
		return ResponseEntity.ok(servicoDTO); 
	}
	
	@GetMapping 
	@Operation(summary = "Busca todos os serviços", description = "Retorna a lista de todos os serviços cadastrados")
	public ResponseEntity<List<ServicoDTO>> findAll() {
		List<ServicoDTO> servicoDTO = servicoService.findAll(); 
		return ResponseEntity.ok(servicoDTO); 
	}
	
	@PostMapping 
	@Operation(summary = "Cria serviço", description = "Cria um novo cadastro de serviço no banco")
	public ResponseEntity<ServicoDTO> create(
			@Parameter(description = "Objeto DTO de um serviço")
			@RequestBody ServicoDTO servicoDTO) { 
		ServicoDTO novoServico = servicoService.insert(servicoDTO); 
		return ResponseEntity.status(201).body(novoServico); 
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um serviço", description = "Atualiza um serviço já existente")
	public ResponseEntity<ServicoDTO> update(
			@Parameter(description = "ID do serviço que você quer atualizar mais o objeto DTO com os dados do serviço atualizados")
			@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) { 
		ServicoDTO servicoAtualizado = servicoService.update(id, servicoDTO); 
		return ResponseEntity.ok(servicoAtualizado); 
	}
	
	@DeleteMapping("/{id}") 
	@Operation(summary = "Deleta um serviço", description = "Deleta um serviço com base em seu ID")
	public ResponseEntity<Void> delete(
			@Parameter(description = "ID do serviço a ser deletado", example = "1")
			@PathVariable Long id) { 
		servicoService.delete(id); 
		return ResponseEntity.noContent().build(); 
	}
}
