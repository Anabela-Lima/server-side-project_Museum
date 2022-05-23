package com.example.serversideproject_museum.repository;

import com.example.serversideproject_museum.model.Exhibit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {
    //Optional<Object> findbyId(Long id);
}
