package dev.agitrubard.couriertracking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Courier extends AbstractDomainModel {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    @Builder.Default
    private Double totalDistanceKilometers = 0.0;

    @Override
    public void update() {
        this.updatedAt = LocalDateTime.now();
    }

}
