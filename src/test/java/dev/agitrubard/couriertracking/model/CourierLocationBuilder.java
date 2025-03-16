package dev.agitrubard.couriertracking.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourierLocationBuilder extends TestDataBuilder<CourierLocation> {

    public CourierLocationBuilder() {
        super(CourierLocation.class);
    }

    public CourierLocationBuilder withValidValues() {
        return this
                .withLocation(Location.builder().build())
                .withLatitude(41.9632463)
                .withLongitude(29.2830908)
                .withCreatedAtNow();
    }

    public CourierLocationBuilder withoutId() {
        data.setId(null);
        return this;
    }

    public CourierLocationBuilder withCourierId(UUID courierId) {
        data.setCourierId(courierId);
        return this;
    }

    public CourierLocationBuilder withLocation(Location location) {
        data.setLocation(location);
        return this;
    }

    public CourierLocationBuilder withLatitude(Double latitude) {
        data.getLocation().setLatitude(latitude);
        return this;
    }

    public CourierLocationBuilder withLongitude(Double longitude) {
        data.getLocation().setLongitude(longitude);
        return this;
    }

    private CourierLocationBuilder withCreatedAtNow() {
        data.setCreatedAt(LocalDateTime.now());
        return this;
    }

    public CourierLocationBuilder withCreatedAt(LocalDateTime createdAt) {
        data.setCreatedAt(createdAt);
        return this;
    }

}
