package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExhibitController {

    public final ExhibitRepository exhibitRepository;

    public ExhibitController(ExhibitRepository exhibitRepository) {
        this.exhibitRepository = exhibitRepository;
    }

    @GetMapping("/exhibits")
    public ResponseEntity<List<Exhibit>> getAllExhibits(){
        List<Exhibit> exhibits = exhibitRepository.findAll();
        return ResponseEntity.ok().body(exhibits);
    }

    @PostMapping("/exhibits")
    public ResponseEntity<Exhibit> addExhibit(@RequestBody Exhibit exhibit){
        Exhibit newExhibit = exhibitRepository.save(exhibit);
        return ResponseEntity.ok().body(newExhibit);
    }

//    @PutMapping("exhibits/{id}")
//    public ResponseEntity<Exhibit> updateExhibit(@RequestBody Exhibit exhibit, @PathVariable Long id){
//        Exhibit update = exhibitRepository.findbyId(id).map(updatedExhibit -> {
//                update.setName(exhibit.getName());
//                return exhibitRepository.save(update);})
//        .orElseGet(() -> {return exhibitRepository.save(exhibit);});
//        return ResponseEntity.ok().body(update);
//    }

//    @DeleteMapping("exhibits/{id}")
//    public ResponseEntity<String> deleteArtefact(@PathVariable Long id){
//        exhibitRepository.getById(id);
//        return ResponseEntity.ok("Exhibit with id" +id +" has been removed from database.");
//    }


}


