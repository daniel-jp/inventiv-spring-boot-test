package com.inventivtest.dreamCaseApp.services;

import com.inventivtest.dreamCaseApp.dto.DreamCaseDto;
import com.inventivtest.dreamCaseApp.entities.DreamCase;
import com.inventivtest.dreamCaseApp.exceptions.dreamCaseException.DreamCaseNotFoundException;
import com.inventivtest.dreamCaseApp.exceptions.dreamCaseException.DreamCaseAlreadExistException;
import com.inventivtest.dreamCaseApp.repositories.DreamCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DreamCaseService {

    @Autowired
    private DreamCaseRepository dreameCaseRepository;
    public DreamCaseService(DreamCaseRepository dreameCaseRepository) {
        this.dreameCaseRepository = dreameCaseRepository;
    }

    // Find dream case by caseId
    public DreamCaseDto getCaseByCaseId(Long caseId) {
        Optional<DreamCase> optionalDreamCase = dreameCaseRepository.findById(caseId);

        if (optionalDreamCase.isEmpty()) {
            throw new DreamCaseNotFoundException("⛔-> The Case with id " + caseId + " you are searching does not exist");
        }
        DreamCase dreamCase = optionalDreamCase.get();
        return new DreamCaseDto(caseId,
                dreamCase.getCreationDate(),
                dreamCase.getLastUpdateDate(),
                dreamCase.getTitle(),
                dreamCase.getDescription()
        );
    }


    //Find all case list
    @Transactional(readOnly = true)
    public List<DreamCaseDto> getListDreamCase() {
        List<DreamCase> list = dreameCaseRepository.findAll();
        return list.stream().map(DreamCaseDto::new).collect(Collectors.toList());
    }


    //Update dream case
    public DreamCaseDto updateCase(DreamCaseDto updatedCaseDto, Long caseId) {
        Optional<DreamCase> optionalDreamCase = dreameCaseRepository.findById(caseId);
        if (optionalDreamCase.isEmpty()) {
            throw new DreamCaseNotFoundException("⛔-> The Case with id " + caseId + " you are trying to update does not exist");
        }
        DreamCase existingCase = optionalDreamCase.get();

        existingCase.setCreationDate(updatedCaseDto.getCreationDate());
        existingCase.setLastUpdateDate(updatedCaseDto.getLastUpdateDate());
        existingCase.setTitle(updatedCaseDto.getTitle());
        existingCase.setDescription(updatedCaseDto.getDescription());

        DreamCase updatedCase = dreameCaseRepository.save(existingCase);

        return new DreamCaseDto(
                updatedCase.getCaseId(),
                updatedCase.getCreationDate(),
                updatedCase.getLastUpdateDate(),
                updatedCase.getTitle(),
                updatedCase.getDescription()
        );
    }


    //Create dream case
    @Transactional
    public ResponseEntity<String> createDreamCase(DreamCaseDto dtoCase) {

        Optional<DreamCase> optionalDreamCase = dreameCaseRepository.findCaseByCaseId(dtoCase.getCaseId());
        if (optionalDreamCase.isPresent() ) {
            throw  new DreamCaseAlreadExistException("Dream Case with id " +dtoCase.getCaseId()+" alread exist");
        }
        DreamCase  dreamCase1 = new DreamCase(null,
                dtoCase.getCreationDate(),
                dtoCase.getLastUpdateDate(),
                dtoCase.getTitle(),
                dtoCase.getDescription());
        dreameCaseRepository.save(dreamCase1);
        return ResponseEntity.ok().build();

    }

    //Delete case
    public void deleteDreamCase(Long id) {

        Optional<DreamCase> caseNotExistId = dreameCaseRepository.findCaseByCaseId(id);

        if (caseNotExistId.isEmpty()) {
            throw  new DreamCaseNotFoundException("Dream case you are trying to DELETE not exist");
        }
        dreameCaseRepository.deleteById(id);
    }




}
