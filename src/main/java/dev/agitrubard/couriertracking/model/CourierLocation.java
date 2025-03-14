package dev.agitrubard.couriertracking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class CourierLocation extends AbstractDomainModel {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private UUID courierId;
    private Double latitude;
    private Double longitude;

    @Override
    public void update() {
        this.updatedAt = LocalDateTime.now();
    }

}
