package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.AbstractUnitTest;
import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.CourierLocationBuilder;
import dev.agitrubard.couriertracking.model.CourierStoreEntry;
import dev.agitrubard.couriertracking.model.CourierStoreEntryBuilder;
import dev.agitrubard.couriertracking.model.Location;
import dev.agitrubard.couriertracking.model.Store;
import dev.agitrubard.couriertracking.model.StoreBuilder;
import dev.agitrubard.couriertracking.port.CourierStoreEntryReadPort;
import dev.agitrubard.couriertracking.port.CourierStoreEntrySavePort;
import dev.agitrubard.couriertracking.port.StoreReadPort;
import dev.agitrubard.couriertracking.service.CourierDistanceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class CourierStoreEntryTrackingServiceImplTest extends AbstractUnitTest {

    @InjectMocks
    CourierStoreEntryTrackingServiceImpl courierStoreEntryTrackingService;

    @Mock
    CourierDistanceService courierDistanceService;

    @Mock
    CourierStoreEntrySavePort courierStoreEntrySavePort;

    @Mock
    CourierStoreEntryReadPort courierStoreEntryReadPort;

    @Mock
    StoreReadPort storeReadPort;


    @Test
    void givenValidCourierIdAndCurrentCourierLocation_whenItsAFirstEntryInOneMinutesAndUnder100MeterNearFromStoreA_thenLogEntry() {

        // Given
        UUID mockCourierId = UUID.fromString("773c512a-36c5-42f2-82d5-a659146a8d02");
        CourierLocation mockCurrentCourierLocation = new CourierLocationBuilder()
                .withValidValues()
                .withCourierId(mockCourierId)
                .withLatitude(41.000450)
                .withLongitude(29.000000)
                .build();

        // When
        Mockito.when(courierStoreEntryReadPort.findLastByCourierId(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        Store mockStoreA = new StoreBuilder()
                .withValidValues()
                .withName("Store A")
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();
        Store mockStoreB = new StoreBuilder()
                .withValidValues()
                .withName("Store B")
                .withLatitude(41.018000)
                .withLongitude(29.000000)
                .build();
        List<Store> mockStores = List.of(mockStoreA, mockStoreB);
        Mockito.when(storeReadPort.findAll())
                .thenReturn(mockStores);

        Mockito.when(courierDistanceService.calculateDistanceInKilometers(mockCurrentCourierLocation.getLocation(), mockStoreA.getLocation()))
                .thenReturn(0.05);

        Mockito.when(courierDistanceService.calculateDistanceInKilometers(mockCurrentCourierLocation.getLocation(), mockStoreB.getLocation()))
                .thenReturn(0.15);

        Mockito.doNothing()
                .when(courierStoreEntrySavePort)
                .save(Mockito.any(CourierStoreEntry.class));

        // Then
        courierStoreEntryTrackingService.save(mockCourierId, mockCurrentCourierLocation);

        // Verify
        Mockito.verify(courierStoreEntryReadPort, Mockito.times(1))
                .findLastByCourierId(Mockito.any(UUID.class));

        Mockito.verify(storeReadPort, Mockito.times(1))
                .findAll();

        Mockito.verify(courierDistanceService, Mockito.times(2))
                .calculateDistanceInKilometers(Mockito.any(Location.class), Mockito.any(Location.class));

        Mockito.verify(courierStoreEntrySavePort, Mockito.times(1))
                .save(Mockito.any(CourierStoreEntry.class));
    }

    @Test
    void givenValidCourierIdAndCurrentCourierLocation_whenItsSecondEntryInOneMinutes_thenDoNotLogEntry() {

        // Given
        UUID mockCourierId = UUID.fromString("b4e6adc8-5ecd-432e-9f07-e87a8981286d");
        CourierLocation mockCurrentCourierLocation = new CourierLocationBuilder()
                .withValidValues()
                .withCourierId(mockCourierId)
                .withLatitude(41.000450)
                .withLongitude(29.000000)
                .withCreatedAt(LocalDateTime.now())
                .build();

        // When
        Store mockStoreA = new StoreBuilder()
                .withValidValues()
                .withName("Store A")
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();
        CourierStoreEntry mockCourierStoreEntry = new CourierStoreEntryBuilder()
                .withValidValues()
                .withCourierId(mockCourierId)
                .withStoreId(mockStoreA.getId())
                .withCreatedAt(LocalDateTime.now().minusSeconds(30))
                .build();
        Mockito.when(courierStoreEntryReadPort.findLastByCourierId(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(mockCourierStoreEntry));

        // Then
        courierStoreEntryTrackingService.save(mockCourierId, mockCurrentCourierLocation);

        // Verify
        Mockito.verify(courierStoreEntryReadPort, Mockito.times(1))
                .findLastByCourierId(Mockito.any(UUID.class));

        Mockito.verify(storeReadPort, Mockito.never())
                .findAll();

        Mockito.verify(courierDistanceService, Mockito.never())
                .calculateDistanceInKilometers(Mockito.any(Location.class), Mockito.any(Location.class));

        Mockito.verify(courierStoreEntrySavePort, Mockito.never())
                .save(Mockito.any(CourierStoreEntry.class));
    }

    @Test
    void givenValidCourierIdAndCurrentCourierLocation_whenItIsNotUnder100MeterNearFromAnyStores_thenDoNotLogEntry() {

        // Given
        UUID mockCourierId = UUID.fromString("9cbd08a6-3d92-4639-9b8e-3f4a0bdf51df");
        CourierLocation mockCurrentCourierLocation = new CourierLocationBuilder()
                .withValidValues()
                .withCourierId(mockCourierId)
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();

        // When
        Mockito.when(courierStoreEntryReadPort.findLastByCourierId(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        Store mockStoreA = new StoreBuilder()
                .withValidValues()
                .withName("Store A")
                .withLatitude(41.090000)
                .withLongitude(29.000000)
                .build();
        Store mockStoreB = new StoreBuilder()
                .withValidValues()
                .withName("Store B")
                .withLatitude(41.018000)
                .withLongitude(29.000000)
                .build();
        List<Store> mockStores = List.of(mockStoreA, mockStoreB);
        Mockito.when(storeReadPort.findAll())
                .thenReturn(mockStores);

        Mockito.when(courierDistanceService.calculateDistanceInKilometers(mockCurrentCourierLocation.getLocation(), mockStoreA.getLocation()))
                .thenReturn(10.0);

        Mockito.when(courierDistanceService.calculateDistanceInKilometers(mockCurrentCourierLocation.getLocation(), mockStoreB.getLocation()))
                .thenReturn(2.0);

        // Then
        courierStoreEntryTrackingService.save(mockCourierId, mockCurrentCourierLocation);

        // Verify
        Mockito.verify(courierStoreEntryReadPort, Mockito.times(1))
                .findLastByCourierId(Mockito.any(UUID.class));

        Mockito.verify(storeReadPort, Mockito.times(1))
                .findAll();

        Mockito.verify(courierDistanceService, Mockito.times(2))
                .calculateDistanceInKilometers(Mockito.any(Location.class), Mockito.any(Location.class));

        Mockito.verify(courierStoreEntrySavePort, Mockito.never())
                .save(Mockito.any(CourierStoreEntry.class));
    }

}
