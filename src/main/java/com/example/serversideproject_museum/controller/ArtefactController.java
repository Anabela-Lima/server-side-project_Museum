package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Artefact;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Country;
import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.model.dto.ArtefactDto;
import com.example.serversideproject_museum.repository.ArtefactRepository;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import com.example.serversideproject_museum.service.ArtefactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/artefact")
public class ArtefactController {

    @Autowired
    ArtefactRepository artefactRepository;

    @Autowired
    ExhibitRepository exhibitRepository;

    private final ArtefactService artefactService;

    public ArtefactController(ArtefactService artefactService) {
        this.artefactService = artefactService;
    }

    // =======================================================================================================================
    //                    Endpoints
    // =======================================================================================================================

    //  +--------+
    //  |   Get  |
    //  +--------+

    // Get all artefacts method
    @GetMapping
    public ResponseEntity<List<ArtefactDto>> getAllArtefacts(){
        List<ArtefactDto> artefacts = artefactService.getAllArtefact();
        return ResponseEntity.ok().body(artefacts);
    }

    //  +--------+
    //  |   Get  |
    //  +--------+
    // Get all artefacts by exhibit id method
    @GetMapping("/byExhibit/{id}")
    public ResponseEntity<List<ArtefactDto>> findByExhibits(@PathVariable Long id){
        List<ArtefactDto> artefacts = artefactService.findByExhibits(id);
        return ResponseEntity.ok().body(artefacts);
    }
    //  +--------+
    //  |   Get  |
    //  +--------+

    // get artefact by country method
    @GetMapping("/{country}")
    public ResponseEntity<List<ArtefactDto>> getByArtefactcountry(@PathVariable("country") Country country){
        List<ArtefactDto> artefacts = artefactService.findByCountryDto(country);
        return ResponseEntity.ok().body(artefacts);
    }

    //  +---------+
    //  |   Post  |
    //  +---------+

    @PostMapping("/create")
    public ResponseEntity<Artefact> addArtefact(
            @RequestParam String name,
            @RequestParam String creator,
            @RequestParam String date,
            @RequestParam Country country,
            @RequestParam Long exhibitId
    )
    {
        Exhibit exhibitToAddTo = exhibitRepository.findById(exhibitId).get();   //Find the exhibit we want to add to
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Artefact newArtefact = artefactService.addArtefact(name, creator, localDate, country);
        newArtefact.setExhibits(exhibitToAddTo);
        artefactRepository.save(newArtefact);
        return ResponseEntity.ok().body(newArtefact);
    }

    //  +--------+
    //  |   Put  |
    //  +--------+

    @Transactional
    @PutMapping("/update/{id}")
    public ResponseEntity<Artefact> updateArtefact(
            @RequestParam String name,
            @RequestParam String creator,
            @RequestParam String date,
            @RequestParam Country country,
            @PathVariable Long id)
    {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Artefact update = artefactRepository.findById(id).get();
        update.setName(name);
        update.setCreator(creator);
        update.setDate(localDate);
        update.setCountry(country);
        artefactRepository.save(update); //overwrites in the database
        return ResponseEntity.ok().body(update);
    }



    //  +------------+
    //  |   Delete   |
    //  +------------+

    // delete an artefact method
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteArtefactById
    (@PathVariable Long id){
        artefactService.deleteById(id);
        return ResponseEntity.ok("Artefact with id " +id +" has been removed from database.");

    }




}