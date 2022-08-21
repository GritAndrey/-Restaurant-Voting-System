package ru.gritandrey.restaurantvotingsystem.exception;

public record ErrorInfo(StringBuffer url, ErrorType type, String detail) {
}