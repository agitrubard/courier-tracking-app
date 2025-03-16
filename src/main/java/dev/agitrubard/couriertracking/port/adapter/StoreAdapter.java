package dev.agitrubard.couriertracking.port.adapter;

import dev.agitrubard.couriertracking.model.Store;
import dev.agitrubard.couriertracking.model.mapper.StoreEntityToDomainMapper;
import dev.agitrubard.couriertracking.port.StoreReadPort;
import dev.agitrubard.couriertracking.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class StoreAdapter implements StoreReadPort {

    private final StoreRepository storeRepository;

    private final StoreEntityToDomainMapper storeEntityToDomainMapper = StoreEntityToDomainMapper.INSTANCE;


    @Override
    public List<Store> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(storeEntityToDomainMapper::map)
                .toList();
    }

}
