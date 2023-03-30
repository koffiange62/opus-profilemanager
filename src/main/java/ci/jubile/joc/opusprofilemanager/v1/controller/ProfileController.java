package ci.jubile.joc.opusprofilemanager.v1.controller;

import ci.jubile.joc.opusprofilemanager.model.Profile;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.mapper.ProfileMapper;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import ci.jubile.joc.opusprofilemanager.v1.service.PasswordServiceImpl;
import ci.jubile.joc.opusprofilemanager.v1.service.ProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/profiles")
@Validated
public class ProfileController {

    private final ProfileServiceImpl profileServiceImpl;
    private final ProfileMapper profileMapper;
    private final PasswordServiceImpl passwordService;

    public ProfileController(ProfileServiceImpl profileServiceImpl, ProfileMapper profileMapper, PasswordServiceImpl passwordService) {
        this.profileServiceImpl = profileServiceImpl;
        this.profileMapper = profileMapper;
        this.passwordService = passwordService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileResource>> findAll(){
        List<ProfileResource> profileResourceList = profileServiceImpl.findAll().stream()
                .map(profileMapper::profileToProfileResource).collect(Collectors.toList());
        return ResponseEntity.ok(profileResourceList);
    }

    @GetMapping("profile/{id}")
    public ResponseEntity<ProfileResource> findById(@PathVariable(name = "id") String id) throws ProfileNotFoundException {
        Profile profile = profileServiceImpl.findById(id);
        return ResponseEntity.ok(profileMapper.profileToProfileResource(profile));
    }

    @PostMapping("profile")
    public ResponseEntity<ProfileResource> create(@Valid @RequestBody ProfileResource profileResource){
        Profile profile = profileMapper.profileResourceToProfile(profileResource);

        profile = profileServiceImpl.create(profile);
        passwordService.create(profile.getId(), profileResource.getPassword());

        String uri = "/profile/"+profile.getId();
        return ResponseEntity.created(URI.create(uri)).body(profileMapper.profileToProfileResource(profile)) ;
    }

    @PutMapping("profile")
    public ResponseEntity<ProfileResource> update(@Valid @RequestBody ProfileResource profileResource){
        Profile profileToUpdate = profileMapper.profileResourceToProfile(profileResource);
        Profile updated = profileServiceImpl.update(profileToUpdate);
        return ResponseEntity.ok(profileMapper.profileToProfileResource(updated));
    }

    @PutMapping("profile/enabling-or-desabling/{id}")
    public ResponseEntity<?> changeProfileEnablingStatus(@PathVariable(name = "id") String id,
                                            @RequestParam(name = "status") ProfileStatus status)
                                            throws ProfileNotFoundException {
        profileServiceImpl.enableOrDisableProfile(id, status);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("profile/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id){
        profileServiceImpl.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
