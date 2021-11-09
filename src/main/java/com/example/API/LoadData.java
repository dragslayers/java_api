package com.example.API;

import com.example.API.models.Candidat;
import com.example.API.repositories.CandidatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class LoadData {

    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner initDatabase(CandidatRepository repository) throws ParseException {
        log.info("Launch Preloading");
        if(repository.count() == 0){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dateNaissance = formatter.parse("05/06/1974");

            Candidat candidat = new Candidat("candidat 1","candidat 1", dateNaissance, "Place charles Hernu", "Villeurbane", "69100");
            return args -> {
              log.info("Preloading"+repository.save(candidat));
            };
        }
        else {
            return args -> {
                log.info("Already initialized");
            };
        }
    }
}
