package ci.jubile.joc.opusprofilemanager.v1.resource;

import ci.jubile.joc.opusprofilemanager.v1.enumeration.ErrorLocation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter @Setter
public class ApiErrorResource {
    private String code;
    private String message;
    private ErrorLocation errorLocation;
    private HttpStatus status;
    private Map<String, String> errors;

    public ApiErrorResource(String code, String message, ErrorLocation errorLocation, HttpStatus status, Map<String, String> errors) {
        this.code = code;
        this.message = message;
        this.errorLocation = errorLocation;
        this.status = status;
        this.errors = errors;
    }
}
