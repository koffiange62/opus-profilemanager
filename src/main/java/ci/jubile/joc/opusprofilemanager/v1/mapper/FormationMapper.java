package ci.jubile.joc.opusprofilemanager.v1.mapper;

import ci.jubile.joc.opusprofilemanager.domain.Formation;
import ci.jubile.joc.opusprofilemanager.v1.resource.FormationResource;
import org.mapstruct.Mapper;

import java.text.Normalizer;

@Mapper
public interface FormationMapper {
    FormationResource formationToFormationResource(Formation formation);
    Formation formationResourceToFormation(FormationResource formation);
}
