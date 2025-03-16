package dev.agitrubard.couriertracking.service;

import dev.agitrubard.couriertracking.model.CourierLocation;

import java.util.UUID;

public interface CourierStoreEntryTrackingService {

    void save(UUID courierId, CourierLocation currentCourierLocation);

}
