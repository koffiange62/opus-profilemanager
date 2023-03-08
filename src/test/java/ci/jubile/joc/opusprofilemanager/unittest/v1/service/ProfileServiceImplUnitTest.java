package ci.jubile.joc.opusprofilemanager.unittest.v1.service;

import ci.jubile.joc.opusprofilemanager.model.Profile;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.mapper.ProfileMapper;
import ci.jubile.joc.opusprofilemanager.v1.repository.ProfileRepository;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import ci.jubile.joc.opusprofilemanager.v1.service.PasswordServiceImpl;
import ci.jubile.joc.opusprofilemanager.v1.service.ProfileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class ProfileServiceImplUnitTest {

    private static final String ID = "gqgjgdf";

    @MockBean
    static ProfileRepository profileRepository;

    @MockBean
    static ProfileMapper profileMapper;

    @MockBean
    static PasswordServiceImpl passwordService;

    ProfileServiceImpl profileService;

    @Test
    @DisplayName(value = "create() : test profile creation")
    void givenProfile_whenNewProfile_thenCreateProfile() {
        Profile profile = this.profileBuilder();
        Mockito.when(profileRepository.insert(Mockito.any(Profile.class))).thenReturn(profile);

        profileService = new ProfileServiceImpl(profileRepository);
        Profile createdProfile = profileService.create(profile);

        Assert.notNull(createdProfile, "Profile non cree ");
        Assert.isInstanceOf(Profile.class, createdProfile);
    }


    @Test
    @DisplayName(value = "create() : test ThrowIllegalArgumentException when profile email already exist")
    void givenProfile_whenProfileEmailAlreadyExist_thenThrowIllegalArgumentException(){
        Profile profile = this.profileBuilder();
        Mockito.when(profileRepository.existsProfileByEmail(Mockito.anyString())).thenReturn(true);
        profileService = new ProfileServiceImpl(profileRepository);

        Assertions.assertThrows(IllegalArgumentException.class, () -> profileService.create(profile));
    }

    @Test
    @DisplayName(value = "update() : test if update make a save when Profile already exist")
    void givenProfileResource_whenExist_thenUpdateMakeSave() {
        Profile profile = this.profileBuilder();
        ProfileResource profileResource = profileResourceBuilder();
        updateArrangePart(true, profileResource, profile);
        Profile updatedProfile = profileService.update(profile);

        Mockito.verify(profileRepository, Mockito.times(1)).save(Mockito.any(Profile.class));
        Assertions.assertNotNull(updatedProfile);
    }

    @Test
    @DisplayName(value = "update() : test if update make a insert when Profile not already exist")
    void givenProfileResource_whenIdNotExist_thenUpdateMakeInsert() {
        Profile profile = profileBuilder();
        ProfileResource profileResource = profileResourceBuilder();
        updateArrangePart(false, profileResource, profile);

        Profile updatedProfile = profileService.update(profile);

        Mockito.verify(profileRepository, Mockito.times(1)).insert(Mockito.any(Profile.class));
        Assertions.assertNotNull(updatedProfile);
    }

    @Test
    @DisplayName(value = "update() : test if update throws IllegalArgumentException when Profile id is null")
    void givenIdAndProfileResource_whenIdIsNull_thenThrowIllegalArgumentException() {
        Profile profile = profileBuilder();
        Mockito.when(profileMapper.profileResourceToProfile(Mockito.any(ProfileResource.class))).thenReturn(profile);
        Mockito.when(profileRepository.findById(Mockito.any())).thenThrow(IllegalArgumentException.class);
        profileService = new ProfileServiceImpl(profileRepository);
        Assertions.assertThrows(IllegalArgumentException.class, () -> profileService.update(profile));
    }

    @Test
    @DisplayName(value = "findById() : test if a profile is return when id is found")
    void givenId_whenIdFound_thenReturnProfile() throws ProfileNotFoundException {
        Mockito.when(profileRepository.findById(Mockito.anyString())).thenReturn(Optional.of(profileBuilder()));
        Mockito.when(profileMapper.profileToProfileResource(Mockito.any())).thenReturn(profileResourceBuilder());
        profileService = new ProfileServiceImpl(profileRepository);
        Profile profile = profileService.findById(ID);

        Assertions.assertNotNull(profile);
    }

    @Test
    @DisplayName(value = "findById() : test if ProfileNotFoundException is thrown when id is not found")
    void givenId_whenIdNotFound_thenThrowProfileNotFoundException() {
        Mockito.when(profileRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        profileService = new ProfileServiceImpl(profileRepository);

        Assertions.assertThrows(ProfileNotFoundException.class, () -> profileService.findById(ID));
    }

    @Test
    @DisplayName(value = "enableOrDisableProfile() : test ThrowProfileNotFoundException")
    void givenIdAndProfileStatus_whenIdNotFound_thenThrowProfileNotFoundException(){
        Mockito.when(profileRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        profileService = new ProfileServiceImpl(profileRepository);

        Assertions.assertThrows(ProfileNotFoundException.class, () -> profileService.enableOrDisableProfile(ID, ProfileStatus.ENABLE));
    }

    private void updateArrangePart(boolean isIdExist, ProfileResource profileResource, Profile profile){

        Mockito.when(profileMapper.profileResourceToProfile(Mockito.any(ProfileResource.class))).thenReturn(profile);
        Mockito.when(profileMapper.profileToProfileResource(Mockito.any(Profile.class))).thenReturn(profileResource);
        if (isIdExist) {
            Mockito.when(profileRepository.findById(Mockito.anyString())).thenReturn(Optional.of(profileBuilder()));
            Mockito.when(profileRepository.save(Mockito.any(Profile.class))).thenReturn(profile);
        } else {
            Mockito.when(profileRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
            Mockito.when(profileRepository.insert(Mockito.any(Profile.class))).thenReturn(profile);
        }
        profileService = new ProfileServiceImpl(profileRepository);
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
                .createdAt(LocalDateTime.now())
                .build();
    }

    private Profile profileBuilder(){
        Profile profile = new Profile("gqgjgdf", "koffi", "ange", "koffiange62@gmail.com",
                "5144341118",  "Canada", "Quebec",
                "Montreal", "Montreal", "Av Walklay", "2325");
        profile.setCreatedAt(LocalDateTime.now());
        return profile;
    }
}