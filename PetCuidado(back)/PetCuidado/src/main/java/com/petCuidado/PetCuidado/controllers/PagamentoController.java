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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pagamentos")
@Tag(name = "Pagamento", description = "Operações relacionadas a um Pagamento")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@GetMapping("/{id}") 
	@Operation(summary = "Busca pagamento por ID", description = "Retorna os dados de um pagamento em específico")
	public ResponseEntity<PagamentoDTO> findById(
			@Parameter(description = "ID do pagamento a ser buscado", example = "1")
			@PathVariable Long id) { 
		PagamentoDTO pagamentoDTO = pagamentoService.findById(id); 
		return ResponseEntity.ok(pagamentoDTO); 
	}
	
	@GetMapping 
	@Operation(summary = "Busca todos os pagamentos", description = "Retorna a lista de todos os pagamentos cadastrados")
	public ResponseEntity<List<PagamentoDTO>> findAll() { 
		List<PagamentoDTO> PagamentoDTOs = pagamentoService.findAll(); 
		return ResponseEntity.ok(PagamentoDTOs); 
	}
	
	@PostMapping 
	@Operation(summary = "Cria pagamento", description = "Cria um novo cadastro de pagamento no banco")
	public ResponseEntity<PagamentoDTO> create(
			@Parameter(description = "Objeto DTO de um pagamento")
			@RequestBody PagamentoDTO pagamentoDTO) { 
		PagamentoDTO novoPagamento = pagamentoService.insert(pagamentoDTO); 
		return ResponseEntity.status(201).body(novoPagamento); 
	}
	
	@PutMapping("/{id}") 
	@Operation(summary = "Atualiza um pagamento", description = "Atualiza um pagamento já existente")
	public ResponseEntity<PagamentoDTO> update(
			@Parameter(description = "ID do pagamento que você quer atualizar mais o objeto DTO com os dados do pagamento atualizados")
			@PathVariable Long id, @RequestBody PagamentoDTO pagamentoDTO) { 
		PagamentoDTO pagamentoAtualizado = pagamentoService.update(id, pagamentoDTO); 
		return ResponseEntity.ok(pagamentoAtualizado); 
	}
	
	@DeleteMapping("/{id}") 
	@Operation(summary = "Deleta um pagamento", description = "Deleta um pagamento com base em seu ID")
	public ResponseEntity<Void> delete(
			@Parameter(description = "ID do pagamento a ser deletado", example = "1")
			@PathVariable Long id) { 
		pagamentoService.delete(id); 
		return ResponseEntity.noContent().build(); 
	}
	
	@GetMapping("/agendamento/{agendamentoId}")
	@Operation(summary = "Busca pagamentos com base no ID do agendamento", description = "Retorna uma lista de todos os pagamentos de um determindado agendamento")
	public ResponseEntity<List<PagamentoDTO>> findByAgendamentoId(
			@Parameter(description = "ID do agendamento", example = "1")
			@PathVariable long agendamentoId) {
		List<PagamentoDTO> pagamentoDTOs = pagamentoService.findByAgendamentoId(agendamentoId);
		return ResponseEntity.ok(pagamentoDTOs);
	}
}
