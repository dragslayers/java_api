package com.example.API.repositories;

import com.example.API.models.Candidat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatRepository extends CrudRepository<Candidat,Long> {
    @Override
    List<Candidat> findAll();

}
