package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.model.Courier;
import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;
import dev.agitrubard.couriertracking.port.CourierLocationSavePort;
import dev.agitrubard.couriertracking.port.CourierReadPort;
import dev.agitrubard.couriertracking.port.CourierSavePort;
import dev.agitrubard.couriertracking.service.CourierTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class CourierTrackingServiceImpl implements CourierTrackingService {

    private final CourierSavePort courierSavePort;
    private final CourierReadPort courierReadPort;
    private final CourierLocationSavePort courierLocationSavePort;

    @Override
    public void saveLocation(final CourierLocationSaveRequest saveRequest) {

        final UUID courierId = saveRequest.getCourierId();
        CourierLocation courierCurrentLocation = CourierLocation.builder()
                .courierId(courierId)
                .latitude(saveRequest.getLatitude())
                .longitude(saveRequest.getLongitude())
                .build();
        courierLocationSavePort.save(courierCurrentLocation);

        final Optional<Courier> courierFromDatabase = courierReadPort.findById(courierId);
        if (courierFromDatabase.isEmpty()) {
            final Courier courier = Courier.builder()
                    .id(courierId)
                    .build();
            courierSavePort.save(courier);
            return;
        }

        courierFromDatabase.get().update();
        courierSavePort.save(courierFromDatabase.get());
    }

}
