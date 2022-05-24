package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.repository.MuseumRepository;
import com.example.serversideproject_museum.service.ExhibitService;
import com.example.serversideproject_museum.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
*   MuseumController  -- API Layer
*   - Handles creation, deletion, updates to museum objects in museum repository
*   - Endpoints available:
*       - Get:
*           - /museum  - returns a list of all museums in the repository
*           - /museum/{id}  - r
*/
@RestController
@RequestMapping("/museum")
public class MuseumController {

    @Autowired
    MuseumService museumService;

    @Autowired
    ExhibitService exhibitService;

    @Autowired
    MuseumRepository museumRepository;


    public MuseumController(MuseumService museumService) {
        this.museumService = museumService;
    }

    @GetMapping
    public List<Museum> getAllMuseums() {
        return museumService.findAll();
    }


    // get museum by id
    @GetMapping("/{id}")
    public Museum getMuseumById(@PathVariable Long id) {
        return museumService.getMuseum(id);
    }


// delete museum by id

    // Accept HTTP DELETE, localhost:8080/deleteMuseumById/id
    @DeleteMapping("/delete/{id}")
    public void deleteMuseumById(@PathVariable Long id) {
        museumRepository.deleteById(id);
    }

    // Post for museum

    @PostMapping("/Create")
    public ResponseEntity<Museum> addMuseum(
            @RequestParam String name)  // Name for new exhibit
    {
        museumService.addMuseum(name);
        return ResponseEntity.ok().build();
    }

    // putMapping

    @PutMapping("{museum_id}/exhibit/{exhibit_id}")
    public ResponseEntity<Exhibit> addExhibitToMuseum(@PathVariable Long museum_id, @PathVariable Long exhibit_id) {
        Museum museum = museumService.getMuseum(museum_id);
        Exhibit exhibit = exhibitService.getExhibit(exhibit_id).orElseThrow();
        museum.addExhibit(exhibit);
        return ResponseEntity.ok().build();
    }


//
//    @PostMapping("/CreateMuseum")
//    public ResponseEntity<Museum> addMuseum(
//            @RequestParam(required = true) String name,  // name
//            @RequestParam(required = true)  Long id )  // id for exhibit
//    {
//        Optional<Exhibit> exhibitOptional = exhibitService.getExhibit(id);
//        if (exhibitOptional.isPresent()) {
//            museumService.addMuseum(name, exhibitOptional.get());
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }


}
