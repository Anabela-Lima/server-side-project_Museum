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




    // get all Artefacts with exhibit id
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
//
////    public List<Artefact> findByCountry(String country) {
////        return artefactRepository.findByCountry(country);
////    }
//        // Service method - get all Artefacts by country
//
//        public List<ArtefactDto> findByCountryDto(String country) {
//            return artefactRepository.findByCountry(country)
//                    .stream()
//                    .map(artefact -> {
//                        return new ArtefactDto(
//                                artefact.getId(),
//                                artefact.getName(),
//                                artefact.getCreator(),
//                                artefact.getDate(),
//                                artefact.getCountry(),
//                                artefact.getExhibits().getId());}
//                    )
//                    .toList();
//        }
}
