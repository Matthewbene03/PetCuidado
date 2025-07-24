package com.petCuidado.PetCuidado.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCuidado.PetCuidado.entitiesDTO.PessoaDTO;
import com.petCuidado.PetCuidado.repositories.PessoaRepository;
import com.petCuidado.PetCuidado.entities.Pessoa;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepo;

	// Buscar todos
	public List<PessoaDTO> findAll() {
		List<Pessoa> listaPessoa = pessoaRepo.findAll();
		return listaPessoa.stream().map(PessoaDTO::new).toList();
	}

	// Buscar por ID
	public PessoaDTO findById(Long id) {
		Pessoa pessoa = pessoaRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrado com ID: " + id));
		return new PessoaDTO(pessoa);
	}
	
	public PessoaDTO findByCpf(String cpf) {
		Pessoa pessoa = pessoaRepo.findByCpf(cpf);
		return new PessoaDTO(pessoa);
	}
	
	public boolean existsByCpf(String cpf) {
		boolean existsPessoa = pessoaRepo.existsByCpf(cpf);		
		return existsPessoa;
	}

	// Inserir Pessoa
	public PessoaDTO insert(PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		
		//Set os atributos do pessoaDTO para o pessoa
		pessoa.setNome(pessoaDTO.getNome());
		pessoa.setCpf(pessoaDTO.getCpf());
		pessoa.setEmail(pessoaDTO.getEmail());
		pessoa.setTelefone(pessoaDTO.getTelefone());
		
		//Salva no banco
		Pessoa pessoaSalvo = pessoaRepo.save(pessoa);
		return new PessoaDTO(pessoaSalvo);
	}

	// Atualizar Pessoa
	public PessoaDTO update(Long id, PessoaDTO novoPessoaDTO) {
		Pessoa pessoa = pessoaRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrado com ID: " + id));

		//Set os atributos atualizado do Pessoa
		pessoa.setNome(novoPessoaDTO.getNome());
		//pessoa.setCpf(novoPessoaDTO.getCpf());
		pessoa.setEmail(novoPessoaDTO.getEmail());
		pessoa.setTelefone(novoPessoaDTO.getTelefone());
		
		//Salva no banco
		Pessoa pessoaAtualizado = pessoaRepo.save(pessoa);
		return new PessoaDTO(pessoaAtualizado);
	}

	// Remover por ID
	public void delete(Long id) {
		if (!pessoaRepo.existsById(id)) {
			throw new EntityNotFoundException("Pessoa não encontrado com ID: " + id);
		}
		pessoaRepo.deleteById(id);
	}
}
