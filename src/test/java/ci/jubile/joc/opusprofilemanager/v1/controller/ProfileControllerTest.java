package ci.jubile.joc.opusprofilemanager.v1.controller;

import ci.jubile.joc.opusprofilemanager.model.Profile;
import ci.jubile.joc.opusprofilemanager.v1.utils.ProfileTestUtils;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.mapper.ProfileMapper;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import ci.jubile.joc.opusprofilemanager.v1.service.PasswordServiceImpl;
import ci.jubile.joc.opusprofilemanager.v1.service.ProfileServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
@ExtendWith(SpringExtension.class)
class ProfileControllerTest {

    private final String BASE_URI = "http://localhos:8080/v1/profiles";

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void initTest(){
        objectMapper = new ObjectMapper();
    }

    @MockBean
    private ProfileServiceImpl profileService;

    @MockBean
    private ProfileMapper profileMapper;

    @MockBean
    private PasswordServiceImpl passwordService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void findAllTest() throws Exception {
        Profile profile = ProfileTestUtils.profileBuilder();
        List<Profile> profileList = List.of(profile);
        Mockito.when(profileService.findAll()).thenReturn(profileList);
        Mockito.when(profileMapper.profileToProfileResource(Mockito.any(Profile.class))).thenReturn(ProfileTestUtils.profileResourceBuilder());

        mockMvc.perform(get(BASE_URI).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(ProfileTestUtils.PROFILE_ID));
    }

    @Test
    void findByIdTest() throws Exception {
        Mockito.when(profileService.findById(Mockito.anyString())).thenReturn(ProfileTestUtils.profileBuilder());
        Mockito.when(profileMapper.profileToProfileResource(Mockito.any(Profile.class)))
                .thenReturn(ProfileTestUtils.profileResourceBuilder());
        URI uri = URI.create(BASE_URI+"/profile/"+ ProfileTestUtils.PROFILE_ID);

        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(ProfileTestUtils.PROFILE_ID));
    }

    @Test
    void createTest() throws Exception {
        ProfileResource profileResource = ProfileTestUtils.profileResourceBuilder();
        Mockito.when(profileMapper.profileResourceToProfile(Mockito.any(ProfileResource.class)))
                .thenReturn(ProfileTestUtils.profileBuilder());
        Mockito.when(profileService.create(Mockito.any(Profile.class))).thenReturn(ProfileTestUtils.profileBuilder());
        Mockito.doNothing().when(passwordService).create(Mockito.anyString(), Mockito.anyString());
        URI uri = URI.create(BASE_URI+"/profile");

        mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(profileResource)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateTest() throws Exception {
        ProfileResource profileResource = ProfileTestUtils.profileResourceBuilder();
        Profile profile = ProfileTestUtils.profileBuilder();
        Mockito.when(profileMapper.profileResourceToProfile(Mockito.any(ProfileResource.class)))
                .thenReturn(ProfileTestUtils.profileBuilder());
        Mockito.when(profileService.update(Mockito.any(Profile.class))).thenReturn(profile);
        Mockito.when(profileMapper.profileToProfileResource(Mockito.any(Profile.class)))
                .thenReturn(profileResource);
        URI uri = URI.create(BASE_URI+"/profile");

        mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(profileResource)))
                .andExpect(status().isOk());
    }

    @Test
    void changeProfileEnablingStatusTest() throws Exception {
        Mockito.doNothing().when(profileService).enableOrDisableProfile(ProfileTestUtils.PROFILE_ID, ProfileStatus.ENABLE);
        URI uri = URI.create(BASE_URI+"/profile/enabling-or-desabling/"+ ProfileTestUtils.PROFILE_ID);
        mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON)
                        .param("status", ProfileStatus.ENABLE.toString()))
                .andExpect(status().isNoContent());
    }
}