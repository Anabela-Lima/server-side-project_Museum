package com.example.serversideproject_museum.service;

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

@Service
public class MuseumService {

    private final MuseumRepository museumRepository;

    public MuseumService(MuseumRepository museumRepository){
        this.museumRepository = museumRepository;
    }

    public List<Museum> findAll() {

        return museumRepository.findAll();
    }


    // get museum

    public Optional<Museum> getMuseum(Long id) {
        return museumRepository.findById(id);
    }


    // add museum
    public void addMuseum(String name, Exhibit exhibit) {
        return museumRepository.save(new Museum(name, exhibit)

    }

}

