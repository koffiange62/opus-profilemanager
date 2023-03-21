package ci.jubile.joc.opusprofilemanager.unittest.v1.controller;

import ci.jubile.joc.opusprofilemanager.unittest.v1.utils.ProfileTestUtils;
import ci.jubile.joc.opusprofilemanager.v1.controller.PasswordController;
import ci.jubile.joc.opusprofilemanager.v1.resource.PasswordResource;
import ci.jubile.joc.opusprofilemanager.v1.service.PasswordServiceImpl;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PasswordController.class)
@ExtendWith(SpringExtension.class)
public class PasswordControllerTest {
    private final String BASE_URI = "http://localhos:8080/v1/password";
    private final String OLD_PASSWORD = "old_password";
    private final String NEW_PASSWORD = "new_password";
    private static ObjectMapper objectMapper;

    @MockBean
    private PasswordServiceImpl passwordService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void initTest(){
        objectMapper = new ObjectMapper();
    }

    @Test
    void editPasswordTest() throws Exception {
        PasswordResource passwordResource = PasswordResource.builder()
                .currentPassword(OLD_PASSWORD).newPassword(NEW_PASSWORD).build();
        Mockito.doNothing().when(passwordService).updatePassword(ProfileTestUtils.PROFILE_ID, NEW_PASSWORD);
        mockMvc.perform(put(BASE_URI+"/edit").contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(passwordResource)))
                .andExpect(status().isOk());
    }
}
