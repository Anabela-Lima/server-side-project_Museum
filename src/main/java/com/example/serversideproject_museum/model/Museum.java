package com.example.serversideproject_museum.model;

import java.util.HashSet;
import java.util.Set;

public class Museum {

    private Long id;
    private String name;
    private Set<Exhibit> exhibits = new HashSet<>();
    //private Set<Staff> staff;


    public Museum(Long id, String name, Set<Exhibit> exhibits) {
        this.id = id;
        this.name = name;
        this.exhibits = exhibits;
    }

    public Museum(String name) {
        this.name = name;
    }

    public Museum() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Exhibit> getExhibits() {
        return exhibits;
    }

    public void setExhibits(Set<Exhibit> exhibits) {
        this.exhibits = exhibits;
    }
}
