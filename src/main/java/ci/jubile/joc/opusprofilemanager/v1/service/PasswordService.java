package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.domain.Password;
import ci.jubile.joc.opusprofilemanager.v1.exception.PasswordHandlerException;
import ci.jubile.joc.opusprofilemanager.v1.resource.PasswordResource;

public interface PasswordService {
    void create(PasswordResource passwordResource);

    Password findByProfileId(String profileId) throws PasswordHandlerException;

    void updatePassword(PasswordResource passwordResource) throws PasswordHandlerException;
}
