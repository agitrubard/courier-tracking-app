package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.AbstractUnitTest;
import dev.agitrubard.couriertracking.model.Courier;
import dev.agitrubard.couriertracking.model.CourierBuilder;
import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.CourierLocationBuilder;
import dev.agitrubard.couriertracking.model.Location;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequestBuilder;
import dev.agitrubard.couriertracking.port.CourierLocationReadPort;
import dev.agitrubard.couriertracking.port.CourierLocationSavePort;
import dev.agitrubard.couriertracking.port.CourierReadPort;
import dev.agitrubard.couriertracking.port.CourierSavePort;
import dev.agitrubard.couriertracking.service.CourierDistanceService;
import dev.agitrubard.couriertracking.service.CourierStoreEntryTrackingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

class CourierTrackingServiceImplTest extends AbstractUnitTest {

    @InjectMocks
    CourierTrackingServiceImpl courierTrackingService;

    @Mock
    CourierSavePort courierSavePort;

    @Mock
    CourierReadPort courierReadPort;

    @Mock
    CourierLocationSavePort courierLocationSavePort;

    @Mock
    CourierLocationReadPort courierLocationReadPort;

    @Mock
    CourierDistanceService courierDistanceService;

    @Mock
    CourierStoreEntryTrackingService courierStoreEntryTrackingService;


    @Test
    void givenValidCourierLocationSaveRequest_whenFirstCourierLocationAndCourierSaved_thenLogCourierEntry() {

        // Given
        CourierLocationSaveRequest mockSaveRequest = new CourierLocationSaveRequestBuilder()
                .withValidValues()
                .withTime(LocalDateTime.now())
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();

        // When
        Mockito.when(courierLocationReadPort.findLastByCourierId(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        Mockito.doNothing()
                .when(courierLocationSavePort)
                .save(Mockito.any(CourierLocation.class));

        Mockito.when(courierReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        Mockito.doNothing()
                .when(courierSavePort)
                .save(Mockito.any(Courier.class));

        Mockito.doNothing()
                .when(courierStoreEntryTrackingService)
                .save(Mockito.any(UUID.class), Mockito.any(CourierLocation.class));

        // Then
        courierTrackingService.saveLocation(mockSaveRequest);

        // Verify
        Mockito.verify(courierLocationReadPort, Mockito.times(1))
                .findLastByCourierId(Mockito.any(UUID.class));

        Mockito.verify(courierLocationSavePort, Mockito.times(1))
                .save(Mockito.any(CourierLocation.class));

        Mockito.verify(courierDistanceService, Mockito.never())
                .calculateDistanceInKilometers(Mockito.any(Location.class), Mockito.any(Location.class));

        Mockito.verify(courierReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));

        Mockito.verify(courierSavePort, Mockito.times(1))
                .save(Mockito.any(Courier.class));

        Mockito.verify(courierStoreEntryTrackingService, Mockito.times(1))
                .save(Mockito.any(UUID.class), Mockito.any(CourierLocation.class));
    }

    @Test
    void givenValidCourierLocationSaveRequest_whenSecondCourierLocationAndCourierSaved_thenLogCourierEntry() {

        // Given
        CourierLocationSaveRequest mockSaveRequest = new CourierLocationSaveRequestBuilder()
                .withValidValues()
                .withTime(LocalDateTime.now())
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();

        // When
        CourierLocation mockLastCourierLocation = new CourierLocationBuilder()
                .withValidValues()
                .withCourierId(mockSaveRequest.getCourierId())
                .withLatitude(41.000450)
                .withLongitude(29.000000)
                .withCreatedAt(LocalDateTime.now().minusSeconds(2))
                .build();
        Mockito.when(courierLocationReadPort.findLastByCourierId(mockSaveRequest.getCourierId()))
                .thenReturn(Optional.of(mockLastCourierLocation));

        Mockito.doNothing()
                .when(courierLocationSavePort)
                .save(Mockito.any(CourierLocation.class));

        Mockito.when(courierDistanceService.calculateDistanceInKilometers(Mockito.any(Location.class), Mockito.any(Location.class)))
                .thenReturn(0.05);

        Courier mockCourier = new CourierBuilder()
                .withValidValues()
                .withId(mockSaveRequest.getCourierId())
                .withTotalDistanceKilometers(1.5)
                .build();
        Mockito.when(courierReadPort.findById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(mockCourier));

        Mockito.doNothing()
                .when(courierSavePort)
                .save(Mockito.any(Courier.class));

        Mockito.doNothing()
                .when(courierStoreEntryTrackingService)
                .save(Mockito.any(UUID.class), Mockito.any(CourierLocation.class));

        // Then
        courierTrackingService.saveLocation(mockSaveRequest);

        // Verify
        Mockito.verify(courierLocationReadPort, Mockito.times(1))
                .findLastByCourierId(Mockito.any(UUID.class));

        Mockito.verify(courierLocationSavePort, Mockito.times(1))
                .save(Mockito.any(CourierLocation.class));

        Mockito.verify(courierDistanceService, Mockito.times(1))
                .calculateDistanceInKilometers(Mockito.any(Location.class), Mockito.any(Location.class));

        Mockito.verify(courierReadPort, Mockito.times(1))
                .findById(Mockito.any(UUID.class));

        Mockito.verify(courierSavePort, Mockito.times(1))
                .save(Mockito.any(Courier.class));

        Mockito.verify(courierStoreEntryTrackingService, Mockito.times(1))
                .save(Mockito.any(UUID.class), Mockito.any(CourierLocation.class));
    }

}
