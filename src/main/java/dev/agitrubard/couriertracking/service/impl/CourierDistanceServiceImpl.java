package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.service.CourierDistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CourierDistanceServiceImpl implements CourierDistanceService {

    private static final double EARTH_RADIUS_KM = 6371.0;

    @Override
    public double calculate(final CourierLocation currentCourierLocation,
                            final CourierLocation lastCourierLocation) {

        final double currentLatitude = currentCourierLocation.getLatitude();
        final double currentLongitude = currentCourierLocation.getLongitude();
        final double lastLatitude = lastCourierLocation.getLatitude();
        final double lastLongitude = lastCourierLocation.getLongitude();

        final double dLatitude = Math.toRadians(lastLatitude - currentLatitude);
        final double dLongitude = Math.toRadians(lastLongitude - currentLongitude);

        final double a = Math.sin(dLatitude / 2) * Math.sin(dLatitude / 2)
                         + Math.cos(Math.toRadians(currentLatitude)) * Math.cos(Math.toRadians(lastLatitude))
                           * Math.sin(dLongitude / 2) * Math.sin(dLongitude / 2);
        final double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

}
