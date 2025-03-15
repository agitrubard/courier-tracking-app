package dev.agitrubard.couriertracking.port;

import dev.agitrubard.couriertracking.model.CourierStoreEntry;

import java.util.Optional;
import java.util.UUID;

public interface CourierStoreEntryReadPort {

    Optional<CourierStoreEntry> findLastByCourierId(UUID courierId);

}
