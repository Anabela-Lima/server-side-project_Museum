package com.example.serversideproject_museum.service;

import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.repository.MuseumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
