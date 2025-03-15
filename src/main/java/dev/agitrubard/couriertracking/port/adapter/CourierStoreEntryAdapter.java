package dev.agitrubard.couriertracking.port.adapter;

import dev.agitrubard.couriertracking.model.CourierStoreEntry;
import dev.agitrubard.couriertracking.model.entity.CourierStoreEntryEntity;
import dev.agitrubard.couriertracking.model.mapper.CourierStoreEntryEntityToDomainMapper;
import dev.agitrubard.couriertracking.model.mapper.CourierStoreEntryToEntityMapper;
import dev.agitrubard.couriertracking.port.CourierStoreEntryReadPort;
import dev.agitrubard.couriertracking.port.CourierStoreEntrySavePort;
import dev.agitrubard.couriertracking.repository.CourierStoreEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class CourierStoreEntryAdapter implements CourierStoreEntryReadPort, CourierStoreEntrySavePort {

    private final CourierStoreEntryRepository courierStoreEntryRepository;

    private final CourierStoreEntryToEntityMapper courierStoreEntryToEntityMapper = CourierStoreEntryToEntityMapper.INSTANCE;
    private final CourierStoreEntryEntityToDomainMapper courierStoreEntryEntityToDomainMapper = CourierStoreEntryEntityToDomainMapper.INSTANCE;


    @Override
    public Optional<CourierStoreEntry> findLastByCourierId(UUID courierId) {
        return courierStoreEntryRepository.findTopByCourierIdOrderByCreatedAtDesc(courierId)
                .map(courierStoreEntryEntityToDomainMapper::map);
    }


    @Override
    public void save(final CourierStoreEntry courierStoreEntry) {
        final CourierStoreEntryEntity courierStoreEntryEntity = courierStoreEntryToEntityMapper.map(courierStoreEntry);
        courierStoreEntryRepository.save(courierStoreEntryEntity);
    }

}
