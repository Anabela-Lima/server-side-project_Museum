package com.example.serversideproject_museum.service;

import com.example.serversideproject_museum.model.Artefact;
import com.example.serversideproject_museum.repository.ArtefactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtefactService {

    @Autowired
    ArtefactRepository artefactRepository;

    public List<Artefact> getAllArtefact() {return artefactRepository.findAll();

    }


}
