package com.example.API.controllers;

import com.example.API.exceptions.RestApplicationExceptionHandler;
import com.example.API.models.Candidat;
import com.example.API.repositories.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/candidats")
public class CandidatController {

//    @GetMapping(value = "/hello")
//    String hello() {
//        return "hello world";
//    }

    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping(value = "/")
    List<Candidat> all() {
        return candidatRepository.findAll();
    }

    @GetMapping(value = "/{candidat}")
    Candidat getOne(@PathVariable(name="candidat",required = false )Candidat candidat) {
        if(candidat == null) {
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "candidat introuvable"
            );
        }
        return candidat;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Candidat> saveCandidat(@Valid @RequestBody Candidat candidat) {
        candidatRepository.save(candidat);
        return new ResponseEntity<>(candidat,HttpStatus.CREATED);
    }

    @PutMapping(value="/{candidat}")
    public ResponseEntity<Candidat> update(@PathVariable(name = "candidat",required = false) Candidat candidat,
                                           @Valid @RequestBody Candidat candidatUpdate, BindingResult bindingResult) {
        if(candidat == null) {
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "candidat introuvable"
            );
        }

        if(bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }

        candidatUpdate.setId(candidat.getId());
        candidatRepository.save(candidatUpdate);
        return new ResponseEntity<>(candidatUpdate,HttpStatus.OK);
    }

    @DeleteMapping(value ="/{candidat}")
    public void deleteOne(@PathVariable(name = "candidat",required = false) Candidat candidat) {
        if(candidat == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "candidat introuvable"
            );
        }
        candidatRepository.delete(candidat);
    }
}
