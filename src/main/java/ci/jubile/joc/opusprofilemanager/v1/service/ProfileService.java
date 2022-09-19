package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.repository.ProfileRepository;
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

    // TODO : refqctorer cette method pour que'elle retourne une exeption quand il n'existe pas de profile ayqnt cet ID.
    public Profile findById(String id) throws ProfileNotFoundException {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isEmpty())
            throw new ProfileNotFoundException();
        return optionalProfile.get();
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
