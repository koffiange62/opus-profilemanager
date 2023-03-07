package ci.jubile.joc.opusprofilemanager.v1.mapper;

import ci.jubile.joc.opusprofilemanager.model.Competence;
import ci.jubile.joc.opusprofilemanager.v1.resource.CompetenceResource;
import org.mapstruct.Mapper;

@Mapper
public interface CompetenceMapper {
    CompetenceResource competenceToCompetenceResource(Competence competence);
    Competence competenceResourceToCompetenceResource(CompetenceResource Resource);
}
