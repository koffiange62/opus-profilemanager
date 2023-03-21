package ci.jubile.joc.opusprofilemanager.v1.resource;

import ci.jubile.joc.opusprofilemanager.model.model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter @Getter @Builder
public class FormationResource extends model {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 30, message = "Must have at least 2 and less than 30 caracters")
    public String diploma;

    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 50, message = "Must have at least 2 and less than 50 caracters")
    public String school;
    @NotBlank(message = "This field is mandatory")
    public Integer year;
    public DomainResource domain;

    @Override
    public String toString() {
        return "Formation{" +
                "diploma='" + diploma + '\'' +
                ", school='" + school + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
