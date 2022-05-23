package com.example.serversideproject_museum.repository;

import com.example.serversideproject_museum.model.Exhibit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExhibitRepository extends JpaRepository<Exhibit, Long> {
    //Optional<Object> findbyId(Long id);
}
