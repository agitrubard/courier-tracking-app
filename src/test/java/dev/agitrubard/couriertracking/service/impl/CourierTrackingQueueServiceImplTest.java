package dev.agitrubard.couriertracking.service.impl;

import dev.agitrubard.couriertracking.AbstractUnitTest;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequestBuilder;
import dev.agitrubard.couriertracking.service.CourierTrackingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;

import java.time.LocalDateTime;

class CourierTrackingQueueServiceImplTest extends AbstractUnitTest {

    @InjectMocks
    CourierTrackingQueueServiceImpl courierTrackingQueueService;

    @Mock
    CourierTrackingService courierTrackingService;

    @Mock
    AmqpTemplate amqpTemplate;


    @Test
    void givenValidCourierLocationSaveRequest_whenRequestAddedToQueue_thenDoNothing() {

        // Given
        CourierLocationSaveRequest mockSaveRequest = new CourierLocationSaveRequestBuilder()
                .withValidValues()
                .withTime(LocalDateTime.now())
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();

        // When
        Mockito.doNothing()
                .when(amqpTemplate)
                .convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(CourierLocationSaveRequest.class));

        // Then
        courierTrackingQueueService.saveLocation(mockSaveRequest);

        // Verify
        Mockito.verify(amqpTemplate, Mockito.times(1))
                .convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(CourierLocationSaveRequest.class));

        Mockito.verify(courierTrackingService, Mockito.never())
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));
    }

    @Test
    void givenValidCourierLocationSaveRequest_whenErrorOccurredWhileAddingRequestToQueue_thenForceToSaveLocation() {

        // Given
        CourierLocationSaveRequest mockSaveRequest = new CourierLocationSaveRequestBuilder()
                .withValidValues()
                .withTime(LocalDateTime.now())
                .withLatitude(41.000000)
                .withLongitude(29.000000)
                .build();

        // When
        Mockito.doThrow(AmqpException.class)
                .when(amqpTemplate)
                .convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(CourierLocationSaveRequest.class));

        Mockito.doNothing()
                .when(courierTrackingService)
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));

        // Then
        courierTrackingQueueService.saveLocation(mockSaveRequest);

        // Verify
        Mockito.verify(amqpTemplate, Mockito.times(1))
                .convertAndSend(Mockito.anyString(), Mockito.anyString(), Mockito.any(CourierLocationSaveRequest.class));

        Mockito.verify(courierTrackingService, Mockito.times(1))
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));
    }

}
