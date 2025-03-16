package dev.agitrubard.couriertracking.model;

import java.util.UUID;

public class StoreBuilder extends TestDataBuilder<Store> {

    public StoreBuilder() {
        super(Store.class);
    }

    public StoreBuilder withValidValues() {
        return this
                .withLocation(Location.builder().build());
    }

    public StoreBuilder withId(UUID id) {
        data.setId(id);
        return this;
    }

    public StoreBuilder withName(String name) {
        data.setName(name);
        return this;
    }

    public StoreBuilder withLocation(Location location) {
        data.setLocation(location);
        return this;
    }

    public StoreBuilder withLatitude(Double latitude) {
        data.getLocation().setLatitude(latitude);
        return this;
    }

    public StoreBuilder withLongitude(Double longitude) {
        data.getLocation().setLongitude(longitude);
        return this;
    }

}
