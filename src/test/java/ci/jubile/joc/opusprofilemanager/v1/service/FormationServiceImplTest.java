package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.model.Domain;
import ci.jubile.joc.opusprofilemanager.model.Formation;
import ci.jubile.joc.opusprofilemanager.model.Profile;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Predicate;

@ExtendWith(MockitoExtension.class)
class FormationServiceImplTest {

    private static final String PROFILE_ID = UUID.randomUUID().toString();
    private static final String FORMATION_ID = UUID.randomUUID().toString();

    @Mock
    ProfileServiceImpl profileService;
    @InjectMocks
    FormationServiceImpl formationService;

    @Test
    @DisplayName(value = "addFormation() : check if formation is added for profile")
    void givenProfileIdAndFormation_whenAddFormation_saveAndReturnFormation() throws ProfileNotFoundException {
        Mockito.when(profileService.findById(Mockito.anyString())).thenReturn(new Profile());
        Mockito.when(profileService.update(Mockito.any(Profile.class))).thenReturn(new Profile());

        Formation addedFormation = formationService.add(PROFILE_ID, buildFormation());

        Assertions.assertNotNull(addedFormation.getId(), "Formation's ID not set");
        Assertions.assertNotNull(addedFormation.getCreatedAt(), "Formation's CreationDate not set");
        Mockito.verify(profileService, Mockito.times(1)).update(Mockito.any(Profile.class));
    }

    @Test
    @DisplayName(value = "addFormation() : throw exception if profile notfound")
    void givenProfileIdAndFormation_whenProfileNotFound_throwNoSuchElementException() throws ProfileNotFoundException {
        Mockito.when(profileService.findById(Mockito.anyString())).thenThrow(ProfileNotFoundException.class);

        Assertions.assertThrows(NoSuchElementException.class, () -> formationService.add(PROFILE_ID, buildFormation()));
        Mockito.verify(profileService, Mockito.times(1)).findById(PROFILE_ID);
    }

    @Test
    @DisplayName(value = "updateFormation() : check if formation is updated for profile")
    void updateFormation() throws ProfileNotFoundException {
        Profile profile = buildProfile();
        Formation formation = buildFormation();
        formation.setId(FORMATION_ID);
        formation.setSchool("UQAM");
        Mockito.when(profileService.findById(PROFILE_ID)).thenReturn(profile);

        Formation updatedFormation = formationService.update(PROFILE_ID, formation);

        Assertions.assertNotNull(updatedFormation);
        Assertions.assertEquals(updatedFormation.getId(), formation.getId(), "Old and New Formation have deiff ID.");
        Assertions.assertNotNull(updatedFormation.getUpdatedAt(), "Formation UpdatedDate is Null.");
        Assertions.assertEquals(updatedFormation.getSchool(), "UQAM", "Formation school value hasn't changed form HETCH to UQAM.");
        Mockito.verify(profileService, Mockito.times(1)).update(profile);
    }

    @Test
    @DisplayName(value = "deleteFormation() : Test deletion")
    void deleteFormation(){
        Predicate<Formation> predicate = f -> f.getId().equals(FORMATION_ID);
        Profile profile = buildProfile();

        profile.getFormations().removeIf(predicate);

        Assertions.assertEquals(0, profile.getFormations().size());
    }

    @Test
    @DisplayName(value = "listFormation() : Test deletion")
    void findAllFormation() throws ProfileNotFoundException {
        Profile profile = buildProfile();
        Mockito.when(profileService.findById(PROFILE_ID)).thenReturn(profile);

        List<Formation> formationList = formationService.findAll(PROFILE_ID);

        Assertions.assertEquals(1, formationList.size());
    }

    private Formation buildFormation(){
        Domain domain = new Domain("D01", "Information Technologie");
        return new Formation("MASTER ID", "HETECH", 2022, domain);
    }

    private Profile buildProfile(){
        Profile profile = new Profile();
        Formation formation = buildFormation();
        formation.setId(FORMATION_ID);
        formation.setCreatedAt(LocalDateTime.now());

        profile.setId(PROFILE_ID);
        profile.getFormations().add(formation);
        return profile;
    }

}