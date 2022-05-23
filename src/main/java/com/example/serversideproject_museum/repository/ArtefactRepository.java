package com.example.serversideproject_museum.repository;

import com.example.serversideproject_museum.model.Artefact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ArtefactRepository extends JpaRepository<Artefact, Long> {
}
