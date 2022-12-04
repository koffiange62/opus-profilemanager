package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;

public interface ProfileService {
    ProfileResource create(ProfileResource profileResource);

    ProfileResource update(String id, ProfileResource profileResource);

    ProfileResource findById(String id) throws ProfileNotFoundException;

    void enableOrDisableProfile(String id, ProfileStatus status) throws ProfileNotFoundException;
}
