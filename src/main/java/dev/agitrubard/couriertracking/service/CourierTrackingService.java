package dev.agitrubard.couriertracking.service;

import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;

public interface CourierTrackingService {

    void saveLocation(CourierLocationSaveRequest saveRequest);

}
