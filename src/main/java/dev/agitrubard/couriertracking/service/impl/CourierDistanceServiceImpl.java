package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.model.Location;
import dev.agitrubard.couriertracking.service.CourierDistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CourierDistanceServiceImpl implements CourierDistanceService {

    private static final double EARTH_RADIUS_KM = 6371.0;

    @Override
    public Double calculateDistanceInKilometers(final Location firstLocation, final Location secondLocation) {
        return this.haversineDistance(firstLocation, secondLocation);
    }

    private double haversineDistance(final Location firstLocation, final Location secondLocation) {

        double latitudeFirst = firstLocation.getLatitude();
        double longitudeFirst = firstLocation.getLongitude();
        double latitudeSecond = secondLocation.getLatitude();
        double longitudeSecond = secondLocation.getLongitude();

        double dLat = Math.toRadians(latitudeSecond - latitudeFirst);
        double dLon = Math.toRadians(longitudeSecond - longitudeFirst);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                   + Math.cos(Math.toRadians(latitudeFirst)) * Math.cos(Math.toRadians(latitudeSecond))
                     * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
