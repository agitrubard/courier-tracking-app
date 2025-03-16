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
                .withLatitude(-6.90389)
                .withLongitude(107.61861)
                .build();
        Location mockSecondLocation = new LocationBuilder()
                .withLatitude(-6.90389)
                .withLongitude(107.61861)
                .build();

        // When
        double distance = courierDistanceService
                .calculateDistanceInKilometers(mockFirstLocation, mockSecondLocation);

        // Then
        Assertions.assertEquals(0.0, distance);
    }

    @Test
    void givenDifferentLocations_whenCalculateDistanceInKilometers_thenReturnDistance2KM() {

        // Given
        Location mockFirstLocation = new LocationBuilder()
                .withLatitude(-6.90389)
                .withLongitude(107.61861)
                .build();
        Location mockSecondLocation = new LocationBuilder()
                .withLatitude(-6.90389)
                .withLongitude(107.63861)
                .build();

        // When
        double distance = courierDistanceService
                .calculateDistanceInKilometers(mockFirstLocation, mockSecondLocation);

        // Then
        Assertions.assertTrue(distance > 2.0);
    }

}
