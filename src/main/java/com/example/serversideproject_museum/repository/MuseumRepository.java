package com.example.serversideproject_museum.repository;

import com.example.serversideproject_museum.model.Country;
import com.example.serversideproject_museum.model.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuseumRepository extends JpaRepository<Museum, Long> {


    List<Museum> findByCountry(Country country);
}
