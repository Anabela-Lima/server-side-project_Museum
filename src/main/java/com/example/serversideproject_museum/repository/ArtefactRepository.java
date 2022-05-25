package com.example.serversideproject_museum.repository;

import com.example.serversideproject_museum.model.Artefact;
import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.dto.ArtefactDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository

public interface ArtefactRepository extends JpaRepository<Artefact, Long> {
    List<Artefact> findByCountry(String country);

    List<Artefact> findByExhibits(Exhibit exhibits);
}
