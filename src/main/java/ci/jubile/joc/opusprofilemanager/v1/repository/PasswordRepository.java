package ci.jubile.joc.opusprofilemanager.v1.repository;

import ci.jubile.joc.opusprofilemanager.model.Password;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PasswordRepository extends MongoRepository<Password, String> {
    Optional<Password> findByProfileId(String s);

    void deleteByProfileId(String id);
}
