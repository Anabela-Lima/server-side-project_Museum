package com.example.serversideproject_museum.model.dto;

import javax.persistence.Column;
import java.time.LocalDate;

public class ArtefactDto {

    private Long id;

    // String - name - Name of the artefact

    private String name;

    // String - creator - Creator of the artefact

    private String creator;

    // Date - date - Date the artefact was created/discovered

    private LocalDate date;

    // String - country - Country of origin of the artefact

    private String country;


    public ArtefactDto(Long id, String name, String creator, LocalDate date, String country) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.date = date;
        this.country = country;
    }

    public ArtefactDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
