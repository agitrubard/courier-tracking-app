package dev.agitrubard.couriertracking.model.entity;

import dev.agitrubard.couriertracking.model.TestDataBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourierStoreEntryEntityBuilder extends TestDataBuilder<CourierStoreEntryEntity> {

    public CourierStoreEntryEntityBuilder() {
        super(CourierStoreEntryEntity.class);
    }

    public CourierStoreEntryEntityBuilder withValidValues() {
        return this
                .withCreatedAtNow();
    }

    public CourierStoreEntryEntityBuilder withCourierId(UUID courierId) {
        data.setCourierId(courierId);
        return this;
    }

    private CourierStoreEntryEntityBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
