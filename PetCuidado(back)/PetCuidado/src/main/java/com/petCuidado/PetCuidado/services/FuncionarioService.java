package com.petCuidado.PetCuidado.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petCuidado.PetCuidado.entitiesDTO.FuncionarioDTO;
import com.petCuidado.PetCuidado.repositories.FuncionarioRepository;
import com.petCuidado.PetCuidado.entities.Funcionario;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepo;

	// Buscar todos
	public List<FuncionarioDTO> findAll() {
		List<Funcionario> listaFuncionario = funcionarioRepo.findAll();
		return listaFuncionario.stream().map(FuncionarioDTO::new).toList();
	}

	// Buscar por ID
	public FuncionarioDTO findById(Long id) {
		Funcionario funcionario = funcionarioRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado com ID: " + id));
		return new FuncionarioDTO(funcionario);
	}

	public FuncionarioDTO autenticar(String usuario, String senha) {
	    Funcionario funcionario = funcionarioRepo.findByUsuarioAndSenha(usuario, senha);
	    if (funcionario != null) {
	        return new FuncionarioDTO(funcionario);
	    } else {
	        return null;
	    }
	}

	
	// Inserir Funcionario
	public FuncionarioDTO insert(FuncionarioDTO funcionarioDTO) {		
		Funcionario funcionario = new Funcionario();
		
		//Set os atributos do funcionarioDTO para o funcionario
		funcionario.setUsuario(funcionarioDTO.getUsuario());
		funcionario.setSenha(funcionarioDTO.getSenha());
		funcionario.setPessoa(funcionarioDTO.getPessoa());
		//EnumCargo cargo = EnumCargo.valueOf(funcionarioDTO.getCargo())
		funcionario.setCargo(funcionarioDTO.getCargo());
		
		//Salva no banco
		Funcionario funcionarioSalvo = funcionarioRepo.save(funcionario);
		return new FuncionarioDTO(funcionarioSalvo);
	}

	// Atualizar Funcionario
	public FuncionarioDTO update(Long id, FuncionarioDTO novoFuncionarioDTO) {
		Funcionario funcionario = funcionarioRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Funcionario não encontrado com ID: " + id));

		//Set os atributos atualizado do Funcionario
		funcionario.setUsuario(novoFuncionarioDTO.getUsuario());
		funcionario.setSenha(novoFuncionarioDTO.getSenha());
		funcionario.setPessoa(novoFuncionarioDTO.getPessoa());
		funcionario.setCargo(novoFuncionarioDTO.getCargo());
		
		//Salva no banco
		Funcionario funcionarioAtualizado = funcionarioRepo.save(funcionario);
		return new FuncionarioDTO(funcionarioAtualizado);
	}

	// Remover por ID
	public void delete(Long id) {
		if (!funcionarioRepo.existsById(id)) {
			throw new EntityNotFoundException("Funcionario não encontrado com ID: " + id);
		}
		funcionarioRepo.deleteById(id);
	}
}
