package com.example.serversideproject_museum.service;

import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.model.Staff;
import com.example.serversideproject_museum.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

    // service annotation 
    @Service
    public class StaffService {

    // get All staff

    @Autowired
    StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // staff by id

    public Optional<Staff> getStaff(Long id) {
        return staffRepository.findById(id);
    }

}
