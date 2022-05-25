package com.example.serversideproject_museum.model.dto;

import com.example.serversideproject_museum.model.Exhibit;

import java.time.LocalDate;
import java.util.Set;

public class StaffDto {

    // properties
    private Long id;

    // staff firstname

    private String firstName;

    // staff last name

    private String lastName;

    // staff dob
    private LocalDate dob;

    // staff address

    private String address;

    // staff salary
    private Integer salary;

    // exhibition sets

    private Set<Long> exhibitIds;


    // arg constructor

    public StaffDto(Long id, String firstName, String lastName, LocalDate dob, String address, Integer salary, Set<Long> exhibitIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.salary = salary;
        this.exhibitIds = exhibitIds;
    }


    // no arg constructor

    public StaffDto() {
    }


    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public Set<Long> getExhibitIds() {
        return exhibitIds;
    }

    public void setExhibitIds(Set<Long> exhibitIds) {
        this.exhibitIds = exhibitIds;
    }
}
