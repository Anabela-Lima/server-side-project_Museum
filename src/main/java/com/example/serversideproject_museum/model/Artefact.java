package com.example.serversideproject_museum.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "artefacts")
public class Artefact {
// artefact
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String creator;
    @Column
    private Date date;
    @Column
    private String country;
    @ManyToOne
    @JoinColumn(name = "exhibit_id", nullable = false)
    private Exhibit exhibits;

    public Artefact(Long id, String name, String creator, Date date, String country, Exhibit exhibits) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.date = date;
        this.country = country;
        this.exhibits = exhibits;
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

    public Exhibit getExhibits() {
        return exhibits;
    }

    public void setExhibits(Exhibit exhibits) {
        this.exhibits = exhibits;
    }
}
