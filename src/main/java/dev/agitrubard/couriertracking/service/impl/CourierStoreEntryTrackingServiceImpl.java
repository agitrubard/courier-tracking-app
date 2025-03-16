package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.CourierStoreEntry;
import dev.agitrubard.couriertracking.model.Store;
import dev.agitrubard.couriertracking.port.CourierStoreEntryReadPort;
import dev.agitrubard.couriertracking.port.CourierStoreEntrySavePort;
import dev.agitrubard.couriertracking.port.StoreReadPort;
import dev.agitrubard.couriertracking.service.CourierDistanceService;
import dev.agitrubard.couriertracking.service.CourierStoreEntryTrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class CourierStoreEntryTrackingServiceImpl implements CourierStoreEntryTrackingService {

    private final CourierDistanceService courierDistanceService;
    private final CourierStoreEntrySavePort courierStoreEntrySavePort;
    private final CourierStoreEntryReadPort courierStoreEntryReadPort;
    private final StoreReadPort storeReadPort;

    @Async
    @Override
    public void save(final UUID courierId,
                     final CourierLocation currentCourierLocation) {

        final boolean isLoggingNotRequired = this.isLoggingNotRequired(courierId, currentCourierLocation);
        if (isLoggingNotRequired) {
            log.trace("Courier Store Entry logging is not required for courier with id: {}", courierId);
            return;
        }

        log.trace("Stores will be checked for courier with id: {}", courierId);

        final List<Store> stores = storeReadPort.findAll();
        for (Store store : stores) {

            final double distanceToStoreKilometers = courierDistanceService
                    .calculateDistanceInKilometers(currentCourierLocation.getLocation(), store.getLocation());
            if (distanceToStoreKilometers > 0.1) {
                continue;
            }

            final CourierStoreEntry courierStoreEntry = CourierStoreEntry.builder()
                    .courierId(courierId)
                    .storeId(store.getId())
                    .createdAt(LocalDateTime.now())
                    .build();
            courierStoreEntrySavePort.save(courierStoreEntry);
            log.debug("Courier near to store with storeId: {} and courierId: {}", store.getId(), courierId);
        }
    }

    private boolean isLoggingNotRequired(final UUID courierId,
                                         final CourierLocation currentCourierLocation) {

        final Optional<CourierStoreEntry> lastCourierStoreEntry = courierStoreEntryReadPort
                .findLastByCourierId(courierId);

        return lastCourierStoreEntry
                .map(courierStoreEntry -> courierStoreEntry.getCreatedAt()
                        .plusMinutes(1)
                        .isAfter(currentCourierLocation.getCreatedAt())
                )
                .orElse(false);
    }

}
