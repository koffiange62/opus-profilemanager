package ci.jubile.joc.opusprofilemanager.v1.controller;

import ci.jubile.joc.opusprofilemanager.v1.utils.FormationTestUtils;
import ci.jubile.joc.opusprofilemanager.v1.utils.ProfileTestUtils;
import ci.jubile.joc.opusprofilemanager.v1.mapper.FormationMapper;
import ci.jubile.joc.opusprofilemanager.v1.service.FormationServiceImpl;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FormationController.class)
@ExtendWith(SpringExtension.class)
public class FormationControllerTest {

    private final String BASE_URI = "http://localhost:8080/v1/formations";

    @MockBean
    private FormationServiceImpl formationService;

    @MockBean
    private FormationMapper formationMapper;

    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void init(){
        objectMapper = new ObjectMapper();
    }

    @Test
    void listAllTest() throws Exception {
        Mockito.when(formationService.findAll(Mockito.anyString())).thenReturn(FormationTestUtils.formationListBuilder());
        Mockito.when(formationMapper.formationListToResourceList(Mockito.anyList())).thenReturn(FormationTestUtils.formationResourceListBuilder());
        URI uri = URI.create(BASE_URI + "/formation/profile/" + ProfileTestUtils.PROFILE_ID);

        mockMvc.perform(get(uri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].diploma").value("Ingenieur Mecanique"));
    }

    @Test
    void addTest() throws Exception {
        this.commonArrangeTestStep();
        URI uri = URI.create(BASE_URI + "/formation/profile/" + ProfileTestUtils.PROFILE_ID);

        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(FormationTestUtils.formationResourceBuilder())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.diploma").value("Ingenieur Mecanique"))
                .andExpect(jsonPath("$.school").value("Harvard"));
    }


    @Test
    void updateFormationTest() throws Exception {
        this.commonArrangeTestStep();
        URI uri = URI.create(BASE_URI + "/formation/profile/" + ProfileTestUtils.PROFILE_ID);

        mockMvc.perform(put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(FormationTestUtils.formationResourceBuilder())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.diploma").value("Ingenieur Mecanique"))
                .andExpect(jsonPath("$.school").value("Harvard"));
    }

    @Test
    void deleteTest() throws Exception {
        Mockito.doNothing().when(formationService).delete(ProfileTestUtils.PROFILE_ID, FormationTestUtils.FORMATION_ID);
        URI uri = URI.create(BASE_URI + "/formation");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("profileId", List.of(ProfileTestUtils.PROFILE_ID));
        map.put("formationId", List.of(FormationTestUtils.FORMATION_ID));
        mockMvc.perform(delete(uri).queryParams(map))
                .andExpect(status().isNoContent());
    }

    private void commonArrangeTestStep(){
        Mockito.when(formationMapper.formationResourceToFormation(Mockito.any()))
                .thenReturn(FormationTestUtils.formationBuilder());
        Mockito.when(formationService.update(ProfileTestUtils.PROFILE_ID, FormationTestUtils.formationBuilder()))
                .thenReturn(FormationTestUtils.formationBuilder());
        Mockito.when(formationMapper.formationToFormationResource(Mockito.any()))
                .thenReturn(FormationTestUtils.formationResourceBuilder());
    }
}
