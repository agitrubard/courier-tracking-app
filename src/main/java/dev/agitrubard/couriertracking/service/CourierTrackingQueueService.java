package dev.agitrubard.couriertracking.service;

import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;

public interface CourierTrackingQueueService {

    void saveLocation(CourierLocationSaveRequest saveRequest);

}
