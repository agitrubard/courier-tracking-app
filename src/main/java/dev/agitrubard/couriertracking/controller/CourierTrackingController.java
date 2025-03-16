package dev.agitrubard.couriertracking.controller;

import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;
import dev.agitrubard.couriertracking.model.response.CustomSuccessResponse;
import dev.agitrubard.couriertracking.service.CourierTrackingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/couriers")
class CourierTrackingController {

    private final CourierTrackingService courierTrackingService;

    @PostMapping("/locations")
    CustomSuccessResponse<Void> save(@RequestBody @Valid CourierLocationSaveRequest saveRequest) {
        courierTrackingService.saveLocation(saveRequest);
        return CustomSuccessResponse.success();
    }

}
