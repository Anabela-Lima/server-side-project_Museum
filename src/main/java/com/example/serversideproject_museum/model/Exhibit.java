package com.example.serversideproject_museum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.websocket.OnError;
import java.util.Set;

@Entity

@Table(name = "exhibits")
public class Exhibit {

  //  Exhibit started Ana

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String name;

    @OneToMany(mappedBy = "exhibits", cascade = CascadeType.ALL)    //- relationship between exhibit and artefacts - one exhit has many artifcats
    private Set<Artefact> artefacts;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "assignments",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "exhibit_id")
    )

    @JsonIgnoreProperties(value = "exhibits")
    private Set <Staff> staff;

    @ManyToOne   // relationship between Exhibits and Museum - many exhibits in 1 museum
    private Museum museum;


    // constructors (2)


    // no arg constructor
    public Exhibit(){};

    // arg constructor

    public Exhibit(Long id, String name) {
        this.id = id;
        this.name = name;
        this.artefacts = artefacts;
        this.staff= staff;
        this.museum= museum;

    }





    // getters + setters


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

    public Set<Artefact> getArtefacts() {
        return artefacts;
    }

    public void setArtefacts(Set<Artefact> artefacts) {
        this.artefacts = artefacts;
    }


}
