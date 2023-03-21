package ci.jubile.joc.opusprofilemanager.unittest.v1.controller;

import ci.jubile.joc.opusprofilemanager.v1.controller.FormationController;
import ci.jubile.joc.opusprofilemanager.v1.mapper.FormationMapperImpl;
import ci.jubile.joc.opusprofilemanager.v1.service.FormationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@WebMvcTest(FormationController.class)
@ExtendWith(SpringExtension.class)
public class FormationControllerTest {
    @MockBean
    private FormationServiceImpl formationService;

    private final FormationMapper formationMapper;

    @Test
    void listAllTest(){

    }
}
