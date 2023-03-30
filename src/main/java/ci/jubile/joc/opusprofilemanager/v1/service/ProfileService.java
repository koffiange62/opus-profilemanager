package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.model.Profile;
import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;

import java.util.List;

public interface ProfileService {

    List<Profile> findAll();

    Profile create(Profile profile);

    Profile update(Profile profile);

    Profile findById(String id) throws ProfileNotFoundException;

    void enableOrDisableProfile(String id, ProfileStatus status) throws ProfileNotFoundException;

    void deleteById(String id) throws ProfileNotFoundException;
}
