package uno.cod.platform.server.rest.advice;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@ControllerAdvice(annotations = RestController.class)
public class NoSuchElementAdvice {

    /**
     * thrown on errors on the data layer (e.g. constraint key exception)
     */
    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The requested element does not exist")
    public void handleNoSuchElement() {}
}
