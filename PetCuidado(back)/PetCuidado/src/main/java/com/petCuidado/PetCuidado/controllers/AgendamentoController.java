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

import com.petCuidado.PetCuidado.entitiesDTO.AgendamentoDTO;
import com.petCuidado.PetCuidado.services.AgendamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamento", description = "Operações relacionadas a Agendamentos")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	@GetMapping("/{id}") 
	@Operation(summary = "Busca agendamento por ID", description = "Retorna os dados de um agendamento em específico")
	public ResponseEntity<AgendamentoDTO> findById(
			@Parameter(description = "ID do agendamento a ser buscado", example = "1")
			@PathVariable Long id) { 
		AgendamentoDTO agendamentoDTO = agendamentoService.findById(id); 
		return ResponseEntity.ok(agendamentoDTO); 
	}
	
	@GetMapping
	@Operation(summary = "Busca todos os agendamentos", description = "Retorna a lista de todos os agendamentos cadastrados")
	public ResponseEntity<List<AgendamentoDTO>> findAll() { 
		List<AgendamentoDTO> AgendamentoDTOs = agendamentoService.findAll(); 
		return ResponseEntity.ok(AgendamentoDTOs); 
	}
	
	@PostMapping 
	@Operation(summary = "Cria agendamento", description = "Cria um novo cadastro de agendamento no banco")
	public ResponseEntity<AgendamentoDTO> create(
			@Parameter(description = "Objeto DTO de um agendamento")
			@RequestBody AgendamentoDTO agendamentoDTO) { 
		AgendamentoDTO novoAgendamento = agendamentoService.insert(agendamentoDTO); 
		return ResponseEntity.status(201).body(novoAgendamento); 
	}
	
	@PutMapping("/{id}") 
	@Operation(summary = "Atualiza um agendamento", description = "Atualiza um agendamento já existente")
	public ResponseEntity<AgendamentoDTO> update(
			@Parameter(description = "ID do agendamento que você quer atualizar mais o objeto DTO com os dados do agendamento atualizados")
			@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDTO) { 
		AgendamentoDTO agendamentoAtualizado = agendamentoService.update(id, agendamentoDTO); 
		return ResponseEntity.ok(agendamentoAtualizado); 
	}
	
	@DeleteMapping("/{id}") 
	@Operation(summary = "Deleta um agendamento", description = "Deleta um agendamento com base em seu ID")
	public ResponseEntity<Void> delete(
			@Parameter(description = "ID do agendamento a ser deletado", example = "1")
			@PathVariable Long id) { 
		agendamentoService.delete(id); 
		return ResponseEntity.noContent().build(); 
	}
	
	@GetMapping("/servico/{servicoId}")
	@Operation(summary = "Busca agendamentos com base no ID do Serviço", description = "Retorna uma lista de todos os agendamentos de um determindado Serviço")
	public ResponseEntity<List<AgendamentoDTO>> findByServicoId(
			@Parameter(description = "ID do serviço", example = "1")
			@PathVariable long servicoId) {
		List<AgendamentoDTO> agendamentoDTOs = agendamentoService.findByServicoId(servicoId);
		return ResponseEntity.ok(agendamentoDTOs);
	}
	
	@GetMapping("/pet/{petId}")
	@Operation(summary = "Busca agendamentos com base no ID do Pet", description = "Retorna uma lista de todos os agendamentos de um determindado Pet")
	public ResponseEntity<List<AgendamentoDTO>> findByPetId(
			@Parameter(description = "ID do pet", example = "1")
			@PathVariable long petId) {
		List<AgendamentoDTO> agendamentoDTOs = agendamentoService.findByPetId(petId);
		return ResponseEntity.ok(agendamentoDTOs);
	}
	
	@GetMapping("/funcionario/{funcionarioId}")
	@Operation(summary = "Busca agendamentos com base no ID do Funcionario", description = "Retorna uma lista de todos os agendamentos de um determindado Funcionario")
	public ResponseEntity<List<AgendamentoDTO>> findByFuncionarioId(
			@Parameter(description = "ID do funcionario", example = "1")
			@PathVariable long funcionarioId) {
		List<AgendamentoDTO> agendamentoDTOs = agendamentoService.findByFuncionarioId(funcionarioId);
		return ResponseEntity.ok(agendamentoDTOs);
	}
}
