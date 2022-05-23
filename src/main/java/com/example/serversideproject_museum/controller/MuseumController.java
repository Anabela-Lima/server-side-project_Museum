package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.service.MuseumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/museum")
public class MuseumController {

    private final MuseumService museumService;

    public MuseumController(MuseumService museumService){
        this.museumService = museumService;
    }
    @GetMapping
    public List<Museum> getMuseums(){
        return museumService.findAll();
    }
}
