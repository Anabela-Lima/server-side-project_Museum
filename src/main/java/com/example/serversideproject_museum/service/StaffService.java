package com.example.serversideproject_museum.service;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.model.Staff;
import com.example.serversideproject_museum.model.dto.ArtefactDto;
import com.example.serversideproject_museum.model.dto.StaffDto;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import com.example.serversideproject_museum.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// service annotation
    @Service
    public class StaffService {

    // get All staff

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ExhibitRepository exhibitRepository;



    // staff by id

    public Optional<Staff> getStaff(Long id) {
        return staffRepository.findById(id);
    }


    // DTO methods

        // get all staff with exhibit id

        public List<StaffDto> findByExhibits(Long exhibitId) {
            return staffRepository.findByExhibits(exhibitRepository.findById(exhibitId).get())
                    .stream()
                    .map(staff -> {
                                return new StaffDto(
                                        staff.getId(),
                                        staff.getFirstName(),
                                        staff.getLastName(),
                                        staff.getDob(),
                                        staff.getAddress(),
                                        staff.getSalary(),
                                        staff.getExhibits().stream().map(exhibit-> exhibit.getId()).collect(Collectors.toSet()));
                             }
                    )
                    .toList();
        }




    // get all staff with exhibit id - shows all staff working in a specific exhibit
    public List<StaffDto> getAllStaff() {
        return staffRepository.findAll()
                .stream()
                .map(staff-> {
                            return new StaffDto(
                                    staff.getId(),
                                    staff.getFirstName(),
                                    staff.getLastName(),
                                    staff.getDob(),
                                    staff.getAddress(),
                                    staff.getSalary(),
                                    staff.getExhibits().stream().map(exhibit-> exhibit.getId()).collect(Collectors.toSet()));

                        }
                )
                .toList();
    }



//    // staff alone- show exhibit they are in
//
//    public List<StaffDto> getStaff() {
//        return staffRepository.findAll()
//                .stream()
//                .map(staff-> {
//                            return new StaffDto(
//                                    staff.getId(),
//                                    staff.getFirstName(),
//                                    staff.getLastName(),
//                                    staff.getDob(),
//                                    staff.getAddress(),
//                                    staff.getSalary(), staff.getExhibits().stream().map(exhibit-> exhibit.getId()).collect(Collectors.toSet()));
//                        }
//                )
//                .toList();
//
//    }



















//
//
//    // use  exhibit id to show all staff working there
//
//    public List<StaffDto> findByExhibits(Long exhibitId) {
//        return staffRepository.findByExhibits(exhibitRepository.findById(exhibitId).get())
//                .stream()
//                .map(staff -> {
//                            return new ArtefactDto(staff.getId(),
//                                    staff.getFirstName(),
//                                    staff.getLastName(),
//                                    staff.getDob(),
//                                    staff.getAddress(),
//                                    staff.getSalary(),
//                                    staff.getExhibits().stream().map(exhibit-> exhibit.getId()).collect(Collectors.toSet());
//
//                        }
//                )
//                .toList();
//    }



}
