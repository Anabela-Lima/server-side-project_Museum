package com.example.serversideproject_museum.service;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExhibitService {


    @Autowired
    ExhibitRepository exhibitRepository;


    public  Optional<Exhibit> getExhibit(Long id) {
      return exhibitRepository.findById(id);
    }
}
