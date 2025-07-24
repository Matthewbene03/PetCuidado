package com.petCuidado.PetCuidado.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCuidado.PetCuidado.entities.Agendamento;
import com.petCuidado.PetCuidado.entitiesDTO.AgendamentoDTO;
import com.petCuidado.PetCuidado.repositories.AgendamentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	// Buscar todos 
	public List<AgendamentoDTO> findAll() { 
		List<Agendamento> listaAgendamento = agendamentoRepository.findAll(); 
		return listaAgendamento.stream().map(AgendamentoDTO::new).toList(); 
	}
	
	// Buscar por ID 
	public AgendamentoDTO findById(Long id) { 
		Agendamento agendamento = agendamentoRepository.findById(id) 
				.orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com ID: " + id)); 
		return new AgendamentoDTO(agendamento); 
	}
	
	// Inserir Produto 
	public AgendamentoDTO insert(AgendamentoDTO agendamentoDTO) {
		LocalDateTime inicio = agendamentoDTO.getData();
        int duracao = agendamentoDTO.getServico().getDuracao();
        LocalDateTime fim = inicio.plusMinutes(duracao);

        Long petId = agendamentoDTO.getPet().getId();
        Long funcionarioId = agendamentoDTO.getFuncionario().getId();

        List<Agendamento> conflitos = agendamentoRepository.findConflitosAgendamento(
            petId, funcionarioId, inicio, fim
        );

        if (!conflitos.isEmpty()) {
            throw new RuntimeException("Conflito de horário com o pet ou funcionário.");
        }
		
		Agendamento agendamento = new Agendamento(); 
		agendamento.setData(agendamentoDTO.getData());
		agendamento.setPet(agendamentoDTO.getPet());
		agendamento.setServico(agendamentoDTO.getServico());
		agendamento.setFuncionario(agendamentoDTO.getFuncionario());
		agendamento.setStatus(agendamentoDTO.getStatus());
		Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento); 
		return new AgendamentoDTO(agendamentoSalvo); 
	}
	
	// Atualizar Produto 
	public AgendamentoDTO update(Long id, AgendamentoDTO agendamentoDTO) { 
		Agendamento agendamento = agendamentoRepository.findById(id) 
				.orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com ID: " + id)); 
		agendamento.setData(agendamentoDTO.getData());
		agendamento.setPet(agendamentoDTO.getPet());
		agendamento.setServico(agendamentoDTO.getServico());
		agendamento.setFuncionario(agendamentoDTO.getFuncionario());
		agendamento.setStatus(agendamentoDTO.getStatus());
		Agendamento agendamentoAtualizado = agendamentoRepository.save(agendamento); 
		return new AgendamentoDTO(agendamentoAtualizado); 
	}
	
	// Remover por ID 
	public void delete(Long id) { 
		if (!agendamentoRepository.existsById(id)) { 
			throw new EntityNotFoundException("Agendamento não encontrado com ID: " + id); 
		} 
		agendamentoRepository.deleteById(id); 
	}
	
	//Buscar agendamentos por servico
	public List<AgendamentoDTO> findByServicoId(Long servicoId) {
		List<Agendamento> listaAgendamentos = agendamentoRepository.findByServicoId(servicoId);
		return listaAgendamentos.stream().map(AgendamentoDTO::new).toList();
	}
	
	//Buscar agendamentos por pet
	public List<AgendamentoDTO> findByPetId(Long petId) {
		List<Agendamento> listaAgendamentos = agendamentoRepository.findByPetId(petId);
		return listaAgendamentos.stream().map(AgendamentoDTO::new).toList();
	}
		
	//Buscar agendamentos por funcionario
	public List<AgendamentoDTO> findByFuncionarioId(Long funcionarioId) {
		List<Agendamento> listaAgendamentos = agendamentoRepository.findByFuncionarioId(funcionarioId);
		return listaAgendamentos.stream().map(AgendamentoDTO::new).toList();
	}
}
