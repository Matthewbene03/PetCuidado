package com.petCuidado.PetCuidado.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petCuidado.PetCuidado.entities.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

	List<Agendamento> findByServicoId(Long servicoId);
	List<Agendamento> findByPetId(Long petId);
	List<Agendamento> findByFuncionarioId(Long funcionarioId);
	
	@Query("""
		    SELECT a FROM Agendamento a 
		    WHERE
		    (
		        (a.pet.id = :petId OR a.funcionario.id = :funcionarioId)
		        AND (
		            a.data BETWEEN :inicio AND :fim
		        )
		    )
		""")
	List<Agendamento> findConflitosAgendamento(
		   @Param("petId") Long petId,
		   @Param("funcionarioId") Long funcionarioId,
		   @Param("inicio") LocalDateTime inicio,
		   @Param("fim") LocalDateTime fim
	);

}
