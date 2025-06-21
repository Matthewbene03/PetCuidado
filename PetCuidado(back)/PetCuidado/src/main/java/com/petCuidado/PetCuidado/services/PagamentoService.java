package com.petCuidado.PetCuidado.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCuidado.PetCuidado.entities.Pagamento;
import com.petCuidado.PetCuidado.entitiesDTO.PagamentoDTO;
import com.petCuidado.PetCuidado.repositories.PagamentoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	// Buscar todos 
		public List<PagamentoDTO> findAll() { 
			List<Pagamento> listaPagamento = pagamentoRepository.findAll(); 
			return listaPagamento.stream().map(PagamentoDTO::new).toList(); 
		}
		
		// Buscar por ID 
		public PagamentoDTO findById(Long id) { 
			Pagamento pagamento = pagamentoRepository.findById(id) 
					.orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado com ID: " + id)); 
			return new PagamentoDTO(pagamento); 
		}
		
		// Inserir Produto 
		public PagamentoDTO insert(PagamentoDTO pagamentoDTO) { 
			Pagamento pagamento = new Pagamento(); 
			pagamento.setDataVencimento(pagamentoDTO.getDataVencimento());
			pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
			pagamento.setValor(pagamentoDTO.getValor());
			pagamento.setMetodo(pagamentoDTO.getMetodo());
			pagamento.setAgendamento(pagamentoDTO.getAgendamento());
			Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento); 
			return new PagamentoDTO(pagamentoSalvo); 
		}
		
		// Atualizar Produto 
		public PagamentoDTO update(Long id, PagamentoDTO pagamentoDTO) { 
			Pagamento pagamento = pagamentoRepository.findById(id) 
					.orElseThrow(() -> new EntityNotFoundException("Pagamento não encontrado com ID: " + id)); 
			pagamento.setDataVencimento(pagamentoDTO.getDataVencimento());
			pagamento.setDataPagamento(pagamentoDTO.getDataPagamento());
			pagamento.setValor(pagamentoDTO.getValor());
			pagamento.setMetodo(pagamentoDTO.getMetodo());
			pagamento.setAgendamento(pagamentoDTO.getAgendamento());
			Pagamento pagamentoAtualizado = pagamentoRepository.save(pagamento); 
			return new PagamentoDTO(pagamentoAtualizado); 
		}
		
		// Remover por ID 
		public void delete(Long id) { 
			if (!pagamentoRepository.existsById(id)) { 
				throw new EntityNotFoundException("Pagamento não encontrado com ID: " + id); 
			} 
			pagamentoRepository.deleteById(id); 
		}
		
		//Buscar pagamentos por agendamento
		public List<PagamentoDTO> findByAgendamentoId(Long agendamentoId) {
			List<Pagamento> listaPagamentos = pagamentoRepository.findByAgendamentoId(agendamentoId);
			return listaPagamentos.stream().map(PagamentoDTO::new).toList();
		}
}
