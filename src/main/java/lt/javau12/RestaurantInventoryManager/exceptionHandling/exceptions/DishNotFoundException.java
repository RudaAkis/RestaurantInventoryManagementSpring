package lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions;

import org.springframework.http.HttpStatus;

public class DishNotFoundException extends BaseException{
    public DishNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
