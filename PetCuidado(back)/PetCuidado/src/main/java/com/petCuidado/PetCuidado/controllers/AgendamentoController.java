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
import com.petCuidado.PetCuidado.entitiesDTO.PagamentoDTO;
import com.petCuidado.PetCuidado.services.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	@GetMapping("/{id}") 
	public ResponseEntity<AgendamentoDTO> findById(@PathVariable Long id) { 
		AgendamentoDTO agendamentoDTO = agendamentoService.findById(id); 
		return ResponseEntity.ok(agendamentoDTO); 
	}
	
	@GetMapping 
	public ResponseEntity<List<AgendamentoDTO>> findAll() { 
		List<AgendamentoDTO> AgendamentoDTOs = agendamentoService.findAll(); 
		return ResponseEntity.ok(AgendamentoDTOs); 
	}
	
	@PostMapping 
	public ResponseEntity<AgendamentoDTO> create(@RequestBody AgendamentoDTO agendamentoDTO) { 
		AgendamentoDTO novoAgendamento = agendamentoService.insert(agendamentoDTO); 
		return ResponseEntity.status(201).body(novoAgendamento); 
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<AgendamentoDTO> update(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDTO) { 
		AgendamentoDTO agendamentoAtualizado = agendamentoService.update(id, agendamentoDTO); 
		return ResponseEntity.ok(agendamentoAtualizado); 
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Void> delete(@PathVariable Long id) { 
		agendamentoService.delete(id); 
		return ResponseEntity.noContent().build(); 
	}
	
	@GetMapping("/servico/{servicoId}")
	public ResponseEntity<List<AgendamentoDTO>> findByServicoId(@PathVariable long servicoId) {
		List<AgendamentoDTO> agendamentoDTOs = agendamentoService.findByServicoId(servicoId);
		return ResponseEntity.ok(agendamentoDTOs);
	}
	
	@GetMapping("/pet/{petId}")
	public ResponseEntity<List<AgendamentoDTO>> findByPetId(@PathVariable long petId) {
		List<AgendamentoDTO> agendamentoDTOs = agendamentoService.findByPetId(petId);
		return ResponseEntity.ok(agendamentoDTOs);
	}
	
	@GetMapping("/funcionario/{funcionarioId}")
	public ResponseEntity<List<AgendamentoDTO>> findByFuncionarioId(@PathVariable long funcionarioId) {
		List<AgendamentoDTO> agendamentoDTOs = agendamentoService.findByFuncionarioId(funcionarioId);
		return ResponseEntity.ok(agendamentoDTOs);
	}
}
