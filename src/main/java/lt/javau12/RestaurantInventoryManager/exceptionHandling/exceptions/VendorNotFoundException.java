package lt.javau12.RestaurantInventoryManager.exceptionHandling.exceptions;

import org.springframework.http.HttpStatus;

public class VendorNotFoundException extends BaseException{
    public VendorNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
