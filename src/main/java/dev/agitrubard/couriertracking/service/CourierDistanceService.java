package dev.agitrubard.couriertracking.service;

import dev.agitrubard.couriertracking.model.CourierLocation;

public interface CourierDistanceService {

    double calculateDistanceInKilometers(CourierLocation currentCourierLocation, CourierLocation lastCourierLocation);

}
