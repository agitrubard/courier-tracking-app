package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.model.Courier;
import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;
import dev.agitrubard.couriertracking.port.CourierLocationReadPort;
import dev.agitrubard.couriertracking.port.CourierLocationSavePort;
import dev.agitrubard.couriertracking.port.CourierReadPort;
import dev.agitrubard.couriertracking.port.CourierSavePort;
import dev.agitrubard.couriertracking.service.CourierDistanceService;
import dev.agitrubard.couriertracking.service.CourierTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class CourierTrackingServiceImpl implements CourierTrackingService {

    private final CourierSavePort courierSavePort;
    private final CourierReadPort courierReadPort;
    private final CourierLocationSavePort courierLocationSavePort;
    private final CourierLocationReadPort courierLocationReadPort;
    private final CourierDistanceService courierDistanceService;

    @Override
    public void saveLocation(final CourierLocationSaveRequest saveRequest) {

        final UUID courierId = saveRequest.getCourierId();

        final Optional<CourierLocation> lastCourierLocation = courierLocationReadPort.findLastByCourierId(courierId);

        final CourierLocation currentCourierLocation = this
                .saveCourierLocation(courierId, saveRequest.getLatitude(), saveRequest.getLongitude());

        this.saveOrUpdateCourier(courierId, lastCourierLocation, currentCourierLocation);
    }

    private void saveOrUpdateCourier(final UUID courierId,
                                     final Optional<CourierLocation> lastCourierLocation,
                                     final CourierLocation currentCourierLocation) {

        if (lastCourierLocation.isEmpty()) {
            this.saveCourier(courierId, 0D);
            return;
        }

        final Double lastDistanceKilometers = courierDistanceService
                .calculateDistanceInKilometers(currentCourierLocation, lastCourierLocation.get());

        final Optional<Courier> courierFromDatabase = courierReadPort.findById(courierId);
        if (courierFromDatabase.isEmpty()) {
            this.saveCourier(courierId, lastDistanceKilometers);
            return;
        }

        this.updateCourier(courierFromDatabase.get(), lastDistanceKilometers);
    }

    private CourierLocation saveCourierLocation(final UUID courierId,
                                                final Double latitude,
                                                final Double longitude) {

        final CourierLocation courierCurrentLocation = CourierLocation.builder()
                .courierId(courierId)
                .latitude(latitude)
                .longitude(longitude)
                .build();
        courierLocationSavePort.save(courierCurrentLocation);
        return courierCurrentLocation;
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
