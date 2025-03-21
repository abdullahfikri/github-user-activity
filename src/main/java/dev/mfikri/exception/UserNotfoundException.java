package dev.mfikri.exception;

public class UserNotfoundException extends RuntimeException{

    public UserNotfoundException() {
        super("User not found");
    }
}
