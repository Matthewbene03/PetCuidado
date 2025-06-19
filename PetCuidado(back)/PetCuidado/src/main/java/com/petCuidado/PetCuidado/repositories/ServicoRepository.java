package com.petCuidado.PetCuidado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petCuidado.PetCuidado.entities.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
	
}
