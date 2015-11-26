package com.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public final class GameException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public GameException() {
        super();
    }
    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
    public GameException(String message) {
        super(message);
    }
    public GameException(Throwable cause) {
        super(cause);
    }
}
