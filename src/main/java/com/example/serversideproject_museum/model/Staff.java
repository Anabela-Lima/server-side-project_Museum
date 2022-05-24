package com.example.serversideproject_museum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "staff")
public class Staff {


// properties

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Staff_generator")
    @SequenceGenerator(name = "Staff_generator", sequenceName = "staff_sequence", allocationSize= 1, initialValue = 51)
    @Column
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private LocalDate dob;
    @Column
    private String address;
    @Column
    private Integer salary;


    @ManyToMany(mappedBy = "staff", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "staff")
    private Set<Exhibit> exhibits;


// constructors (2)


// No arg constructor

public Staff(){};

// arg constructor

    public Staff(String firsName, String lastName, LocalDate dob, String address, Integer salary) {
        this.id = id;
        this.firstName = firsName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.salary = salary;
        this.exhibits = new HashSet<>();

    }


    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirsName() {
        return firstName;
    }

    public void setFirsName(String firsName) {
        this.firstName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
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


    // add exhibit to staff method
    public void addExhibit(Exhibit exhibit) {
        this.exhibits.add(exhibit);
    }


    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firsName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", exhibits=" + exhibits +
                '}';
    }
}
