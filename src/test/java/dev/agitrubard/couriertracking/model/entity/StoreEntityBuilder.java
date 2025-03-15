package dev.agitrubard.couriertracking.model.entity;

import dev.agitrubard.couriertracking.model.TestDataBuilder;

import java.time.LocalDateTime;

public class StoreEntityBuilder extends TestDataBuilder<StoreEntity> {

    public StoreEntityBuilder() {
        super(StoreEntity.class);
    }

    public StoreEntityBuilder withValidValues() {
        return this
                .withLatitude(41.9632463)
                .withLongitude(29.2830908)
                .withCreatedAtNow();
    }

    public StoreEntityBuilder withLatitude(Double latitude) {
        data.setLatitude(latitude);
        return this;
    }

    public StoreEntityBuilder withLongitude(Double longitude) {
        data.setLongitude(longitude);
        return this;
    }

    private StoreEntityBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
