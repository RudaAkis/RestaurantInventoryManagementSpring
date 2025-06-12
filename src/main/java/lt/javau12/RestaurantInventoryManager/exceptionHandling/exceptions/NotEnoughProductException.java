package lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions;

import org.springframework.http.HttpStatus;

public class NotEnoughProductException extends BaseException{
    public NotEnoughProductException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
