package dev.agitrubard.couriertracking.repository;

import dev.agitrubard.couriertracking.model.Store;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface StoreRepository extends MongoRepository<Store, UUID> {
}
