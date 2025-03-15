package dev.agitrubard.couriertracking.model.entity;

import dev.agitrubard.couriertracking.model.TestDataBuilder;

public class CourierEntityBuilder extends TestDataBuilder<CourierEntity> {

    public CourierEntityBuilder() {
        super(CourierEntity.class);
    }

    public CourierEntityBuilder withValidValues() {
        return this;
    }

}
