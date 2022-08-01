package ci.jubile.joc.opusprofilemanager.controller;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return profileService.findById(id);
    }


}
