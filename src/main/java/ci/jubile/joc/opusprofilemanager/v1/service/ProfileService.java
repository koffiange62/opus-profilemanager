package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.mapper.ProfileMapper;
import ci.jubile.joc.opusprofilemanager.v1.repository.ProfileRepository;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Log4j2
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ProfileMapper profileMapper;

    public ProfileResource create(ProfileResource profileResource){
        Profile savedProfile;
        if (profileResource.getId() != null && profileRepository.existsById(profileResource.getId()))
            throw new IllegalArgumentException("Ce profil existe deja");
        savedProfile = profileRepository.insert(profileMapper.profileResourceToProfile(profileResource));
        return profileMapper.profileToProfileResource(savedProfile);
    }

    public ProfileResource update(String id, ProfileResource profileResource){
        Profile savedProfile;
        if (profileResource.getId() == null){
            savedProfile = profileRepository.insert(profileMapper.profileResourceToProfile(profileResource));
        } else {
            savedProfile = profileRepository.save(profileMapper.profileResourceToProfile(profileResource));
        }
        return profileMapper.profileToProfileResource(savedProfile);
    }

    public ProfileResource findById(String id) throws ProfileNotFoundException {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isEmpty())
            throw new ProfileNotFoundException("Profile non trouvé");
        return profileMapper.profileToProfileResource(optionalProfile.get());
    }

    public boolean enableOrDisableProfile(String id) throws ProfileNotFoundException {
        Optional<Profile> profileOpt = profileRepository.findById(id);
        if (profileOpt.isPresent()){
            profileOpt.get().setStatus(!profileOpt.get().isStatus());
            this.update(id, profileMapper.profileToProfileResource(profileOpt.get()));
            return !profileOpt.get().isStatus();
        } else {
            throw new ProfileNotFoundException();
        }
    }
}
