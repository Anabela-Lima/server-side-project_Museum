package com.example.serversideproject_museum;

import com.example.serversideproject_museum.model.Artefact;
import com.example.serversideproject_museum.model.Exhibit;
import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.model.Staff;
import com.example.serversideproject_museum.repository.ArtefactRepository;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import com.example.serversideproject_museum.repository.MuseumRepository;
import com.example.serversideproject_museum.repository.StaffRepository;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ServerSideProjectMuseumApplicationTests {

    @Autowired
    ArtefactRepository artefactRepository;

    @Autowired
    ExhibitRepository exhibitRepository;

    @Autowired
    MuseumRepository museumRepository;

    @Autowired
    StaffRepository staffRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void dataSqlLoadsArtefacts() {
        List<Artefact> found = artefactRepository.findAll();
        assertEquals(100, found.size());
    }

    @Test
    public void dataSqlLoadExhibits() {
        List<Exhibit> found = exhibitRepository.findAll();
        assertEquals(27, found.size());
    }

    @Test
    public void dataSqlLoadMuseums() {
        List<Museum> found = museumRepository.findAll();
        assertEquals(20, found.size());
    }

    @Test
    public void canFindByCountry() {
        List<Artefact> found =  artefactRepository.findByCountry("UnitedStates");
        for (Artefact artefact: found) {
            assertEquals("UnitedStates", artefact.getCountry());
        }
    }

    @Test
    public void canDeleteMuseumById() {
        Museum museum = museumRepository.getReferenceById(3L);
        museumRepository.deleteById(3L);
        assertThat(museumRepository.findAll(), not(hasItem(museum)));
    }

    @Test
    public void canFireStaff() {
        Staff staff = staffRepository.getReferenceById(1L);
        staffRepository.deleteById(1L);
        assertThat(staffRepository.findAll(), not(hasItem(staff)));
    }

    @Test
    public void canFindById() {
        Exhibit found = exhibitRepository.findById(1L).orElseThrow();
        assertEquals("Ancient Egypt", found.getName());
    }
}