package dev.agitrubard.couriertracking.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public final class CourierDistanceResponse {

    private Double totalDistanceKilometers;

}
