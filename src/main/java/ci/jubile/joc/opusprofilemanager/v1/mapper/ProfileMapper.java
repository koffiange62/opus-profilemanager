package ci.jubile.joc.opusprofilemanager.v1.mapper;

import ci.jubile.joc.opusprofilemanager.domain.Profile;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "password", ignore = true)
    ProfileResource profileToProfileResource(Profile profile);
    Profile profileResourceToProfile(ProfileResource profile);
}
