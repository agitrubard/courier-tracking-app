package dev.agitrubard.couriertracking.model;

import java.util.UUID;

public class CourierBuilder extends TestDataBuilder<Courier> {

    public CourierBuilder() {
        super(Courier.class);
    }

    public CourierBuilder withValidValues() {
        return this;
    }

    public CourierBuilder withoutId() {
        data.setId(null);
        return this;
    }

    public CourierBuilder withId(UUID id) {
        data.setId(id);
        return this;
    }

    public CourierBuilder withTotalDistanceKilometers(Double totalDistanceKilometers) {
        data.setTotalDistanceKilometers(totalDistanceKilometers);
        return this;
    }

}
