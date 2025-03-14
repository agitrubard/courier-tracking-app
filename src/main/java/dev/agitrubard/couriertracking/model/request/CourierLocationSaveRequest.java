package dev.agitrubard.couriertracking.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public final class CourierLocationSaveRequest {

    @NotNull
    @PastOrPresent
    private LocalDateTime time;

    @NotNull
    private UUID courierId;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

}
