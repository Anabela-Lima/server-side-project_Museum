package com.example.serversideproject_museum.controller;

import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.model.Staff;
import com.example.serversideproject_museum.repository.StaffRepository;
import com.example.serversideproject_museum.service.ExhibitService;
import com.example.serversideproject_museum.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


// Ana started working commit

@RestController
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffService staffService;
    @Autowired

    private ExhibitService exhibitService;


    // GetAll

    @GetMapping(path = "/staff")
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staff = staffService.getAllStaff();
        return ResponseEntity.ok().body(staff);
    }


    // Get a staff

    @GetMapping(path = "/Staff/{id}")
    public ResponseEntity<Staff> getStaff(@PathVariable Long id) {
        Optional<Staff> staffOptional = staffService.getStaff(id);

        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            return ResponseEntity.ok().body(staff);
        }
        System.out.println("Employee is not present!");
        return ResponseEntity.notFound().build();
    }


    // Post for staff

    @PostMapping("/HireStaff")
    public ResponseEntity<Staff> hireStaff(@RequestParam String firstName,
                                           @RequestParam String lastName,
                                           @RequestParam int age,
                                           @RequestParam String address,
                                           @RequestParam Integer salary ) {
        LocalDate dob = LocalDate.now().minusYears(age);

        staffRepository.save(new Staff(firstName,lastName,dob, address,salary));
        System.out.println("Employee " + firstName + "has been hired!");
        return ResponseEntity.ok().build();
    }

    // putMapping

    @Transactional
    @PutMapping("/staff/{staff_id}/exhibit/{exhibit_id}")
    public ResponseEntity<Exhibit>addExhibitID(@PathVariable Long staff_id, @PathVariable Long exhibit_id){

        Staff staff = staffService.getStaff(staff_id).orElseThrow();
        Exhibit exhibit = exhibitService.getExhibit(exhibit_id);
        staff.addExhibit(exhibit);
        return ResponseEntity.ok().build();
    }


   // Fire staff method [Delete]

    @DeleteMapping("/fireStaff/{id}")
    public ResponseEntity<String> fireStaff(@PathVariable Long id) {

        List<Long> staffIds = staffService.getAllStaff().stream().map(Staff::getId)
                .filter(f -> f.equals(id)).toList();
        if (!staffIds.isEmpty())
        {ResponseEntity<String> OUT = ResponseEntity.ok("Staff" + staffService
                .getStaff(id)
                .stream()
                .map(Staff::getFirstName).toList()
                + " has been fired from the Museum and is no longer in our records.") ;
            staffRepository.deleteById(id);
            return OUT;}
        return ResponseEntity.badRequest().build();
    }



//
//    @DeleteMapping("/fireStaff/{id}")
//    public void fireStaff(@PathVariable Long id) {
//        staffRepository.deleteById(id);
//    }








}
