package dev.agitrubard.couriertracking.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document(collection = "stores")
public class StoreEntity extends AbstractEntity {

    @Id
    private UUID id;
    private String name;
    private Double latitude;
    private Double longitude;

}
