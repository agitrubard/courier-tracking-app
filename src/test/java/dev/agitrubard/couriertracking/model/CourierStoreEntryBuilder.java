package dev.agitrubard.couriertracking.model;

import java.time.LocalDateTime;

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

    private CourierStoreEntryBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
