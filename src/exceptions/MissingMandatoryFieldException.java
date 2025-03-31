package exceptions;

public class MissingMandatoryFieldException extends RuntimeException{
    public MissingMandatoryFieldException(String message) {
        super(message);
    }
}
