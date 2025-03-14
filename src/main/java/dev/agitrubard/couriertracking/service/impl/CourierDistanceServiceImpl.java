package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.service.CourierDistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CourierDistanceServiceImpl implements CourierDistanceService {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public double calculateDistanceInKilometers(final CourierLocation currentCourierLocation,
                                                final CourierLocation lastCourierLocation) {

        return this.haversineDistance(
                currentCourierLocation.getLatitude(),
                currentCourierLocation.getLongitude(),
                lastCourierLocation.getLatitude(),
                lastCourierLocation.getLongitude()
        );
    }

    private double haversineDistance(double latitudeFirst, double longitudeFirst,
                                     double latitudeSecond, double longitudeSecond) {

        double dLat = Math.toRadians(latitudeSecond - latitudeFirst);
        double dLon = Math.toRadians(longitudeSecond - longitudeFirst);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                   + Math.cos(Math.toRadians(latitudeFirst)) * Math.cos(Math.toRadians(latitudeSecond))
                     * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

}
