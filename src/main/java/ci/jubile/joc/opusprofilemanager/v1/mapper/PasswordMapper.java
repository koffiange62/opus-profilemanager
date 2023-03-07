package ci.jubile.joc.opusprofilemanager.v1.mapper;

import ci.jubile.joc.opusprofilemanager.model.Password;
import ci.jubile.joc.opusprofilemanager.v1.resource.PasswordResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PasswordMapper {
    @Mapping(target = "newPassword", ignore = true)
    PasswordResource passwordToPasswordResource(Password password);

    @Mapping(target = "passwordHistory", ignore = true)
    Password passwordResourceToPassword(PasswordResource passwordResource);
}
