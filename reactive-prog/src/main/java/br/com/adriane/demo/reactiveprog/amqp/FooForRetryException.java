package br.com.adriane.demo.reactiveprog.amqp;

public class FooForRetryException extends RuntimeException{

    public FooForRetryException(String message) {
        super(message);
    }

    public FooForRetryException(String message, Throwable cause) {
        super(message, cause);
    }
}
