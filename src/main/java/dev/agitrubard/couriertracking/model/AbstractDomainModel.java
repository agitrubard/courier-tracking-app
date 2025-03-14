package dev.agitrubard.couriertracking.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
@NoArgsConstructor
public abstract class AbstractDomainModel {

    @Builder.Default
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime updatedAt;

    public abstract void update();

}
