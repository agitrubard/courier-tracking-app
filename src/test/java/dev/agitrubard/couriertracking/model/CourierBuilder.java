package dev.agitrubard.couriertracking.model;

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

}
