package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.AbstractUnitTest;
import dev.agitrubard.couriertracking.model.Location;
import dev.agitrubard.couriertracking.model.LocationBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class CourierDistanceServiceImplTest extends AbstractUnitTest {

    @InjectMocks
    CourierDistanceServiceImpl courierDistanceService;


    @Test
    void givenSameLocations_whenCalculateDistanceInKilometers_thenReturnDistanceAs0() {

        // Given
        Location mockFirstLocation = new LocationBuilder()
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();
        Location mockSecondLocation = new LocationBuilder()
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();

        // When
        double distance = courierDistanceService
                .calculateDistanceInKilometers(mockFirstLocation, mockSecondLocation);

        // Then
        Assertions.assertEquals(0.0, distance);
    }

    @Test
    void givenDifferentLocations_whenCalculateDistanceInKilometers_thenReturnDistance10KM() {

        // Given
        Location mockFirstLocation = new LocationBuilder()
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();
        Location mockSecondLocation = new LocationBuilder()
                .withLatitude(41.090000)
                .withLongitude(29.000000)
                .build();

        // When
        double distance = courierDistanceService
                .calculateDistanceInKilometers(mockFirstLocation, mockSecondLocation);

        // Then
        Assertions.assertTrue(10.0 <= distance && distance <= 10.1);
    }

}
