package com.example.serversideproject_museum.repository;

import com.example.serversideproject_museum.model.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuseumRepository extends JpaRepository<Museum, Long> {



}
