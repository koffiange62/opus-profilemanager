package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.mapper.ProfileMapper;
import ci.jubile.joc.opusprofilemanager.v1.repository.ProfileRepository;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final ProfileRepository profileRepository;

    private final ProfileMapper profileMapper;

    public ProfileResource create(ProfileResource profileResource){
        if (profileResource.getEmail() != null && profileRepository.existsProfileByEmail(profileResource.getEmail()))
            throw new IllegalArgumentException("Ce profil existe deja");


        Profile profile = profileMapper.profileResourceToProfile(profileResource);
        profile.setCreatedAt(LocalDateTime.now());
        profile = profileRepository.insert(profile);
        return profileMapper.profileToProfileResource(profile);
    }

    public ProfileResource update(ProfileResource profileResource){
        Profile unsavedProfile = profileMapper.profileResourceToProfile(profileResource);
        Profile savedProfile;
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
            throw new ProfileNotFoundException("Profile non trouvé");
        return profileMapper.profileToProfileResource(optionalProfile.get());
    }

    public void enableOrDisableProfile(String id, ProfileStatus status) {
        Optional<Profile> profileOpt = profileRepository.findById(id);
        profileOpt.ifPresent(profile -> {
            profileOpt.get().setStatus(status);
            this.update(profileMapper.profileToProfileResource(profileOpt.get()));
        });
    }
}
