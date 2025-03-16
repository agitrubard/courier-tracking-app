package dev.agitrubard.couriertracking.service;

import dev.agitrubard.couriertracking.model.Location;

public interface CourierDistanceService {

    Double calculateDistanceInKilometers(Location firstLocation, Location secondLocation);

}
