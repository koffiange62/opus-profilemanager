package ci.jubile.joc.opusprofilemanager.v1.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter @Setter @Builder
public class DomainResource{

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @NotBlank(message = "This field is mandatory")
    @Size(min = 3, max = 3, message = "Must contain only 3 caracters")
    public String code;
    @NotBlank(message = "This field is mandatory")
    @Size(min = 3, max = 15, message = "Must have at least 3 and less than 15 caracters")
    public String libelle;

    @Override
    public String toString() {
        return null;
    }
}
