package ci.jubile.joc.opusprofilemanager.v1.controller;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.mapper.ProfileMapper;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import ci.jubile.joc.opusprofilemanager.v1.service.PasswordService;
import ci.jubile.joc.opusprofilemanager.v1.service.ProfileServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/profiles")
@Validated
public class ProfileController {

    private final ProfileServiceImpl profileServiceImpl;
    private final ProfileMapper profileMapper;
    private final PasswordService passwordService;

    public ProfileController(ProfileServiceImpl profileServiceImpl, ProfileMapper profileMapper, PasswordService passwordService) {
        this.profileServiceImpl = profileServiceImpl;
        this.profileMapper = profileMapper;
        this.passwordService = passwordService;
    }

    @GetMapping
    public List<ProfileResource> findAll(){
        return profileServiceImpl.findAll().stream().map(profileMapper::profileToProfileResource).collect(Collectors.toList());
    }

    @PostMapping("profile")
    public ProfileResource create(@Valid @RequestBody ProfileResource profileResource){
        Profile profile = profileMapper.profileResourceToProfile(profileResource);

        profile = profileServiceImpl.create(profile);
        passwordService.create(profile.getId(), profileResource.getPassword());

        return profileMapper.profileToProfileResource(profile);
    }

    @GetMapping("profile/{id}")
    public ProfileResource findById(@PathVariable(name = "id") String id) throws ProfileNotFoundException {
        Profile profile = profileServiceImpl.findById(id);
        return profileMapper.profileToProfileResource(profile);
    }

    @PutMapping("profile")
    public ProfileResource updateProfile(@Valid @RequestBody ProfileResource profileResource){
        Profile profileToUpdate = profileMapper.profileResourceToProfile(profileResource);
        Profile updated = profileServiceImpl.update(profileToUpdate);
        return profileMapper.profileToProfileResource(updated);
    }

    @PutMapping("profile/enabling-or-desabling/{id}")
    public void changeProfileEnablingStatus(@PathVariable(name = "id") String id,
                                            @RequestParam(name = "status") ProfileStatus status)
                                            throws ProfileNotFoundException {
        profileServiceImpl.enableOrDisableProfile(id, status);
    }

}
