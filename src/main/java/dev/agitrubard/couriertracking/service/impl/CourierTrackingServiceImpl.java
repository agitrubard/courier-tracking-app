package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.model.Courier;
import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.Location;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;
import dev.agitrubard.couriertracking.port.CourierLocationReadPort;
import dev.agitrubard.couriertracking.port.CourierLocationSavePort;
import dev.agitrubard.couriertracking.port.CourierReadPort;
import dev.agitrubard.couriertracking.port.CourierSavePort;
import dev.agitrubard.couriertracking.service.CourierDistanceService;
import dev.agitrubard.couriertracking.service.CourierStoreEntryTrackingService;
import dev.agitrubard.couriertracking.service.CourierTrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class CourierTrackingServiceImpl implements CourierTrackingService {

    private final CourierSavePort courierSavePort;
    private final CourierReadPort courierReadPort;
    private final CourierLocationSavePort courierLocationSavePort;
    private final CourierLocationReadPort courierLocationReadPort;
    private final CourierDistanceService courierDistanceService;
    private final CourierStoreEntryTrackingService courierStoreEntryTrackingService;

    @Override
    public void saveLocation(final CourierLocationSaveRequest saveRequest) {

        final UUID courierId = saveRequest.getCourierId();

        final LocalDateTime time = saveRequest.getTime();
        log.trace("Courier location will be saved for courierId: {}, time: {}", courierId, time);

        final Optional<CourierLocation> lastCourierLocation = courierLocationReadPort.findLastByCourierId(courierId);

        final Location currentLocation = Location.builder()
                .latitude(saveRequest.getLatitude())
                .longitude(saveRequest.getLongitude())
                .build();
        final CourierLocation currentCourierLocation = this.saveCourierLocation(courierId, currentLocation, time);

        log.trace("Courier location saved for courierId: {}, time: {}", courierId, time);

        Double lastDistanceKilometers = 0D;
        if (lastCourierLocation.isPresent()) {
            lastDistanceKilometers = courierDistanceService.calculateDistanceInKilometers(
                    currentCourierLocation.getLocation(),
                    lastCourierLocation.get().getLocation()
            );
        }

        this.saveOrUpdateCourier(courierId, lastDistanceKilometers);

        courierStoreEntryTrackingService.save(courierId, currentCourierLocation);
    }

    private CourierLocation saveCourierLocation(final UUID courierId,
                                                final Location location,
                                                final LocalDateTime time) {

        final CourierLocation courierCurrentLocation = CourierLocation.builder()
                .courierId(courierId)
                .location(location)
                .createdAt(time)
                .build();
        courierLocationSavePort.save(courierCurrentLocation);
        return courierCurrentLocation;
    }

    private void saveOrUpdateCourier(final UUID courierId,
                                     final Double lastDistanceKilometers) {

        log.trace("Courier will be saved or updated for courierId: {}", courierId);

        final Optional<Courier> courierFromDatabase = courierReadPort.findById(courierId);
        if (courierFromDatabase.isEmpty()) {
            this.saveCourier(courierId, lastDistanceKilometers);
            log.trace("Courier saved for courierId: {}", courierId);
            return;
        }

        this.updateCourier(courierFromDatabase.get(), lastDistanceKilometers);
        log.trace("Courier updated for courierId: {}", courierId);
    }

    private void saveCourier(final UUID courierId,
                             final Double lastDistanceKilometers) {

        final Courier courier = Courier.builder()
                .id(courierId)
                .totalDistanceKilometers(lastDistanceKilometers)
                .build();
        courierSavePort.save(courier);
    }

    private void updateCourier(final Courier courier,
                               final Double totalDistanceKilometers) {

        courier.update(totalDistanceKilometers);
        courierSavePort.save(courier);
    }

}
