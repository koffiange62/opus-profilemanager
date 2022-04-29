package ci.jubile.joc.opusprofilemanager.repository;


import ci.jubile.joc.opusprofilemanager.domain.Velo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VeloRepository extends MongoRepository<Velo, String> {
}
