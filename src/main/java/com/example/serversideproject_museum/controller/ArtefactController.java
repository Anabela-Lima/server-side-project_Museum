package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Artefact;
import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.dto.ArtefactDto;
import com.example.serversideproject_museum.repository.ArtefactRepository;
import com.example.serversideproject_museum.service.ArtefactService;
import com.example.serversideproject_museum.service.ExhibitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArtefactController {

    @Autowired
    ArtefactRepository artefactRepository;

    private final ArtefactService artefactService;

    public ArtefactController(ArtefactService artefactService) {
        this.artefactService = artefactService;
    }


//    private final ArtefactRepository artefactRepository;
//
//    public ArtefactController(ArtefactRepository artefactRepository) {
//        this.artefactRepository = artefactRepository;
//    }

    // Get all arefacts method
    @GetMapping("/artefacts")
    public ResponseEntity<List<Artefact>> getAllArtefacts(){
        List<Artefact> artefacts = artefactService.getAllArtefact();
        return ResponseEntity.ok().body(artefacts);
    }


    // get artefact by country method
    @GetMapping("artefacts/{country}")
    public ResponseEntity<List<ArtefactDto>> getByArtefactcountry(@PathVariable("country") String country){
        List<ArtefactDto> artefacts = artefactService.findByCountryDto(country);
        return ResponseEntity.ok().body(artefacts);
    }

    // create an artefact method
    @PostMapping("/artefacts")
    public ResponseEntity<Artefact> addArtefact(@RequestBody Artefact artefact){
        Artefact newArtefact = artefactRepository.save(artefact);
        return ResponseEntity.ok().body(newArtefact);
    }

    // update an artefact by id
    @PutMapping("artefacts/{id}")
    public ResponseEntity<Artefact> updateArtefact(@RequestBody Artefact artefact, @PathVariable Long id){
        Artefact update = artefactRepository.findById(id).map(updatedArtefact -> {
                    updatedArtefact.setName(artefact.getName());
                    return artefactRepository.save(updatedArtefact);})
                .orElseGet(() -> {return artefactRepository.save(artefact);});
        return ResponseEntity.ok().body(update);
    }

    // delete an artefact method
    @DeleteMapping("artefacts/{id}")
    public ResponseEntity<String> deleteArtefactById
    (@PathVariable Long id){
        artefactService.deleteById(id);
        return ResponseEntity.ok("Artefact with id" +id +" has been removed from database.");

    }

//    @DeleteMapping("/delete/{id}")
//    public void deleteMuseumById(
//            @PathVariable Long id  // id of museum to be deleted
//    )
//    {
//        museumService.deleteById(id);  // Perform the deletion
//    }


}