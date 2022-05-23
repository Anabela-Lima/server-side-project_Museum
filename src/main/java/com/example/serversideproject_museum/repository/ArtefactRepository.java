package com.example.serversideproject_museum.repository;

import com.example.serversideproject_museum.model.Artefact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtefactRepository extends JpaRepository<Artefact, Long> {
}
