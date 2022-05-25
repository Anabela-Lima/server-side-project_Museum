package com.example.serversideproject_museum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

/*

UML Diagram: Museum 
 +------------------------------------------+
 |         Museum                           |
 +------------------------------------------+
 |   id                  :  Long            |
 |   name                :  String          |
 |   country             :  Country         |
 |   exhibits            :  Set<Exhibit>    |
 +------------------------------------------+
 |   Constructors                           |
 |   Getters & Setters                      |
 +------------------------------------------+
 |   addExhibit(Exhibit) :  void            |
 +------------------------------------------+

 */

@Table
@Entity
public class Museum {

    //   Museum properties

    // Primary Key - Long - id   -- Autogenerated by Spring, initial value of 21 as we create 20 Museum objects in data.sql
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "museum_sequence_generator")
    @SequenceGenerator(name = "museum_sequence_generator", allocationSize = 1, initialValue = 21)
    @Column
    private Long id;

    // String - name - Name of the Museum object
    @Column
    private String name;

    // Country - country - country of the museum object, one of an enum value, as defined in model.Country
    @Column
    @Enumerated(EnumType.STRING)
    private Country country;

    /* Set<Exhibit> - exhibits
    *          - Set of exhibits within the museum, initialised as an empty HashSet
    *          - Ignored in the JSON to avoid infinite recursion error
    *          - One-to-Many relationship with Exhibits table, mapped by its museum property
    *
    * Relationship - One Museum has many Exhibits
     */
    @JsonIgnoreProperties(value = "museum")
    @OneToMany(mappedBy = "museum", cascade = ALL)
    private Set<Exhibit> exhibits = new HashSet<>();


    //   Constructors

    /*  Basic Constructor
     *   @Param name - Name of the new museum to be instantiated
     *   @Param country - Country the museum will be in, of type Country enum
     */
    public Museum(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    // No arg constructor
    public Museum() {
    }


    //   Getters & Setters

    //Note - Do not include getter/setter for id property as this is created for us

    //For name variable:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //For exhibit set:
    public Set<Exhibit> getExhibits() {
        return exhibits;
    }
    public void setExhibits(Set<Exhibit> exhibits) {
        this.exhibits = exhibits;
    }

    //For country:
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }
    //   Other methods

    /* addExhibit
    *      Adds an exhibit to the existing set of exhibits housed in the museum
    *
    *  @Param Exhibit - Exhibit to add to current Set of exhibits
    *
    */
    public void addExhibit(Exhibit exhibit) {
        exhibits.add(exhibit);
    }

}
