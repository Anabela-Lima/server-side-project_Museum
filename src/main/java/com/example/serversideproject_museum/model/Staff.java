package com.example.serversideproject_museum.model;

import java.util.Date;

public class Staff {



// properties

    private Long id;
    private String name;
    private Date dob;
    private String address;
    private Integer salary;

// constructors (2)


// No arg constructor

public Staff(){};

// arg constructor


    public Staff(Long id, String name, Date dob, String address, Integer salary) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.salary = salary;
    }



    // getters and setters


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
}
