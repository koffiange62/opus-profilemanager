package ci.jubile.joc.opusprofilemanager.v1.error;

import ci.jubile.joc.opusprofilemanager.v1.enumeration.ErrorLocation;
import ci.jubile.joc.opusprofilemanager.v1.exception.PasswordHandlerException;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.resource.ApiErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;


@ControllerAdvice
public class GlobalRestExceptionHandler {
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> handleNotFound(Exception ex){
        ApiErrorResource apiErrorResource = new ApiErrorResource("opus.internal.error.message",
                                            ex.getMessage(), ErrorLocation.INTERNE_OPUS_ERROR, HttpStatus.NOT_FOUND, null);
        return new ResponseEntity<>(apiErrorResource, apiErrorResource.getStatus());
    }

    @ExceptionHandler({IllegalArgumentException.class, PasswordHandlerException.class})
    public ResponseEntity<Object> handleBadParameter(Exception ex){
        ApiErrorResource apiErrorResource = new ApiErrorResource("opus.internal.error.message",
                ex.getMessage(), ErrorLocation.INTERNE_OPUS_ERROR, HttpStatus.BAD_REQUEST, null);
        return new ResponseEntity<>(apiErrorResource, apiErrorResource.getStatus());
    }

    @ExceptionHandler({ProfileNotFoundException.class})
    public ResponseEntity<Object> handleProfileNotFound(Exception ex){
        ApiErrorResource apiErrorResource = new ApiErrorResource("opus.internal.error.message",
                ex.getMessage(), ErrorLocation.INTERNE_OPUS_ERROR, HttpStatus.NOT_FOUND, null);
        return new ResponseEntity<>(apiErrorResource, apiErrorResource.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ApiErrorResource apiErrorResource = new ApiErrorResource("opus.internal.error.message",
                "Object validation error", ErrorLocation.INTERNE_OPUS_ERROR, HttpStatus.BAD_REQUEST, errors);

        return ResponseEntity.badRequest().body(apiErrorResource);
    }
}
