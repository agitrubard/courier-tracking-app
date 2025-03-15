package dev.agitrubard.couriertracking.port.adapter;

import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.entity.CourierLocationEntity;
import dev.agitrubard.couriertracking.model.mapper.CourierLocationEntityToDomainMapper;
import dev.agitrubard.couriertracking.model.mapper.CourierLocationToEntityMapper;
import dev.agitrubard.couriertracking.port.CourierLocationReadPort;
import dev.agitrubard.couriertracking.port.CourierLocationSavePort;
import dev.agitrubard.couriertracking.repository.CourierLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class CourierLocationAdapter implements CourierLocationReadPort, CourierLocationSavePort {

    private final CourierLocationRepository courierLocationRepository;

    private final CourierLocationToEntityMapper courierLocationToEntityMapper = CourierLocationToEntityMapper.INSTANCE;
    private final CourierLocationEntityToDomainMapper courierLocationEntityToDomainMapper = CourierLocationEntityToDomainMapper.INSTANCE;


    @Override
    public Optional<CourierLocation> findLastByCourierId(UUID courierId) {
        return courierLocationRepository.findTopByCourierIdOrderByCreatedAtDesc(courierId)
                .map(courierLocationEntityToDomainMapper::map);
    }


    @Override
    public void save(final CourierLocation courierLocation) {
        final CourierLocationEntity courierLocationEntity = courierLocationToEntityMapper.map(courierLocation);
        courierLocationRepository.save(courierLocationEntity);
    }

}
