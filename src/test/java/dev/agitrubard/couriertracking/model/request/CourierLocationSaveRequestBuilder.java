package dev.agitrubard.couriertracking.model.request;

import dev.agitrubard.couriertracking.model.TestDataBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourierLocationSaveRequestBuilder extends TestDataBuilder<CourierLocationSaveRequest> {

    public CourierLocationSaveRequestBuilder() {
        super(CourierLocationSaveRequest.class);
    }

    public CourierLocationSaveRequestBuilder withValidValues() {
        return this;
    }

    public CourierLocationSaveRequestBuilder withTime(LocalDateTime time) {
        data.setTime(time);
        return this;
    }

    public CourierLocationSaveRequestBuilder withCourierId(UUID courierId) {
        data.setCourierId(courierId);
        return this;
    }

    public CourierLocationSaveRequestBuilder withLatitude(Double latitude) {
        data.setLatitude(latitude);
        return this;
    }

    public CourierLocationSaveRequestBuilder withLongitude(Double longitude) {
        data.setLongitude(longitude);
        return this;
    }

}
