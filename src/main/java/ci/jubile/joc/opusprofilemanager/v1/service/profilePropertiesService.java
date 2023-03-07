package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.model.Formation;

public interface profilePropertiesService<T> {
    Formation addFormation(String idProfile, T t);
    Formation updateFormation(String idProfile, T t);
    Formation deleteFormation(String idProfile, String id);
    Formation listFormation(String idProfile, String id);
}
