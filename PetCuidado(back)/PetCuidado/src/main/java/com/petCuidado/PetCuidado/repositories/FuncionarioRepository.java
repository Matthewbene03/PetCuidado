package com.petCuidado.PetCuidado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petCuidado.PetCuidado.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	Funcionario findByUsuarioAndSenha(String usuario, String senha);
}
