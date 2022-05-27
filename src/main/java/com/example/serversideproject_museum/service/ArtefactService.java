package com.example.serversideproject_museum.service;

import com.example.serversideproject_museum.model.Artefact;

import com.example.serversideproject_museum.model.Country;
import com.example.serversideproject_museum.model.dto.ArtefactDto;
import com.example.serversideproject_museum.repository.ArtefactRepository;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// Service annotation
@Service
public class ArtefactService {

    @Autowired
    ArtefactRepository artefactRepository;

    @Autowired
    ExhibitRepository exhibitRepository;


    // get all Artefacts with exhibit id showing in the body - using ArtefactsDto
    public List<ArtefactDto> getAllArtefact() {
        return artefactRepository.findAll()
                .stream()
                .map(artefact -> {
                            return new ArtefactDto(
                                    artefact.getId(),
                                    artefact.getName(),
                                    artefact.getCreator(),
                                    artefact.getDate(),
                                    artefact.getCountry(),
                                    artefact.getExhibits().getId());
                        }
                )
                .toList();
    }

    // get all artefacts by exhibit id - using ArtefactsDto
    public List<ArtefactDto> findByExhibits(Long exhibitId) {
        return artefactRepository.findByExhibits(exhibitRepository.findById(exhibitId).get())
                .stream()
                .map(artefact -> {
                            return new ArtefactDto(
                                    artefact.getId(),
                                    artefact.getName(),
                                    artefact.getCreator(),
                                    artefact.getDate(),
                                    artefact.getCountry(),
                                    artefact.getExhibits().getId());
                        }
                )
                .toList();
    }

    // Service method - get all Artefacts by country - using ArtefactsDto

    public List<ArtefactDto> findByCountryDto(Country country) {
        return artefactRepository.findByCountry(country)
                .stream()
                .map(artefact -> {
                    return new ArtefactDto(
                            artefact.getId(),
                            artefact.getName(),
                            artefact.getCreator(),
                            artefact.getDate(),
                            artefact.getCountry(),
                            artefact.getExhibits().getId());}
                )
                .toList();
    }

    // Service method - delete all Artefacts

    public void deleteById(Long id) {
        artefactRepository.deleteById(id);
    }

    // Add Artefact
    public Artefact addArtefact(String name, String creator, LocalDate date, Country country) {
        return artefactRepository.save(new Artefact(name, creator, date, country));
    }

}
