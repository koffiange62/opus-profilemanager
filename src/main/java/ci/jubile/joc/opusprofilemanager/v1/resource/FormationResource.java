package ci.jubile.joc.opusprofilemanager.v1.resource;

import ci.jubile.joc.opusprofilemanager.model.model;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter @Getter
public class FormationResource extends model {
    @Getter @Setter
    private String id;
    @Getter @Setter
    private LocalDateTime createdAt;
    @Getter @Setter
    private LocalDateTime updatedAt;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 30, message = "Must have at least 2 and less than 30 caracters")
    public String diploma;
    @Setter @Getter
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 50, message = "Must have at least 2 and less than 50 caracters")
    public String school;
    @Setter @Getter
    @NotBlank(message = "This field is mandatory")
    public Integer year;
    @Setter @Getter
    public DomainResource domain;

    public FormationResource(String id, String diploma, String school, Integer year) {
        this.setId(id);
        this.diploma = diploma;
        this.school = school;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "diploma='" + diploma + '\'' +
                ", school='" + school + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
