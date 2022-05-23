package com.example.serversideproject_museum.repository;

import com.example.serversideproject_museum.model.Exhibit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {
}
