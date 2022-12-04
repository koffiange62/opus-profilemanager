package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.mapper.ProfileMapper;
import ci.jubile.joc.opusprofilemanager.v1.repository.ProfileRepository;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProfileServiceImplTest {

    private static final String ID = "gqgjgdf";

    @MockBean
    static
    ProfileRepository profileRepository;
    @MockBean
    static
    ProfileMapper profileMapper;

    ProfileServiceImpl profileService;

    @Test
    void givenProfile_whenNewProfile_thenCreateProfile() {
        ProfileResource profileResource = this.profileResourceBuilder();
        Profile profile = this.profileBuilder();
        Mockito.when(profileMapper.profileResourceToProfile(Mockito.any(ProfileResource.class))).thenReturn(profile);
        Mockito.when(profileRepository.insert(Mockito.any(Profile.class))).thenReturn(profile);
        Mockito.when(profileMapper.profileToProfileResource(Mockito.any(Profile.class))).thenReturn(profileResource);

        profileService = new ProfileServiceImpl(profileRepository, profileMapper);
        profileResource = profileService.create(profileResource);

        Assert.notNull(profileResource, "Profile non cree ");
        Assert.isInstanceOf(ProfileResource.class, profileResource);
    }


    @Test
    void givenProfile_whenProfileEmailAlreadyExist_thenThrowIllegalArgumentException(){
        ProfileResource profileResource = this.profileResourceBuilder();
        Mockito.when(profileRepository.existsProfileByEmail(Mockito.anyString())).thenReturn(true);
        profileService = new ProfileServiceImpl(profileRepository, profileMapper);

        Assertions.assertThrows(IllegalArgumentException.class, () -> profileService.create(profileResource));
    }

    @Test
    void givenIdAndProfileResource_whenIdExist_thenUpdateMakeSave() {
        ProfileResource profileResource = this.profileResourceBuilder();
        Profile profile = this.profileBuilder();
        updateArrangePart(true, profileResource, profile);

        ProfileResource updated = profileService.update(ID, profileResource);

        Mockito.verify(profileRepository, Mockito.times(1)).save(Mockito.any(Profile.class));
        Assertions.assertNotNull(updated);
    }

    @Test
    void givenIdAndProfileResource_whenIdExist_thenUpdateMakeInsert() {
        ProfileResource profileResource = profileResourceBuilder();
        Profile profile = profileBuilder();
        updateArrangePart(false, profileResource, profile);

        ProfileResource updated = profileService.update("gqgjgdf", profileResource);

        Mockito.verify(profileRepository, Mockito.times(1)).insert(Mockito.any(Profile.class));
        Assertions.assertNotNull(updated);
    }

    @Test
    void givenIdAndProfileResource_whenIdIsNull_thenThrowIllegalArgumentException() {
        ProfileResource profileResource = profileResourceBuilder();
        Mockito.when(profileRepository.existsById(Mockito.any())).thenThrow(IllegalArgumentException.class);
        profileService = new ProfileServiceImpl(profileRepository, profileMapper);
        Assertions.assertThrows(IllegalArgumentException.class, () -> profileService.update(ID, profileResource));
    }

    @Test
    void givenId_whenIdFound_thenReturnProfile() throws ProfileNotFoundException {
        Mockito.when(profileRepository.findById(Mockito.anyString())).thenReturn(Optional.of(profileBuilder()));
        Mockito.when(profileMapper.profileToProfileResource(Mockito.any())).thenReturn(profileResourceBuilder());
        profileService = new ProfileServiceImpl(profileRepository, profileMapper);
        ProfileResource profileResource = profileService.findById(ID);

        Assertions.assertNotNull(profileResource);
    }

    @Test
    void givenId_whenIdNotFound_thenThrowProfileNotFoundException() {
        Mockito.when(profileRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        profileService = new ProfileServiceImpl(profileRepository, profileMapper);

        Assertions.assertThrows(ProfileNotFoundException.class, () -> profileService.findById(ID));
    }

    private void updateArrangePart(boolean isIdExist, ProfileResource profileResource, Profile profile){
        Mockito.when(profileRepository.existsById(Mockito.anyString())).thenReturn(isIdExist);
        Mockito.when(profileMapper.profileResourceToProfile(Mockito.any(ProfileResource.class))).thenReturn(profile);
        Mockito.when(profileMapper.profileToProfileResource(Mockito.any(Profile.class))).thenReturn(profileResource);
        if (isIdExist) {
            Mockito.when(profileRepository.save(Mockito.any(Profile.class))).thenReturn(profile);
        } else {
            Mockito.when(profileRepository.insert(Mockito.any(Profile.class))).thenReturn(profile);
        }
        profileService = new ProfileServiceImpl(profileRepository, profileMapper);
    }

    private ProfileResource profileResourceBuilder(){
        return ProfileResource.builder()
                .id("gqgjgdf")
                .address("2325 av walklay")
                .city("montreal")
                .country("Canada")
                .district("montreal")
                .email("koffiange62@gmail.com")
                .firstName("ange")
                .lastName("koffi")
                .password("test123")
                .phoneNumber("5144341118")
                .province("Quebec")
                .status(ProfileStatus.ENABLE)
                .build();
    }

    private Profile profileBuilder(){
        return new Profile("gqgjgdf", "koffi", "ange", "koffiange62@gmail.com",
                "5144341118", "test123", "Canada", "Quebec",
                "Montreal", "Montreal", "Av Walklay", "2325");
    }
}