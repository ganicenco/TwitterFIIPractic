package ro.itschool.exceptions;

public class UserNotFoundException extends RuntimeException {
    private String message;
    public UserNotFoundException (String message){
        super(message);
    }
}
