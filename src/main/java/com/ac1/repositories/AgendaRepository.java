package com.ac1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ac1.entities.AgendaEntity;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {

    List<AgendaEntity> findByProfessoresId(Long professorId);

}
