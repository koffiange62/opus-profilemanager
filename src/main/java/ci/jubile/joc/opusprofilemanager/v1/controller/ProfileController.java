package ci.jubile.joc.opusprofilemanager.v1.controller;

import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import ci.jubile.joc.opusprofilemanager.v1.service.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/profiles")
@Validated
public class ProfileController {
    @Autowired
    ProfileServiceImpl profileServiceImpl;

    @PostMapping
    public ProfileResource create(@Valid @RequestBody ProfileResource profileResource){
        return profileServiceImpl.create(profileResource);
    }

    @GetMapping("profile/{id}")
    public ProfileResource findById(@PathVariable(name = "id") String id) throws ProfileNotFoundException {
        return profileServiceImpl.findById(id);
    }

    @PutMapping("profile/{id}")
    public ProfileResource updateProfile(@PathVariable(name = "id") String id, @Valid @RequestBody ProfileResource profileResource){
        return profileServiceImpl.update(id, profileResource);
    }

    @PutMapping("profile/enabling-or-desabling/{id}")
    public void changeProfileEnablingStatus(@PathVariable(name = "id") String id,
                                            @RequestParam(name = "status") ProfileStatus status)
                                            throws ProfileNotFoundException {
        profileServiceImpl.enableOrDisableProfile(id, status);
    }

}
