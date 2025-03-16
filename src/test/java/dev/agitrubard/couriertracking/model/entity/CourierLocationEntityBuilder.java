package dev.agitrubard.couriertracking.model.entity;

import dev.agitrubard.couriertracking.model.TestDataBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourierLocationEntityBuilder extends TestDataBuilder<CourierLocationEntity> {

    public CourierLocationEntityBuilder() {
        super(CourierLocationEntity.class);
    }

    public CourierLocationEntityBuilder withValidValues() {
        return this
                .withLatitude(40.9632463)
                .withLongitude(30.2830908)
                .withCreatedAtNow();
    }

    public CourierLocationEntityBuilder withCourierId(UUID courierId) {
        data.setCourierId(courierId);
        return this;
    }

    public CourierLocationEntityBuilder withLatitude(Double latitude) {
        data.setLatitude(latitude);
        return this;
    }

    public CourierLocationEntityBuilder withLongitude(Double longitude) {
        data.setLongitude(longitude);
        return this;
    }

    private CourierLocationEntityBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

}
