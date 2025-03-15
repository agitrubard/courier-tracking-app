package dev.agitrubard.couriertracking.repository;

import dev.agitrubard.couriertracking.model.entity.CourierStoreEntryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CourierStoreEntryRepository extends MongoRepository<CourierStoreEntryEntity, UUID> {

    Optional<CourierStoreEntryEntity> findTopByCourierIdOrderByCreatedAtDesc(UUID courierId);

}
