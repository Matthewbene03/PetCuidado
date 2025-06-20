package com.petCuidado.PetCuidado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petCuidado.PetCuidado.entities.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
