package lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException{

    public ProductNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
