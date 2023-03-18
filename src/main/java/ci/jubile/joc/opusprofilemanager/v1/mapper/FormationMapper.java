package ci.jubile.joc.opusprofilemanager.v1.mapper;

import ci.jubile.joc.opusprofilemanager.model.Formation;
import ci.jubile.joc.opusprofilemanager.v1.resource.FormationResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormationMapper {
    @Mapping(target = "domain.id", source = "domain.id", ignore = true)
    @Mapping(target = "domain.createdAt", source = "domain.createdAt", ignore = true)
    @Mapping(target = "domain.updatedAt", source = "domain.updatedAt", ignore = true)
    FormationResource formationToFormationResource(Formation formation);

    @Mapping(target = "domain.id", source = "domain.id", ignore = true)
    @Mapping(target = "domain.createdAt", source = "domain.createdAt", ignore = true)
    @Mapping(target = "domain.updatedAt", source = "domain.updatedAt", ignore = true)
    Formation formationResourceToFormation(FormationResource formation);
    List<FormationResource> formationListToResourceList(List<Formation> formations);
}
