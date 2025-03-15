package dev.agitrubard.couriertracking.model.entity;

import dev.agitrubard.couriertracking.model.TestDataBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourierEntityBuilder extends TestDataBuilder<CourierEntity> {

    public CourierEntityBuilder() {
        super(CourierEntity.class);
    }

    public CourierEntityBuilder withValidValues() {
        return this
                .withCreatedAtNow();
    }

    public CourierEntityBuilder withId(UUID id) {
        data.setId(id);
        return this;
    }

    private CourierEntityBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
