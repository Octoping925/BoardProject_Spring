package com.octoping.boardproject_spring.exception;

public class NoMovieFoundException extends Exception {
    public NoMovieFoundException() {

    }

    public NoMovieFoundException(String message) {
        super(message);
    }

    public NoMovieFoundException(String message, long movieId) {
        super(String.format("%s %ld", message, movieId));
    }
}