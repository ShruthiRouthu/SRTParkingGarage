package srtparkinggarage;

/**
 *
 * @author Shruthi Routhu
 */
public class CustomIllegalArgumentException extends Exception {

    public CustomIllegalArgumentException() {
    }

    public CustomIllegalArgumentException(String message) {
        super(message);
    }

    public CustomIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomIllegalArgumentException(Throwable cause) {
        super(cause);
    }

    public CustomIllegalArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
}
