package dev.agitrubard.couriertracking.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "couirers")
public class CourierEntity extends AbstractEntity {

    @Id
    private UUID id;
    private Double totalDistanceKilometers;

}
