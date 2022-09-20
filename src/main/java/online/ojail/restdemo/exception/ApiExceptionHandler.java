package online.ojail.restdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Mo. Ojail
 * created: 2022-09-20
 */

@ControllerAdvice
public record ApiExceptionHandler() {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleApiExceptions(Exception e){

        String exceptionName = e.getClass().getSimpleName();

        HttpStatus httpStatus =
                exceptionName.equals("NoHandlerFoundException") ||
                exceptionName.equals("ResourceNotFoundException") ?
                HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                exceptionName,
                e.getMessage()
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }
}

record ApiException(String exception, String message){}
