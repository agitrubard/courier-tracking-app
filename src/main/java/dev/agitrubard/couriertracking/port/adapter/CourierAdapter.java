package dev.agitrubard.couriertracking.port.adapter;

import dev.agitrubard.couriertracking.model.Courier;
import dev.agitrubard.couriertracking.model.entity.CourierEntity;
import dev.agitrubard.couriertracking.model.mapper.CourierEntityToDomainMapper;
import dev.agitrubard.couriertracking.model.mapper.CourierToEntityMapper;
import dev.agitrubard.couriertracking.port.CourierReadPort;
import dev.agitrubard.couriertracking.port.CourierSavePort;
import dev.agitrubard.couriertracking.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class CourierAdapter implements CourierReadPort, CourierSavePort {

    private final CourierRepository courierRepository;

    private final CourierToEntityMapper courierToEntityMapper = CourierToEntityMapper.INSTANCE;
    private final CourierEntityToDomainMapper courierEntityToDomainMapper = CourierEntityToDomainMapper.INSTANCE;


    @Override
    public Optional<Courier> findById(final UUID id) {
        return courierRepository.findById(id)
                .map(courierEntityToDomainMapper::map);
    }


    @Override
    public void save(final Courier courier) {
        final CourierEntity courierEntity = courierToEntityMapper.map(courier);
        courierRepository.save(courierEntity);
    }
}
