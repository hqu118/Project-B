package main;

public class InvalidPositionException extends RuntimeException{
    public InvalidPositionException() {
    }

    public InvalidPositionException(String msg) {
        super(msg);
    }
}
