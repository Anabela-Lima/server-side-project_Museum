package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Staff;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import com.example.serversideproject_museum.service.ExhibitService;
import com.example.serversideproject_museum.service.MuseumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exhibit")
public class ExhibitController {



    @Autowired
    ExhibitRepository exhibitRepository;

    private final ExhibitService exhibitService;

    @Autowired
    MuseumService museumService;

    public ExhibitController(ExhibitService exhibitService) {
        this.exhibitService = exhibitService;
    }

    // =======================================================================================================================
    //                    Endpoints
    // =======================================================================================================================

    //  +--------+
    //  |   Get  |
    //  +--------+

    // get all exhibits method
    @GetMapping
    public ResponseEntity<List<Exhibit>> getAllExhibit() {
        List<Exhibit> exhibits = exhibitService.getAllExhibit();
        return ResponseEntity.ok().body(exhibits);
    }
    //  +--------+
    //  |   Get  |
    //  +--------+
    // get exhibit by id- done

    @GetMapping("/{id}")
    public ResponseEntity<Exhibit> getAnExhibit(@PathVariable Long id ) {
        Exhibit exhibit = exhibitService.getExhibit(id);
        if(exhibit != null){
            return ResponseEntity.ok().body(exhibit);
        }
        return ResponseEntity.notFound().build();
    }

    //  +---------+
    //  |   Post  |
    //  +---------+
    // create an exhibit
    @PostMapping("/create")
    public ResponseEntity<Exhibit> addExhibit(
            @RequestParam String name,
            @RequestParam(required = false) Long museum_id
    )
    {
        Exhibit newExhibit = new Exhibit(name);
        newExhibit.setMuseum(museumService.getMuseum(museum_id));
        exhibitRepository.save(newExhibit);
        return ResponseEntity.ok().body(newExhibit);
    }

    //  +--------+
    //  |   Put  |
    //  +--------+

    // update an exhibit by id

    @PutMapping("/update/{id}")
    public ResponseEntity<Exhibit> updateExhibit(
            @RequestParam String name,
            @PathVariable Long id){
        Exhibit update = exhibitRepository.findById(id).map(updatedExhibit -> {
                updatedExhibit.setName(name);
                return exhibitRepository.save(updatedExhibit);}).orElse(null);
        return ResponseEntity.ok().body(update);
    }

    //  +------------+
    //  |   Delete   |
    //  +------------+
    // delete an exhibit by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteArtefact(@PathVariable Long id){
        exhibitRepository.deleteById(id);
        return ResponseEntity.ok("Exhibit with id" +id +" has been removed from database.");
    }


}


