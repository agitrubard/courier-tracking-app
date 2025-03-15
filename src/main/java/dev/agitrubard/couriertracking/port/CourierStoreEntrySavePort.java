package dev.agitrubard.couriertracking.port;

import dev.agitrubard.couriertracking.model.CourierStoreEntry;

public interface CourierStoreEntrySavePort {

    void save(CourierStoreEntry courierStoreEntry);

}
