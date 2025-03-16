package dev.agitrubard.couriertracking.repository;

import dev.agitrubard.couriertracking.model.entity.CourierEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CourierRepository extends MongoRepository<CourierEntity, UUID> {
}
