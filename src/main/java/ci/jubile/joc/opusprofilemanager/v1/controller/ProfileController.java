package ci.jubile.joc.opusprofilemanager.v1.controller;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/profiles")
@Validated
public class ProfileController {
    @Autowired
    ProfileService profileService;

    // TODO : controller les objets json reçu dans les methode des controlllers
    @PostMapping
    public Profile create(@Valid @RequestBody Profile profile){
        return profileService.save(profile);
    }

    @GetMapping("profile/{id}")
    public Profile findById(@PathVariable(name = "id") String id){
        try {
            return profileService.findById(id);
        } catch (ProfileNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile "+id+" not found.", e);
        }
    }


}
