package ci.jubile.joc.opusprofilemanager.v1.error;

import ci.jubile.joc.opusprofilemanager.v1.enumeration.ErrorLocation;
import ci.jubile.joc.opusprofilemanager.v1.exception.ProfileNotFoundException;
import ci.jubile.joc.opusprofilemanager.v1.resource.ApiErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;


@ControllerAdvice
public class CostumRestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest webRequest){
        ApiErrorResource apiErrorResource = new ApiErrorResource("opus.interna.error.message",
                                                                ex.getMessage(), ErrorLocation.INTERNE_OPUS_ERROR, HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(apiErrorResource, apiErrorResource.getStatus());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleBadParameter(Exception ex, WebRequest webRequest){
        ApiErrorResource apiErrorResource = new ApiErrorResource("opus.interna.error.message",
                ex.getMessage(), ErrorLocation.INTERNE_OPUS_ERROR, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(apiErrorResource, apiErrorResource.getStatus());
    }

    @ExceptionHandler({ProfileNotFoundException.class})
    public ResponseEntity<Object> handleProfileNotFound(Exception ex, WebRequest webRequest){
        ApiErrorResource apiErrorResource = new ApiErrorResource("opus.interna.error.message",
                ex.getMessage(), ErrorLocation.INTERNE_OPUS_ERROR, HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(apiErrorResource, apiErrorResource.getStatus());
    }
}
