package ci.jubile.joc.opusprofilemanager.integrationtest.v1.service;

import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import ci.jubile.joc.opusprofilemanager.v1.service.ProfileServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class ProfileServiceImplIntegrationTest {

    @Autowired
    private ProfileServiceImpl profileService;

    @Test
    @DisplayName(value = "findAll() : return all profile if they are")
    void givenNothing_whenProfilesExist_thenFindAll(){
        List<ProfileResource> profileResourceList = profileService.findAll();
        Assert.notEmpty(profileResourceList, "There is no data in the document");
    }

}
