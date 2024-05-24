package com.inventivtest.dreamCaseApp.controllers;

import com.inventivtest.dreamCaseApp.dto.DreamCaseDto;
import com.inventivtest.dreamCaseApp.exceptions.dreamCaseException.DreamCaseNotFoundException;
import com.inventivtest.dreamCaseApp.exceptions.dreamCaseException.DreamCaseAlreadExistException;
import com.inventivtest.dreamCaseApp.services.DreamCaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cases")
public class DreamCaseController {

    @Autowired
    private DreamCaseService dreamCaseService;


    @GetMapping("/{caseId}")
    public ResponseEntity<DreamCaseDto> getDreamCaseById(@PathVariable Long caseId) {
        try {
            DreamCaseDto dreamCaseDto = dreamCaseService.getCaseByCaseId(caseId);
            return ResponseEntity.ok(dreamCaseDto);
        } catch (DreamCaseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<DreamCaseDto>> findAll(){
        List<DreamCaseDto> list= dreamCaseService.getListDreamCase();
        return  ResponseEntity.ok().body(list);
    }



    @PutMapping(path = "/{caseId}")
    public ResponseEntity<DreamCaseDto> updateDreamCase(
            @RequestBody DreamCaseDto updatedCaseDto, @PathVariable Long caseId) {
        try {
            DreamCaseDto updatedCase = dreamCaseService.updateCase(updatedCaseDto, caseId);
            return ResponseEntity.ok(updatedCase);
        } catch (DreamCaseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createDreamCase(@Valid @RequestBody DreamCaseDto dtoCase) {
        try {
            dreamCaseService.createDreamCase(dtoCase);
            return ResponseEntity.ok("Dream Case created successfully ✅✅ ");
        } catch (DreamCaseAlreadExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @DeleteMapping(path = "/{caseId}")
    ResponseEntity<String> deleteDreamCase(@PathVariable Long caseId){
        dreamCaseService.deleteDreamCase(caseId);

        return  ResponseEntity.ok("Dream case deleted successfully ✅✅");
    }



}
