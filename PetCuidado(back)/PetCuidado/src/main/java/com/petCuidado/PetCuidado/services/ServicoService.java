package com.petCuidado.PetCuidado.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCuidado.PetCuidado.entities.Servico;
import com.petCuidado.PetCuidado.entitiesDTO.ServicoDTO;
import com.petCuidado.PetCuidado.repositories.ServicoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
	
	// Buscar todos 
	public List<ServicoDTO> findAll(){ 
		List<Servico> listaServico = servicoRepository.findAll(); 
		return listaServico.stream().map(ServicoDTO::new).toList(); 
	}
	
	// Buscar por ID 
	public ServicoDTO findById(Long id) { 
		Servico servico = servicoRepository.findById(id) 
				.orElseThrow(() -> new EntityNotFoundException("Tipo não encontrado com ID: " + id)); 
		return new ServicoDTO(servico); 
	}
	
	// Inserir 
	public ServicoDTO insert(ServicoDTO servicoDTO) { 
		Servico servico = new Servico(); 
		servico.setDescricao(servicoDTO.getDescricao());
		servico.setPreco(servicoDTO.getPreco());
		servico.setDuracao(servicoDTO.getDuracao());
		Servico servicoSalvo = servicoRepository.save(servico);
		return new ServicoDTO(servicoSalvo); 
	}
	
	// Atualizar 
	public ServicoDTO update(Long id, ServicoDTO servicoDTO) { 
		Servico servico = servicoRepository.findById(id) 
				.orElseThrow(() -> new EntityNotFoundException("Servico não encontrado com ID: " + id)); 
		servico.setDescricao(servicoDTO.getDescricao());
		servico.setPreco(servicoDTO.getPreco());
		servico.setDuracao(servicoDTO.getDuracao());
		Servico servicoAtualizado = servicoRepository.save(servico); 
		return new ServicoDTO(servicoAtualizado); 
	}
	
	// Remover por ID 
	public void delete(Long id) { 
		if (!servicoRepository.existsById(id)) { 
			throw new EntityNotFoundException("Servico não encontrado com ID: " + id); 
		} 
		servicoRepository.deleteById(id); 
	}
}
