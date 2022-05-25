package com.example.serversideproject_museum;

import com.example.serversideproject_museum.model.Museum;
import com.example.serversideproject_museum.model.Staff;
import com.example.serversideproject_museum.repository.ExhibitRepository;
import com.example.serversideproject_museum.repository.MuseumRepository;
import com.example.serversideproject_museum.repository.StaffRepository;
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
class StaffControllerTest {

    @Autowired
    // Methods to test the endpoints
    private MockMvc mockMvc;
    //
    @Autowired
    // Methods to map JSON <-> Java
    private ObjectMapper objectMapper;

    @Autowired
    private ExhibitRepository exhibitRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Test
    // Initial Spring context / DB after the test
    @DirtiesContext
    void should_createStaff_and_addUpdateWithExistingExhibit() throws Exception {
        //                          Given
        var baseUrl = "/HireStaff";
        var staffFirstName = "Dave";
        var staffLastName = "Mingchang";
        var staffAge = "234";
        var staffAddress = "19 Forestreet road";
        var staffSalary= "45000";
        var staffListSizeBeforeSave = staffRepository.findAll().size();
        var existingExhibitID = 1L;   // id for existing exhibit (in long format)


        // Save Staff

        //                          When

        MvcResult mvcResultCreate = mockMvc
                // Call the endpoint with HTTP POST
                .perform(MockMvcRequestBuilders.post(baseUrl)
                        // add some params to the request's URL (can be done directly in post method)
                        .param("firstName", staffFirstName)
                        .param("lastName", staffLastName)
                        .param("age", staffAge)
                        .param("address", staffAddress)
                        .param("salary", staffSalary)

                )

                // Add some extra logging
                .andDo(print())
                // Expect HTTP 200
                .andExpect(status().isOk())
                // Return an object to assert the response
                .andReturn();

        // Staff in JSON (String) format
        String createdStaffAsJSON = mvcResultCreate.getResponse().getContentAsString();

        // Convert JSON (String) to Staff object
        Staff createdStaff = objectMapper.readValue(createdStaffAsJSON, Staff.class);

        //                          Then
        var staffListSizeAfterSave = staffRepository.findAll().size();
        assertNotNull(createdStaff);
        assertEquals(0, createdStaff.getExhibits().size());
        assertEquals(staffListSizeBeforeSave + 1, staffListSizeAfterSave);

        // Update Staff

        //                          When

        Staff newStaffFromDB = staffRepository.findAll().get(staffListSizeAfterSave - 1);
        MvcResult mvcResultUpdate = mockMvc
                .perform(put("/staff/" + newStaffFromDB.getId() + "/exhibit/" + existingExhibitID))
                .andExpect(status().isOk())
                .andReturn();

        String updateStaffAsJSON = mvcResultUpdate.getResponse().getContentAsString();

        Staff updatedStaff = objectMapper.readValue(updateStaffAsJSON, Staff.class);

        //                          Then
        assertNotNull(updatedStaff);
        assertEquals(1, updatedStaff.getExhibits().size());

    }

}