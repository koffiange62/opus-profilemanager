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
import java.util.Objects;
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

    private void compareAndChange(Profile oldProfile, Profile newOne){
        if(!oldProfile.getLastName().equals(newOne.getLastName())) oldProfile.setLastName(newOne.getLastName());
        if(!oldProfile.getFirstName().equals(newOne.getFirstName())) oldProfile.setFirstName(newOne.getFirstName());
        if(!oldProfile.getEmail().equals(newOne.getEmail())) oldProfile.setEmail(newOne.getEmail());
        if(!oldProfile.getPhoneNumber().equals(newOne.getPhoneNumber())) oldProfile.setPhoneNumber(newOne.getPhoneNumber());
        if(!oldProfile.getCountry().equals(newOne.getCountry())) oldProfile.setCountry(newOne.getCountry());
        if(!oldProfile.getProvince().equals(newOne.getProvince())) oldProfile.setProvince(newOne.getProvince());
        if(!oldProfile.getCity().equals(newOne.getCity())) oldProfile.setCity(newOne.getCity());
        if(!oldProfile.getStreet().equals(newOne.getStreet())) oldProfile.setDistrict(newOne.getStreet());
        if(!oldProfile.getDistrict().equals(newOne.getDistrict())) oldProfile.setDistrict(newOne.getDistrict());
        if(!oldProfile.getAddress().equals(newOne.getAddress())) oldProfile.setAddress(newOne.getAddress());
        if(!oldProfile.getStatus().equals(newOne.getStatus())) oldProfile.setStatus(newOne.getStatus());
        if(!oldProfile.getCreatedAt().equals(newOne.getCreatedAt())) oldProfile.setCreatedAt(oldProfile.getCreatedAt());
        oldProfile.setUpdatedAt(LocalDateTime.now());
    }
}
