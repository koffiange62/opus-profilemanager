package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.v1.enumeration.ProfileStatus;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;

import java.util.List;

public interface ProfileService {

    List<ProfileResource> findAll();

    ProfileResource create(ProfileResource profileResource);

    ProfileResource update(String id, ProfileResource profileResource);

    ProfileResource findById(String id) throws ProfileNotFoundException;

    ProfileResource enableOrDisableProfile(String id, ProfileStatus status) throws ProfileNotFoundException;
}
