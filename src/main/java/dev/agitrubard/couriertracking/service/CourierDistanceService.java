package dev.agitrubard.couriertracking.service;

import dev.agitrubard.couriertracking.model.CourierLocation;

public interface CourierDistanceService {

    double calculate(CourierLocation currentCourierLocation, CourierLocation lastCourierLocation);

}
