package com.example.serversideproject_museum;

import java.util.Date;

public class Artefact {

    private Long id;
    private String name;
    private String creator;
    private Date date;
    private String country;

    public Artefact(Long id, String name, String creator, Date date, String country) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.date = date;
        this.country = country;
    }

    public Artefact() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
