package com.petCuidado.PetCuidado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petCuidado.PetCuidado.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	Pessoa findByCpf(String cpf);
	boolean existsByCpf(String cpf);
}
