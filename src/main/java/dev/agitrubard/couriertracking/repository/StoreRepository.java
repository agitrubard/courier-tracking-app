package dev.agitrubard.couriertracking.repository;

import dev.agitrubard.couriertracking.model.entity.StoreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface StoreRepository extends MongoRepository<StoreEntity, UUID> {
}
