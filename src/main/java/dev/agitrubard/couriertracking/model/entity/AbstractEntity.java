package dev.agitrubard.couriertracking.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractEntity {

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

}
