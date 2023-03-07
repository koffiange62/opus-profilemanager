package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.model.Formation;
import ci.jubile.joc.opusprofilemanager.v1.mapper.ProfileMapper;

public class FormationService implements profilePropertiesService<Formation>{
    private final ProfileServiceImpl profileService;
    private final ProfileMapper profileMapper;

    public FormationService(ProfileServiceImpl profileService, ProfileMapper profileMapper) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }

    @Override
    public Formation addFormation(String idProfile, Formation formation) {
        return null;
    }

    @Override
    public Formation updateFormation(String idProfile, Formation formation) {
        return null;
    }

    @Override
    public Formation deleteFormation(String idProfile, String id) {
        return null;
    }

    @Override
    public Formation listFormation(String idProfile, String id) {
        return null;
    }
}
