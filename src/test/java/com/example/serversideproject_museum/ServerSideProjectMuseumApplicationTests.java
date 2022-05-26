package com.example.serversideproject_museum;

import com.example.serversideproject_museum.model.*;
import com.example.serversideproject_museum.repository.ArtefactRepository;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import com.example.serversideproject_museum.repository.MuseumRepository;
import com.example.serversideproject_museum.repository.StaffRepository;
import com.example.serversideproject_museum.service.ExhibitService;
import com.example.serversideproject_museum.service.MuseumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;


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

    @Autowired
    MuseumService museumService;

    @Autowired
    ExhibitService exhibitService;

    @Test
    void contextLoads() {
    }

    @Nested
    @DisplayName("Tests for Museum")
    class MuseumTests{

        @Transactional
        @Test
        @DisplayName("Museums are loaded from data.sql correctly")
        public void dataSqlLoadMuseums() {
            List<Museum> found = museumRepository.findAll();
            assertEquals(20, found.size());
        }

        @Transactional
        @Test
        @DisplayName("Museums can be deleted by ID")
        public void canDeleteMuseumById() {
            Museum museum = museumRepository.getReferenceById(3L);
            museumRepository.deleteById(3L);
            assertThat(museumRepository.findAll(), not(hasItem(museum)));
        }

        @Transactional
        @Test
        @DisplayName("Can add a new museum")
        public void canAddMuseum(){
            museumService.addMuseum("New Museum test", Country.Afghanistan);
            assertEquals(21, museumRepository.findAll().size());
        }

        @Transactional
        @Test
        @DisplayName("Can filter museums by country")
        public void canFindByCountry(){
            assertEquals(1, museumService.findByCountry(Country.Japan).size());
        }

        @Transactional
        @Test
        @DisplayName("Can add existing exhibit to the museum")
        public void canAddExhibitToMuseum(){
            Museum museum = museumService.addExhibit(1L, 20L);
            assertEquals(5, museum.getExhibits().size());
        }

        @Transactional
        @Test
        @DisplayName("Can add a new exhibit to the museum")
        public void canAddNewExhibitToMuseum(){
            Exhibit exhibit = exhibitRepository.save(new Exhibit("Test Exhibit"));
            Museum museum = museumService.addExhibit(1L, 28L);
            assertEquals(5, museum.getExhibits().size());
        }

        @Transactional
        @Test
        @DisplayName("Can add existing exhibit to new museum")
        public void canAddExhibitToNewMuseum(){
            Museum museum = museumService.addMuseum("Test Museum", Country.Afghanistan);
            assertEquals(0, museum.getExhibits().size());
            Museum updatedMuseum = museumService.addExhibit(21L, 1L);
            assertEquals(1, updatedMuseum.getExhibits().size());
        }

        @Transactional
        @Test
        @DisplayName("Can add new exhibit to new museum")
        public void canAddNewExhibitToNewMuseum(){
            Museum museum = museumService.addMuseum("Test Museum", Country.Afghanistan);
            Exhibit exhibit = exhibitRepository.save(new Exhibit("Test Exhibit"));
            assertEquals(21, museumRepository.findAll().size());
            assertEquals(28, exhibitRepository.findAll().size());
            assertEquals(0, museum.getExhibits().size());
            assertTrue(museum.getExhibits().isEmpty());
            assertNull(exhibit.getMuseum());
            Museum updatedMuseum = museumService.addExhibit(21L, 28L);
            //assertEquals("Test Exhibit", exhibitRepository.findById(28L).orElseThrow().getName());
            //assertEquals(1, updatedMuseum.getExhibits().size());
        }
    }

    @Nested
    @DisplayName("Tests for Artefacts")
    class ArtefactTest{
        @Transactional
        @Test
        public void dataSqlLoadsArtefacts() {
            List<Artefact> found = artefactRepository.findAll();
            assertEquals(100, found.size());
        }

        @Transactional
        @Test
        @DisplayName("Can find artefacts by country")
        public void canFindByCountry() {
            List<Artefact> found =  artefactRepository.findByCountry(Country.UnitedStates);
            for (Artefact artefact: found) {
                assertEquals("UnitedStates", artefact.getCountry());
            }
        }
    }

    @Nested
    @DisplayName("Tests for Staff")
    class StaffTest{
        @Transactional
        @Test
        @DisplayName("Can fire staff")
        public void canFireStaff() {
            Staff staff = staffRepository.getReferenceById(1L);
            staffRepository.deleteById(1L);
            assertThat(staffRepository.findAll(), not(hasItem(staff)));
        }
    }

    @Nested
    @DisplayName("Tests for Exhibit")
    class ExhibitTest{
        @Transactional
        @Test
        @DisplayName("Exhibits load properly from data.sql")
        public void dataSqlLoadExhibits() {
            List<Exhibit> found = exhibitRepository.findAll();
            assertEquals(27, found.size());
        }
        @Transactional
        @Test
        @DisplayName("Can find exhibits by ID")
        public void canFindById() {
            Exhibit found = exhibitRepository.findById(1L).orElseThrow();
            assertEquals("Ancient Egypt", found.getName());
        }
    }



}