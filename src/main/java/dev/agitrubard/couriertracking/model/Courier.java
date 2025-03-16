package dev.agitrubard.couriertracking.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Courier extends AbstractDomainModel {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    @Builder.Default
    private Double totalDistanceKilometers = 0.0;

    public void update(final Double totalDistanceKilometers) {
        super.update();
        this.totalDistanceKilometers += totalDistanceKilometers;
    }

}
