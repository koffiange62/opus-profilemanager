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
        profile = profileRepository.insert(profile);
        return profileMapper.profileToProfileResource(profile);
    }

    public ProfileResource update(String id, ProfileResource profileResource){
        Profile savedProfile;
        if (profileRepository.existsById(id)){
            savedProfile = profileRepository.save(profileMapper.profileResourceToProfile(profileResource));
        } else {
            savedProfile = profileRepository.insert(profileMapper.profileResourceToProfile(profileResource));
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
            this.update(id, profileMapper.profileToProfileResource(profileOpt.get()));
        });
    }
}
