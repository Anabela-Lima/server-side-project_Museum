package com.example.serversideproject_museum;

import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import com.example.serversideproject_museum.repository.MuseumRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.serversideproject_museum.model.Country.UnitedStates;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MuseumControllerTest {

    @Autowired
    // Methods to test the endpoints
    private MockMvc mockMvc;

    @Autowired
    // Methods to map JSON <-> Java
    private ObjectMapper objectMapper;

    @Autowired
    private ExhibitRepository exhibitRepository;

    @Autowired
    private MuseumRepository museumRepository;

    @Test
    // Initial Spring context / DB after the test
    @DirtiesContext
    void should_createMuseum_and_addUpdateWithExistingExhibit() throws Exception {
        //                          Given
        var baseUrl = "/museum";
        var museumName = "museum";
        var museumCountry = UnitedStates;
        var museumListSizeBeforeSave = museumRepository.findAll().size();
        var existingExhibitID = 1L;


        // Save Museum

        //                          When

        MvcResult mvcResultCreate = mockMvc
                // Call the endpoint with HTTP POST
                .perform(MockMvcRequestBuilders.post(baseUrl + "/create/")
                        // add some params to the request's URL (can be done directly in post method)
                        .param("name", museumName)
                        .param("country", museumCountry.toString())
                )
                // Add some extra logging
                .andDo(print())
                // Expect HTTP 200
                .andExpect(status().isOk())
                // Return an object to assert the response
                .andReturn();

        // Museum in JSON (String) format
        String createdMuseumAsJSON = mvcResultCreate.getResponse().getContentAsString();

        // Convert JSON (String) to Museum object
        Museum createdMuseum = objectMapper.readValue(createdMuseumAsJSON, Museum.class);

        //                          Then
        var museumListSizeAfterSave = museumRepository.findAll().size();
        assertNotNull(createdMuseum);
        assertEquals(0, createdMuseum.getExhibits().size());
        assertEquals(museumListSizeBeforeSave + 1, museumListSizeAfterSave);

        // Update Museum

        //                          When

        Museum newMuseumFromDB = museumRepository.findAll().get(museumListSizeAfterSave - 1);
        MvcResult mvcResultUpdate = mockMvc
                .perform(put("/museum/" + newMuseumFromDB.getId() + "/exhibit/" + existingExhibitID))
                .andExpect(status().isOk())
                .andReturn();

        String updatedMuseumAsJSON = mvcResultUpdate.getResponse().getContentAsString();

        Museum updatedMuseum = objectMapper.readValue(updatedMuseumAsJSON, Museum.class);

        //                          Then
        assertNotNull(updatedMuseum);
        assertEquals(1, updatedMuseum.getExhibits().size());

    }

}