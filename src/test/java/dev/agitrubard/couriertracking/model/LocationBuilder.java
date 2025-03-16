package dev.agitrubard.couriertracking.model;

public class LocationBuilder extends TestDataBuilder<Location> {

    public LocationBuilder() {
        super(Location.class);
    }

    public LocationBuilder withLatitude(Double latitude) {
        data.setLatitude(latitude);
        return this;
    }

    public LocationBuilder withLongitude(Double longitude) {
        data.setLongitude(longitude);
        return this;
    }

}
