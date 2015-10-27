package uno.cod.platform.server.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uno.cod.platform.server.core.exception.ResourceConflictException;

@ControllerAdvice(annotations = RestController.class)
public class ResourceConflictAdvice {

    @ExceptionHandler(value = ResourceConflictException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    public String handleResourceConflict(ResourceConflictException ex) {
        return ex.getMessage();
    }
}
