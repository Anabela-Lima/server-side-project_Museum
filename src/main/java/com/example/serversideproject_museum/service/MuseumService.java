package com.example.serversideproject_museum.service;

import com.example.serversideproject_museum.model.Country;
import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.repository.MuseumRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

// Service annotation
@Service
public class MuseumService {

    private final MuseumRepository museumRepository;
    private final ExhibitService exhibitService;

    public MuseumService(MuseumRepository museumRepository, ExhibitService exhibitService){
        this.museumRepository = museumRepository;
        this.exhibitService = exhibitService;
    }

    // get all museums
    public List<Museum> findAll() {
        return museumRepository.findAll();
    }


    // get museum
    public Museum getMuseum(Long id) {
        return museumRepository.findById(id).orElseThrow();
    }


    // add museum
    public Museum addMuseum(String name, Country country) {
        return museumRepository.save(new Museum(name, country));
    }


    public void deleteById(Long id) {
        museumRepository.deleteById(id);
    }

    public List<Museum> findByCountry(Country country) {
        return museumRepository.findByCountry(country);
    }

    public Museum addExhibit(Long museum_id, Long exhibit_id) {
        Museum museum = getMuseum(museum_id);   //Museum to add to
        Exhibit exhibit = exhibitService.getExhibit(exhibit_id); //Exhibit to add
        museum.addExhibit(exhibit);  //Perform the add
        exhibit.setMuseum(museum);  //Sets the exhibit's museum
        return museum;
    }
}

