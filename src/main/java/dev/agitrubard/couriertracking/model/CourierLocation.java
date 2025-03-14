package dev.agitrubard.couriertracking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CourierLocation extends AbstractDomainModel {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private UUID courierId;
    @Builder.Default
    private Location location = Location.builder().build();


    public static class CourierLocationBuilder {

        private Location location;

        public CourierLocationBuilder latitude(final Double latitude) {
            this.location.setLatitude(latitude);
            return this;
        }

        public CourierLocationBuilder longitude(final Double longitude) {
            this.location.setLongitude(longitude);
            return this;
        }
    }

}
