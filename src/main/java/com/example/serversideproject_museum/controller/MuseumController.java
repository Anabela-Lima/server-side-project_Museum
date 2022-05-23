package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.repository.ArtefactRepository;
import com.example.serversideproject_museum.repository.MuseumRepository;
import com.example.serversideproject_museum.service.ExhibitService;
import com.example.serversideproject_museum.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/museum")
public class MuseumController {

    @Autowired
    MuseumService museumService;

    @Autowired
    ExhibitService exhibitService;

    @Autowired
    MuseumRepository museumRepository;




    public MuseumController(MuseumService museumService){
        this.museumService = museumService;
    }
    @GetMapping
    public List<Museum> getMuseums(){
        return museumService.findAll();
    }



// get museum by id
    @GetMapping("/museum/{id}")
    public Optional<Museum> getMuseum(@PathVariable Long id) {
        return museumService.getMuseum(id);

    }


// delete museum by id

    // Accept HTTP DELETE, localhost:8080/deleteMuseum/id
    @DeleteMapping("/deleteMuseum/{id}")
    public void deleteMuseum(@PathVariable Long id) {
        museumRepository.deleteById(id);

    }

 // Post for museum

    @PostMapping("/CreateMuseum")
    public ResponseEntity<Museum> addMuseum(
            @RequestParam(required = true) String name)  // id for exhibit
    {
           museumService.addMuseum(name);
            return ResponseEntity.ok().build();
    }

 // putMapping

 @PutMapping("/museum/{museum_id}/exhibit/{exhibit_id}")
    public ResponseEntity<Exhibit>addExhibitID(@RequestParam @PathVariable Long museum_id, @PathVariable Long exhibit_id){

        Museum museum = museumService.getMuseum(museum_id).orElseThrow();
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
