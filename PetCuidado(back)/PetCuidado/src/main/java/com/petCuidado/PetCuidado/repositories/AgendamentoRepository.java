package com.petCuidado.PetCuidado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petCuidado.PetCuidado.entities.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	List<Agendamento> findByServicoId(Long servicoId);
	List<Agendamento> findByPetId(Long petId);
	List<Agendamento> findByFuncionarioId(Long funcionarioId);
}
