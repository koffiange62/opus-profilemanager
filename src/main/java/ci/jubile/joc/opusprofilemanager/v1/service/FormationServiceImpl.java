package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.model.Formation;
import ci.jubile.joc.opusprofilemanager.model.Profile;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Component
public class FormationServiceImpl implements ProfilePropertiesService<Formation> {
    private final ProfileServiceImpl profileService;

    public FormationServiceImpl(ProfileServiceImpl profileService) {
        this.profileService = profileService;
    }

    @Override
    public Formation addFormation(String idProfile, Formation formation) {
        try {
            Profile profile = profileService.findById(idProfile);
            formation.setId(UUID.randomUUID().toString());
            formation.setCreatedAt(LocalDateTime.now());
            profile.getFormations().add(formation);
            profileService.update(profile);
        } catch (ProfileNotFoundException e) {
            throw new NoSuchElementException(e);
        }

        return formation;
    }

    @Override
    public Formation updateFormation(String idProfile, Formation formation) {
        try {
            Profile profile = profileService.findById(idProfile);
            List<Formation> formationList = profile.getFormations();
            Optional<Formation> oldFormationOptional = formationList.stream().filter(f -> f.getId().equals(formation.getId())).findFirst();
            oldFormationOptional.ifPresent(oldFormation -> {
                oldFormation.setId(oldFormation.getId());
                oldFormation.setCreatedAt(oldFormation.getCreatedAt());
                oldFormation.setUpdatedAt(LocalDateTime.now());
                if (!oldFormation.getDiploma().equals(formation.getDiploma())) oldFormation.setDiploma(formation.getDiploma());
                if (!oldFormation.getYear().equals(formation.getYear())) oldFormation.setYear(formation.getYear());
                if (!oldFormation.getSchool().equals(formation.getSchool())) oldFormation.setSchool(formation.getSchool());
                if (!oldFormation.getDomain().equals(formation.getDomain())) oldFormation.setDomain(formation.getDomain());
                profile.getFormations().removeIf(f -> f.getId().equals(formation.getId()));
                profile.getFormations().add(oldFormation);
            });
            profileService.update(profile);
        } catch (ProfileNotFoundException e) {
            throw new NoSuchElementException(e);
        }
        return formation;
    }

    @Override
    public void deleteFormation(String idProfile, String formationId) {
        try {
            Profile profile = profileService.findById(idProfile);
            profile.getFormations().removeIf(f -> f.getId().equals(formationId));
            profileService.update(profile);
        } catch (ProfileNotFoundException e) {
            throw new NoSuchElementException(e);
        }
    }

    @Override
    public List<Formation> listFormation(String idProfile) {
        try {
            Profile profile = profileService.findById(idProfile);
            return profile.getFormations();
        } catch (ProfileNotFoundException e) {
            throw new NoSuchElementException(e);
        }
    }
}
