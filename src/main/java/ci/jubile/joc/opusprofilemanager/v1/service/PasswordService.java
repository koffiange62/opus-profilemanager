package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.domain.Password;
import ci.jubile.joc.opusprofilemanager.v1.exception.PasswordHandlerException;

public interface PasswordService {
    void create(String profileId, String nonEncodedPassword);

    Password findByProfileId(String profileId) throws PasswordHandlerException;

    void updatePassword(String profileId, String nonEncodedPassword) throws PasswordHandlerException;
}
