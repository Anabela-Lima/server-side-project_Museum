package com.example.serversideproject_museum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Museum {

    @Id
    @GeneratedValue(strategy = IDENTITY)


    private Long id;
    private String name;
    private Country country;
    @JsonIgnore
    @OneToMany(mappedBy = "museum", cascade = ALL)
    private Set<Exhibit> exhibits = new HashSet<>();
    //private Set<Staff> staff;



    public Museum(Long id, String name) {
        this.id = id;
        this.name = name;
        this.exhibits = new HashSet<>();
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


    public void addExhibit(Exhibit exhibit) {
        exhibits.add(exhibit);
    }
}
