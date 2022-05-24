package com.example.serversideproject_museum.service;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

    // Service annotation 
    @Service
    public class ExhibitService {

    
    //  linking Exhibit service to ExhibitRepository
    
    @Autowired
    ExhibitRepository exhibitRepository;


    // Service method- get by id 
    
    public  Optional<Exhibit> getExhibit(Long id) {
      return exhibitRepository.findById(id);
    }

    // service method to get all Exhibition rooms 
    
    public List<Exhibit> getAllExhibit() {return exhibitRepository.findAll();
    }
}
