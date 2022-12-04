package ci.jubile.joc.opusprofilemanager.v1.resource;

import ci.jubile.joc.opusprofilemanager.v1.enumeration.ErrorLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter @Setter
public class ApiErrorResource {
    private String code;
    private String message;
    private ErrorLocation errorLocation;
    private HttpStatus status;
}
