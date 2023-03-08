package ci.jubile.joc.opusprofilemanager.v1.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DomainResource{
    @Setter @Getter
    @NotBlank(message = "This field is mandatory")
    @Size(min = 3, max = 3, message = "Must contain only 3 caracters")
    public String code;
    @Setter @Getter
    @NotBlank(message = "This field is mandatory")
    @Size(min = 3, max = 15, message = "Must have at least 3 and less than 15 caracters")
    public String libelle;

    @Override
    public String toString() {
        return null;
    }
}
