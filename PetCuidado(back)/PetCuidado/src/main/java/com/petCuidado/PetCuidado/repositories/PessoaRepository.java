package com.petCuidado.PetCuidado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petCuidado.PetCuidado.entities.Funcionario;
import com.petCuidado.PetCuidado.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
