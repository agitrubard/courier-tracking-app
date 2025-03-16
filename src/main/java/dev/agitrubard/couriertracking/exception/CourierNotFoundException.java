package dev.agitrubard.couriertracking.exception;

import java.io.Serial;
import java.util.UUID;

public final class CourierNotFoundException extends AbstractNotFoundException {

    @Serial
    private static final long serialVersionUID = -5310854553164694465L;

    public CourierNotFoundException(UUID id) {
        super("courier not found by id: " + id);
    }

}
