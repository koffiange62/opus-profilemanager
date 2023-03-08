package ci.jubile.joc.opusprofilemanager.v1.service;

import java.util.List;

public interface ProfilePropertiesService<T> {
    T addFormation(String idProfile, T t);
    T updateFormation(String idProfile, T t);
    void deleteFormation(String idProfile, String id);
    List<T> listFormation(String idProfile);
}
