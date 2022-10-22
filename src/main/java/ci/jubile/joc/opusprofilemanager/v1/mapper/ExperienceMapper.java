package ci.jubile.joc.opusprofilemanager.v1.mapper;

import ci.jubile.joc.opusprofilemanager.domain.Experience;
import ci.jubile.joc.opusprofilemanager.v1.resource.ExperienceResource;
import org.mapstruct.Mapper;

@Mapper
public interface ExperienceMapper {
    ExperienceResource experienceToExperienceResource(Experience experience);
    Experience experienceResourceToExperience(ExperienceResource resource);
}
