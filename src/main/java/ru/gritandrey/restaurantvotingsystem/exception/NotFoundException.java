package ru.gritandrey.restaurantvotingsystem.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}