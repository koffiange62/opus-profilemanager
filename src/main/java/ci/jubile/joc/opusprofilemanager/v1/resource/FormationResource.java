package ci.jubile.joc.opusprofilemanager.v1.resource;

import ci.jubile.joc.opusprofilemanager.model.Domain;
import ci.jubile.joc.opusprofilemanager.model.model;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.Year;

@Setter @Getter
public class FormationResource extends model {
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 30, message = "Must have at least 2 and less than 30 caracters")
    public String diploma;
    @Setter @Getter
    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 50, message = "Must have at least 2 and less than 50 caracters")
    public String school;
    @Setter @Getter
    @NotBlank(message = "This field is mandatory")
    @PastOrPresent
    public Year year;
    @Setter @Getter
    public Domain domain;

    public FormationResource(String id, String diploma, String school, Year year) {
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
