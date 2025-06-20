package com.petCuidado.PetCuidado.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petCuidado.PetCuidado.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

}
