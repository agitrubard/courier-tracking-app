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
public class Store extends AbstractDomainModel {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String name;
    private Location location;

}
