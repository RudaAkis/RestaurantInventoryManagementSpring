package lt.javau12.RestaurantInventoryManager.exceptionHandling;

import lt.javau12.RestaurantInventoryManager.dtos.errorDtos.ErrorResponse;
import lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExeptionHandler{

    @ExceptionHandler(BaseException.class)//Used for all subclasses that extends the base exception
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException ex) {
        //Create a new response entity to pass to frontend with current time, HttpStatus code and the error message
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                ex.getStatus().value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        //Create a new hash map that will store fieldName as key and the error message as value
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
