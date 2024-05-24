package com.inventivtest.dreamCaseApp.repositories;

import com.inventivtest.dreamCaseApp.entities.DreamCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DreamCaseRepository extends JpaRepository<DreamCase, Long> {
    Optional<DreamCase> findCaseByCaseId(Long caseId); // Modificado para aceitar um argumento

}