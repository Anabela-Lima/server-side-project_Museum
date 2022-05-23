package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.service.MuseumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/museums")
public class MuseumController {

    private final MuseumService museumService;

    public MuseumController(MuseumService museumService){
        this.museumService = museumService;
    }

    // getall museums
    @GetMapping
    public List<Museum> getMuseums(){
        return museumService.findAll();
    }


    // // method to get a single museum by id

    @GetMapping ("/museum/{id}")
    public Optional<Museum> getAmuseum(@PathVariable Long id){
        return museumService.getMuseum(id);
    }





}
