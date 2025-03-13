package dev.agitrubard.couriertracking.exception;

import java.io.Serial;

public abstract class AbstractNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8232946084881268560L;

    protected AbstractNotFoundException(final String message) {
        super(message);
    }

}
