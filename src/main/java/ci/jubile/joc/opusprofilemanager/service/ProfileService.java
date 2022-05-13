package ci.jubile.joc.opusprofilemanager.service;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    /**
     * Insert or Update profile data
     * @param profile
     * @return
     */
    public Profile save(Profile profile){
        Profile savedProfile;
        if (profile.getId() == null){
            savedProfile = profileRepository.insert(profile);
        } else {
            savedProfile = profileRepository.save(profile);
        }
        return savedProfile;
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
