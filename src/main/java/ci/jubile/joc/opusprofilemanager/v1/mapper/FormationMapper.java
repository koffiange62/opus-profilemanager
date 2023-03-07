package ci.jubile.joc.opusprofilemanager.v1.mapper;

import ci.jubile.joc.opusprofilemanager.model.Formation;
import ci.jubile.joc.opusprofilemanager.v1.resource.FormationResource;
import org.mapstruct.Mapper;

@Mapper
public interface FormationMapper {
    FormationResource formationToFormationResource(Formation formation);
    Formation formationResourceToFormation(FormationResource formation);
}
