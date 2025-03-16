package dev.agitrubard.couriertracking.port;

import dev.agitrubard.couriertracking.model.CourierLocation;

public interface CourierLocationSavePort {

    void save(CourierLocation courierLocation);

}
