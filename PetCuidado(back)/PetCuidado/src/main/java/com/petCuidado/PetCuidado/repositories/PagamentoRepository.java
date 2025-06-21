package com.petCuidado.PetCuidado.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petCuidado.PetCuidado.entities.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

	List<Pagamento> findByAgendamentoId(Long agendamentoId);
}
