package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Country;
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
*                      - optional extra filter by country
*           - /museum/{id}  - returns a specific museum by id
*       - Post:
*           - /museum/create  - to add a new museum
*       - Put:
*           - /museum/{museum_id}/exhibit/{exhibit_id}  - to add an exhibit to a museum
*       - Delete:
*           - /museum/delete/{id}  - delete a museum by its id
*/

@RestController
@RequestMapping("/museum")
public class MuseumController {

    //   MuseumController Properties

    // MuseumService - museumService - To link controller up with museum service layer
    private final MuseumService museumService;

    // ExhibitService - exhibitService - To link controller with exhibit service layer
    private final ExhibitService exhibitService;


    //   Constructor

    // Initiate controller to include exhibitService and museumService
    public MuseumController(MuseumService museumService, ExhibitService exhibitService) {
        this.museumService = museumService;
        this.exhibitService = exhibitService;
    }

    // =======================================================================================================================
    //                    Endpoints
    // =======================================================================================================================

    //  +--------+
    //  |   Get  |
    //  +--------+

    /*
    *   /museum
    *       - returns a list of all museums available in the repository
    *         by calling findAll() in the museum service layer
    *       - Optional choice to filter the list by country by
    *         calling findByCountry(country) from the museum service layer
    *
    * @RequestParam country  - optional choice to filter museums only from this given country
    *
    * @return List<Museum> - List of all museums in the repository
    *
     */
    @GetMapping
    public List<Museum> getAllMuseums(
            @RequestParam(required = false) Country country //Optional choice to filter by country
    )
    {
        if(country != null){
            return museumService.findByCountry(country);
        }
        return museumService.findAll();
    }

    /*
     *   /museum/{id}
     *       - returns a Museum object, with the given id
     *         by calling getMuseum(id) with given id
     *
     * @PathVariable id - id to be searched for
     *
     * @return Museum - Museum with given id
     *
     */
    @GetMapping("/{id}")
    public Museum getMuseumById(
            @PathVariable Long id  // id of museum to get
    )
    {
        return museumService.getMuseum(id);
    }



    //  +---------+
    //  |   Post  |
    //  +---------+

    /*
    *   /museum/create
    *       - Creates a new museum object with the given name and country
    *       - saves it to the repository by calling addMuseum(name)
    *       - in the service layer
    *
    * @RequestParam name - The name of the new Museum to be created
    * @RequestParam country - The country the museum will be in
    *
    * @Return ResponseEntity<Museum> - The newly created museum as a body
    */
    @PostMapping("/create")
    public ResponseEntity<Museum> addMuseum(
            @RequestParam String name,  // Name for new exhibit
            @RequestParam Country country //Country of museum
    )
    {
        Museum newMuseum = museumService.addMuseum(name, country);
        return ResponseEntity.ok().body(newMuseum);
    }

    //  +--------+
    //  |   Put  |
    //  +--------+


    /*
    *   /museum/{museum_id}/exhibit/{exhibit_id}
    *       - Adds an exhibit to the given museum
    *       - by calling addExhibit(exhibit) on Museum class
    *
    * @PathVariable museum_id  - id of the museum the exhibit is to be added to
    * @PathVariable exhibit_id  - id of the exhibit we are going to add to the museum
    *
    * @Return ResponseEntity<Museum>  - The added to museum as a body, with the newly added exhibit
     */
    @PutMapping("/{museum_id}/exhibit/{exhibit_id}")
    public ResponseEntity<Museum> addExhibitToMuseum(
            @PathVariable Long museum_id,   // id of museum to add to
            @PathVariable Long exhibit_id   // id of exhibit to add
    )
    {
        Museum museum = museumService.getMuseum(museum_id);   //Museum to add to
        Exhibit exhibit = exhibitService.getExhibit(exhibit_id).orElseThrow(); //Exhibit to add
        museum.addExhibit(exhibit);  //Perform the add
        return ResponseEntity.ok().body(museum);
    }


    //  +------------+
    //  |   Delete   |
    //  +------------+


    /*
    *  /museum/delete/{id}
    *     - Deletes a museum with the given id
    *     - by calling deleteById(id) method from the museum service layer
    *
    * @PathVariable id  - id of museum to be deleted
     */
    @DeleteMapping("/delete/{id}")
    public void deleteMuseumById(
            @PathVariable Long id  // id of museum to be deleted
    )
    {
        museumService.deleteById(id);  // Perform the deletion
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
