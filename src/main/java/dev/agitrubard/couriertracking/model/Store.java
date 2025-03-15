package dev.agitrubard.couriertracking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Store extends AbstractDomainModel {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String name;
    private Location location;

}
