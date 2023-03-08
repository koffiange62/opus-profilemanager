package ci.jubile.joc.opusprofilemanager.v1.mapper;

import ci.jubile.joc.opusprofilemanager.model.Profile;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "password", ignore = true)
    ProfileResource profileToProfileResource(Profile profile);

    @Mapping(target = "formations", ignore = true)
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "competences", ignore = true)
    @Mapping(target = "status", ignore = true)
    Profile profileResourceToProfile(ProfileResource profileResource);
}
