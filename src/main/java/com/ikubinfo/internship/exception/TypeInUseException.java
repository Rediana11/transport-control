package com.ikubinfo.internship.exception;

public class TypeInUseException extends RuntimeException {

    public TypeInUseException() {
        super();
    }

    public TypeInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeInUseException(String message) {
        super(message);
    }

    public TypeInUseException(Throwable cause) {
        super(cause);
    }
}
