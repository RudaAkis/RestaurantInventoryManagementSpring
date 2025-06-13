package lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions;

import org.springframework.http.HttpStatus;

public class PasswordsNotMatchingException extends BaseException{
    public PasswordsNotMatchingException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
