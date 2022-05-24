package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Staff;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import com.example.serversideproject_museum.service.ExhibitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ExhibitController {



    @Autowired
    ExhibitRepository exhibitRepository;

    private final ExhibitService exhibitService;

    public ExhibitController(ExhibitService exhibitService) {
        this.exhibitService = exhibitService;
    }

    @GetMapping(path = "/exhibits")
    public ResponseEntity<List<Exhibit>> getAllExhibit() {
        List<Exhibit> exhibits = exhibitService.getAllExhibit();
        return ResponseEntity.ok().body(exhibits);
    }

    // get exhibit by id- done

    @GetMapping(path = "/exhibit/{id}")
    public ResponseEntity<java.util.Optional<Exhibit>> getAnExhibit(@PathVariable Long id ) {
        Optional<Exhibit> exhibit = exhibitService.getExhibit(id);
        return ResponseEntity.ok().body(exhibit);
    }

    @PostMapping("/exhibits")
    public ResponseEntity<Exhibit> addExhibit(@RequestBody Exhibit exhibit){
        Exhibit newExhibit = exhibitRepository.save(exhibit);
        return ResponseEntity.ok().body(newExhibit);
    }

    @PutMapping("exhibits/{id}")
    public ResponseEntity<Exhibit> updateExhibit(@RequestBody Exhibit exhibit, @PathVariable Long id){
        Exhibit update = exhibitRepository.findById(id).map(updatedExhibit -> {
                updatedExhibit.setName(exhibit.getName());
                return exhibitRepository.save(updatedExhibit);})
        .orElseGet(() -> {return exhibitRepository.save(exhibit);});
        return ResponseEntity.ok().body(update);
    }

    @DeleteMapping("exhibits/{id}")
    public ResponseEntity<String> deleteArtefact(@PathVariable Long id){
        exhibitRepository.findById(id);
        return ResponseEntity.ok("Exhibit with id" +id +" has been removed from database.");
    }


}


