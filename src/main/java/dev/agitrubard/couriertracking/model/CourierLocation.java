package dev.agitrubard.couriertracking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class CourierLocation extends AbstractDomainModel {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private UUID courierId;
    private Location location;

}
