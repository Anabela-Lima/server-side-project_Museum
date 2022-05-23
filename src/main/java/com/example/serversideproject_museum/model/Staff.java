package com.example.serversideproject_museum.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@Entity
@Table (name = "staff")
public class Staff {


// properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String firsName;
    @Column
    private String lastName;
    @Column
    private Date dob;
    @Column
    private String address;
    @Column
    private Integer salary;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "assignments",
            joinColumns = @JoinColumn(staff_id),
            inverseJoinColumns = @JoinColumn(name = exhibit_id)
    )
    private Set<Exhibit> exhibits;


// constructors (2)


// No arg constructor

public Staff(){};

// arg constructor

    public Staff(String firsName, String lastName, Date dob, String address, Integer salary, Set<Exhibit> exhibits) {
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.salary = salary;
        this.exhibits = exhibits;
    }


    // getters and setters


    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<Exhibit> getExhibits() {
        return exhibits;
    }

    public void setExhibits(Set<Exhibit> exhibits) {
        this.exhibits = exhibits;
    }
}
