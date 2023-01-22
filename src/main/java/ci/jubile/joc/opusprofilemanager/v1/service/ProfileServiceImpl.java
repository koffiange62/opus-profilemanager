package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.mapper.ProfileMapper;
import ci.jubile.joc.opusprofilemanager.v1.repository.ProfileRepository;
import ci.jubile.joc.opusprofilemanager.v1.resource.PasswordResource;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ProfileServiceImpl implements ProfileService{
    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    private final ProfileRepository profileRepository;

    private final PasswordServiceImpl passwordService;

    private final ProfileMapper profileMapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, PasswordServiceImpl passwordService, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.passwordService = passwordService;
        this.profileMapper = profileMapper;
    }

    @Override
    public List<ProfileResource> findAll() {
        return profileRepository.findAll().stream().map(profileMapper::profileToProfileResource).collect(Collectors.toList());
    }

    public ProfileResource create(ProfileResource profileResource){
        if (profileResource.getEmail() != null && profileRepository.existsProfileByEmail(profileResource.getEmail()))
            throw new IllegalArgumentException(resourceBundle.getString("profile.already.exist"));

        Profile profile = profileMapper.profileResourceToProfile(profileResource);
        profile.setCreatedAt(LocalDateTime.now());
        profile = profileRepository.insert(profile);

        PasswordResource passwordResource = PasswordResource.builder()
                .profileId(profile.getId())
                .newPassword(profileResource.getPassword())
                .build();
        Assert.notNull(passwordResource, "Password Resource est null");
        passwordService.create(passwordResource);

        return profileMapper.profileToProfileResource(profile);
    }

    public ProfileResource update(ProfileResource profileResource){
        Profile savedProfile;
        Profile unsavedProfile = profileMapper.profileResourceToProfile(profileResource);
        Optional<Profile> oldProfile = profileRepository.findById(unsavedProfile.getId());
        if (oldProfile.isPresent()){
            unsavedProfile.setUpdatedAt(LocalDateTime.now());
            unsavedProfile.setCreatedAt(oldProfile.get().getCreatedAt());
            savedProfile = profileRepository.save(unsavedProfile);
        } else {
            unsavedProfile.setCreatedAt(LocalDateTime.now());
            savedProfile = profileRepository.insert(unsavedProfile);
        }
        return profileMapper.profileToProfileResource(savedProfile);
    }

    public ProfileResource findById(String id) throws ProfileNotFoundException {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isEmpty())
            throw new ProfileNotFoundException(resourceBundle.getString("profile.not.found"));
        return profileMapper.profileToProfileResource(optionalProfile.get());
    }

    public ProfileResource enableOrDisableProfile(String id, ProfileStatus status) throws ProfileNotFoundException {
        Optional<Profile> profileOpt = profileRepository.findById(id);
        if(profileOpt.isEmpty())
            throw new ProfileNotFoundException(resourceBundle.getString("profile.not.found"));

        profileOpt.get().setStatus(status);
        return this.update(profileMapper.profileToProfileResource(profileOpt.get()));
    }
}
