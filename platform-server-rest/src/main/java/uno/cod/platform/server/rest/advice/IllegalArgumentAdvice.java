package uno.cod.platform.server.rest.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(annotations = RestController.class)
public class IllegalArgumentAdvice extends AbstractLocalizedAdvice {

    @Autowired
    public IllegalArgumentAdvice(@Qualifier("exceptionMessageSource") ResourceBundleMessageSource messageSource) {
        super(messageSource);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(final Exception ex, WebRequest request) {
        return new ResponseEntity<>(getMessage(ex, request), HttpStatus.BAD_REQUEST);
    }
}
