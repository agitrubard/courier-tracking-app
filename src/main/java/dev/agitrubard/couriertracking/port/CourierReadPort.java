package dev.agitrubard.couriertracking.port;

import dev.agitrubard.couriertracking.model.Courier;

import java.util.Optional;
import java.util.UUID;

public interface CourierReadPort {

    Optional<Courier> findById(UUID id);

}
