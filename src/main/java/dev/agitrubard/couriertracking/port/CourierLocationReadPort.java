package dev.agitrubard.couriertracking.port;

import dev.agitrubard.couriertracking.model.CourierLocation;

import java.util.Optional;
import java.util.UUID;

public interface CourierLocationReadPort {

    Optional<CourierLocation> findLastByCourierId(UUID courierId);

}
