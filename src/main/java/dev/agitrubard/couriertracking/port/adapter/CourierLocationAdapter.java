package dev.agitrubard.couriertracking.port.adapter;

import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.entity.CourierLocationEntity;
import dev.agitrubard.couriertracking.model.mapper.CourierLocationToEntityMapper;
import dev.agitrubard.couriertracking.port.CourierLocationSavePort;
import dev.agitrubard.couriertracking.repository.CourierLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class CourierLocationAdapter implements CourierLocationSavePort {

    private final CourierLocationRepository courierLocationRepository;

    private final CourierLocationToEntityMapper courierLocationToEntityMapper = CourierLocationToEntityMapper.INSTANCE;

    @Override
    public void save(final CourierLocation courierLocation) {
        final CourierLocationEntity courierLocationEntity = courierLocationToEntityMapper.map(courierLocation);
        courierLocationRepository.save(courierLocationEntity);
    }

}
