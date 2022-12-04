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


    /**
     * Insert or Update profile data
     * @param profile
     * @return
     */
    public ProfileResource save(Profile profile){
        Profile savedProfile;
        if (profile.getId() == null){
            savedProfile = profileRepository.insert(profile);
        } else {
            savedProfile = profileRepository.save(profile);
        }
        return profileMapper.profileToProfileResource(savedProfile);
    }

    // TODO : refactorer cette method pour que'elle retourne une exeption quand il n'existe pas de profile ayqnt cet ID.
    public Profile findById(String id) throws ProfileNotFoundException {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isEmpty())
            throw new ProfileNotFoundException();
        return optionalProfile.get();
    }

    // TODO : refactorer cette method pour que'elle retourne une exeption quand il n'existe pas de profile ayqnt cet ID.
    public ProfileResource findByIdMapped(String id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isEmpty())
            log.info("NON TROUVE");
        return profileMapper.profileToProfileResource(optionalProfile.get());
    }

    public void enableProfile(String id){
        Optional<Profile> profileOpt = profileRepository.findById(id);
        profileOpt.ifPresent(profile -> {
            profile.setStatus(true);
            this.save(profile);
        });
    }

    public void desableProfile(String id){
        Optional<Profile> profileOpt = profileRepository.findById(id);
        profileOpt.ifPresent(profile -> {
            profile.setStatus(false);
        this.save(profile);
        });
    }

}
