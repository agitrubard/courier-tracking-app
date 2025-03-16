package dev.agitrubard.couriertracking.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourierStoreEntryBuilder extends TestDataBuilder<CourierStoreEntry> {

    public CourierStoreEntryBuilder() {
        super(CourierStoreEntry.class);
    }

    public CourierStoreEntryBuilder withValidValues() {
        return this
                .withCreatedAtNow();
    }

    public CourierStoreEntryBuilder withoutId() {
        data.setId(null);
        return this;
    }

    public CourierStoreEntryBuilder withCourierId(UUID courierId) {
        data.setCourierId(courierId);
        return this;
    }

    public CourierStoreEntryBuilder withStoreId(UUID storeId) {
        data.setStoreId(storeId);
        return this;
    }

    private CourierStoreEntryBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

    public CourierStoreEntryBuilder withCreatedAt(LocalDateTime createdAt) {
        data.setCreatedAt(createdAt);
        return this;
    }

}
