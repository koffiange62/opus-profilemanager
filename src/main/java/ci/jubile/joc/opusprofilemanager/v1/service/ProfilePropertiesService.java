package ci.jubile.joc.opusprofilemanager.v1.service;

import java.util.List;

public interface ProfilePropertiesService<T> {
    T add(String idProfile, T t);
    T update(String idProfile, T t);
    void delete(String idProfile, String id);
    List<T> findAll(String idProfile);
}
