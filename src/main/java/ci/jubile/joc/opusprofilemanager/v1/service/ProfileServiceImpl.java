package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.model.Profile;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
@Slf4j
public class ProfileServiceImpl implements ProfileService{
    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    private final ProfileRepository profileRepository;

    private final PasswordServiceImpl passwordService;

    public ProfileServiceImpl(ProfileRepository profileRepository, PasswordServiceImpl passwordService) {
        this.profileRepository = profileRepository;
        this.passwordService = passwordService;
    }

    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile create(Profile profile){
        if (profile.getEmail() != null && profileRepository.existsProfileByEmail(profile.getEmail()))
            throw new IllegalArgumentException(resourceBundle.getString("profile.already.exist"));

        profile.setCreatedAt(LocalDateTime.now());
        return profileRepository.insert(profile);
    }

    @Override
    public Profile update(Profile newOne){
        Profile updatedProfile;
        Optional<Profile> oldProfileOptional = profileRepository.findById(newOne.getId());
        if (oldProfileOptional.isPresent()){
            Profile oldProfile = oldProfileOptional.get();
            this.compareAndChange(oldProfile, newOne);
            updatedProfile = profileRepository.save(oldProfile);
        } else {
            newOne.setCreatedAt(LocalDateTime.now());
            updatedProfile = profileRepository.insert(newOne);
        }
        return updatedProfile;
    }

    @Override
    public Profile findById(String id) throws ProfileNotFoundException {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        return optionalProfile.orElseThrow(() -> new ProfileNotFoundException(resourceBundle.getString("profile.not.found")));
    }

    @Override
    public void enableOrDisableProfile(String id, ProfileStatus status) throws ProfileNotFoundException {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        if(optionalProfile.isEmpty())
            throw new ProfileNotFoundException(resourceBundle.getString("profile.not.found"));

        Profile profile = optionalProfile.get();
        profile.setStatus(status);
        update(profile);
    }

    @Override
    public void deleteById(String id) {
        passwordService.deleteByProfile(id);
        profileRepository.deleteById(id);
    }

    private void compareAndChange(Profile oldProfile, Profile newOne){
        if(newOne.getLastName() != null && !oldProfile.getLastName().equals(newOne.getLastName())) oldProfile.setLastName(newOne.getLastName());
        if(newOne.getFirstName() != null && !oldProfile.getFirstName().equals(newOne.getFirstName())) oldProfile.setFirstName(newOne.getFirstName());
        if(newOne.getEmail() != null && !oldProfile.getEmail().equals(newOne.getEmail())) oldProfile.setEmail(newOne.getEmail());
        if(newOne.getPhoneNumber() != null && !oldProfile.getPhoneNumber().equals(newOne.getPhoneNumber())) oldProfile.setPhoneNumber(newOne.getPhoneNumber());
        if(newOne.getCountry() != null && !oldProfile.getCountry().equals(newOne.getCountry())) oldProfile.setCountry(newOne.getCountry());
        if(newOne.getProvince() != null && !oldProfile.getProvince().equals(newOne.getProvince())) oldProfile.setProvince(newOne.getProvince());
        if(newOne.getCity() != null && !oldProfile.getCity().equals(newOne.getCity())) oldProfile.setCity(newOne.getCity());
        if(newOne.getStreet() != null && !oldProfile.getStreet().equals(newOne.getStreet())) oldProfile.setDistrict(newOne.getStreet());
        if(newOne.getDistrict() != null && !oldProfile.getDistrict().equals(newOne.getDistrict())) oldProfile.setDistrict(newOne.getDistrict());
        if(newOne.getAddress() != null && !oldProfile.getAddress().equals(newOne.getAddress())) oldProfile.setAddress(newOne.getAddress());
        if(newOne.getStatus() != null && !oldProfile.getStatus().equals(newOne.getStatus())) oldProfile.setStatus(newOne.getStatus());
        if(newOne.getCreatedAt() != null && !oldProfile.getCreatedAt().equals(newOne.getCreatedAt())) oldProfile.setCreatedAt(oldProfile.getCreatedAt());

        if(newOne.getFormations() != null && !oldProfile.getFormations().equals(newOne.getFormations())) oldProfile.setFormations(newOne.getFormations());
        if(newOne.getCompetences() != null && !oldProfile.getCompetences().equals(newOne.getCompetences())) oldProfile.setCompetences(newOne.getCompetences());
        if(newOne.getExperiences() != null && !oldProfile.getExperiences().equals(newOne.getExperiences())) oldProfile.setExperiences(newOne.getExperiences());

        oldProfile.setUpdatedAt(LocalDateTime.now());
    }


}
