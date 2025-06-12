package lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions;

import org.springframework.http.HttpStatus;

public class UnitNotFoundException extends BaseException{
    public UnitNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
