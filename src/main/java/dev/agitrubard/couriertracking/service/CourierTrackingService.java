package dev.agitrubard.couriertracking.service;

import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;

import java.util.UUID;

public interface CourierTrackingService {

    void saveLocation(CourierLocationSaveRequest saveRequest);

    Double findTotalDistanceKilometers(UUID courierId);

}
