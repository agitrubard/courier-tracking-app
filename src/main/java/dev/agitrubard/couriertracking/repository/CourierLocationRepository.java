package dev.agitrubard.couriertracking.repository;

import dev.agitrubard.couriertracking.model.entity.CourierLocationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CourierLocationRepository extends MongoRepository<CourierLocationEntity, UUID> {
}
