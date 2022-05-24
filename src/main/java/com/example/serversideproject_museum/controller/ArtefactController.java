package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Artefact;
import com.example.serversideproject_museum.repository.ArtefactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtefactController {

    private final ArtefactRepository artefactRepository;

    public ArtefactController(ArtefactRepository artefactRepository) {
        this.artefactRepository = artefactRepository;
    }

    @GetMapping("/artefacts")
    public ResponseEntity<List<Artefact>> getAllArtefacts(){
        List<Artefact> artefacts = artefactRepository.findAll();
        return ResponseEntity.ok().body(artefacts);
    }

    @GetMapping("artefacts/{country}")
    public ResponseEntity<List<Artefact>> getByArtefactcountry(@PathVariable("country") String country){
        List<Artefact> artefacts = artefactRepository.findByCountry(country);
        return ResponseEntity.ok().body(artefacts);
    }

    @PostMapping("/artefacts")
    public ResponseEntity<Artefact> addArtefact(@RequestBody Artefact artefact){
        Artefact newArtefact = artefactRepository.save(artefact);
        return ResponseEntity.ok().body(newArtefact);
    }

    @DeleteMapping("artefacts/{id}")
    public ResponseEntity<String> deleteArtefact(@PathVariable Long id){
        artefactRepository.findById(id);
        return ResponseEntity.ok("Artefact with id" +id +" has been removed from database.");
    }

}