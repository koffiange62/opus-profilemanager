package ci.jubile.joc.opusprofilemanager.v1.controller;

import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import ci.jubile.joc.opusprofilemanager.v1.utils.ProfileTestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileControllerIntTest {

    private static final String BASE_URI = "http://localhost:8080/v1/profiles";
    private static final String PROFILE_EMAIL = "koffiange@siracide.com";

    private static ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeAll
    public static void init(){
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @DisplayName(value = "Profile createProfile() methode Integration Test.")
    @Order(0)
    void createProfile() throws Exception {
        URI uri = URI.create(BASE_URI + "/profile");
        ProfileResource profileResource = ProfileTestUtils.profileResourceBuilder();
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(profileResource)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.email").value(PROFILE_EMAIL));
    }

    @Test
    @DisplayName(value = "Profile findAllProfile() methode Integration Test.")
    @Order(1)
    void findAllProfile() throws Exception {
        URI uri = URI.create(BASE_URI);
        mockMvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(ProfileTestUtils.PROFILE_ID));
    }

    @Test
    @DisplayName(value = "Profile findProfileById() methode Integration Test.")
    @Order(2)
    void findProfileById() throws Exception {
        URI uri = URI.create(BASE_URI + "/profile/" + ProfileTestUtils.PROFILE_ID);
        mockMvc.perform(get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.email").value(PROFILE_EMAIL));
    }

    @Test
    @DisplayName(value = "Profile updateProfile() methode Integration Test.")
    @Order(3)
    void updateProfile() throws Exception {
        URI uri = URI.create(BASE_URI + "/profile");
        ProfileResource profileResource = ProfileTestUtils.profileResourceBuilder();
        profileResource.setCity("Wakanda");
        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(profileResource)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.city").value("Wakanda"));
    }

    @Test
    @DisplayName(value = "Profile changeProfileEnablingStatus() methode Integration Test.")
    @Order(4)
    void changeProfileEnablingStatus() throws Exception {
        URI uri = URI.create(BASE_URI + "/profile/enabling-or-desabling/" + ProfileTestUtils.PROFILE_ID);
        mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                        .param("status", ProfileStatus.ENABLE.toString()))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName(value = "Profile deleteProfile() methode Integration Test.")
    @Order(5)
    void deleteProfile() throws Exception {
        URI uri = URI.create(BASE_URI + "/profile/" + ProfileTestUtils.PROFILE_ID);
        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent());
    }
}
