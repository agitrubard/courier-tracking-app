package dev.agitrubard.couriertracking.controller;

import dev.agitrubard.couriertracking.AbstractRestControllerTest;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequest;
import dev.agitrubard.couriertracking.model.request.CourierLocationSaveRequestBuilder;
import dev.agitrubard.couriertracking.model.response.CustomErrorResponse;
import dev.agitrubard.couriertracking.model.response.CustomErrorResponseBuilder;
import dev.agitrubard.couriertracking.model.response.CustomSuccessResponse;
import dev.agitrubard.couriertracking.model.response.CustomSuccessResponseBuilder;
import dev.agitrubard.couriertracking.service.CourierTrackingService;
import dev.agitrubard.couriertracking.util.CustomMockMvcRequestBuilders;
import dev.agitrubard.couriertracking.util.CustomMockResultMatchersBuilders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

class CourierTrackingControllerTest extends AbstractRestControllerTest {

    @MockitoBean
    CourierTrackingService courierTrackingService;


    private static final String BASE_PATH = "/api/v1/couriers";

    @Test
    void givenValidCourierLocationSaveRequest_whenLocationSaved_thenReturnSuccess() throws Exception {

        // Given
        CourierLocationSaveRequest mockSaveRequest = new CourierLocationSaveRequestBuilder()
                .withValidValues()
                .build();

        // When
        Mockito.doNothing()
                .when(courierTrackingService)
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));

        // Then
        String endpoint = BASE_PATH + "/locations";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockSaveRequest);

        CustomSuccessResponse<Void> mockResponse = CustomSuccessResponseBuilder.success();

        customMockMvc.perform(mockHttpServletRequestBuilder, mockResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isOk())
                .andExpect(CustomMockResultMatchersBuilders.content()
                        .doesNotHaveJsonPath());

        // Verify
        Mockito.verify(courierTrackingService, Mockito.times(1))
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));
    }

    @NullSource
    @CsvSource({
            "8000-01-01T00:00:00",
    })
    @ParameterizedTest
    void givenCourierLocationSaveRequest_whenTimeIsNotValid_thenReturnSuccess(LocalDateTime mockTime) throws Exception {

        // Given
        CourierLocationSaveRequest mockSaveRequest = new CourierLocationSaveRequestBuilder()
                .withValidValues()
                .withTime(mockTime)
                .build();

        // When
        Mockito.doNothing()
                .when(courierTrackingService)
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));

        // Then
        String endpoint = BASE_PATH + "/locations";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockSaveRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(courierTrackingService, Mockito.never())
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));
    }

    @NullSource
    @ParameterizedTest
    void givenCourierLocationSaveRequest_whenCourierIdIsNotValid_thenReturnSuccess(UUID mockCourierId) throws Exception {

        // Given
        CourierLocationSaveRequest mockSaveRequest = new CourierLocationSaveRequestBuilder()
                .withValidValues()
                .withCourierId(mockCourierId)
                .build();

        // When
        Mockito.doNothing()
                .when(courierTrackingService)
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));

        // Then
        String endpoint = BASE_PATH + "/locations";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockSaveRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(courierTrackingService, Mockito.never())
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));
    }

    @NullSource
    @ParameterizedTest
    void givenCourierLocationSaveRequest_whenLatitudeIsNotValid_thenReturnSuccess(Double mockLatitude) throws Exception {

        // Given
        CourierLocationSaveRequest mockSaveRequest = new CourierLocationSaveRequestBuilder()
                .withValidValues()
                .withLatitude(mockLatitude)
                .build();

        // When
        Mockito.doNothing()
                .when(courierTrackingService)
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));

        // Then
        String endpoint = BASE_PATH + "/locations";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockSaveRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(courierTrackingService, Mockito.never())
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));
    }

    @NullSource
    @ParameterizedTest
    void givenCourierLocationSaveRequest_whenLongitudeIsNotValid_thenReturnSuccess(Double mockLongitude) throws Exception {

        // Given
        CourierLocationSaveRequest mockSaveRequest = new CourierLocationSaveRequestBuilder()
                .withValidValues()
                .withLongitude(mockLongitude)
                .build();

        // When
        Mockito.doNothing()
                .when(courierTrackingService)
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));

        // Then
        String endpoint = BASE_PATH + "/locations";
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = CustomMockMvcRequestBuilders
                .post(endpoint, mockSaveRequest);

        CustomErrorResponse mockErrorResponse = CustomErrorResponseBuilder.VALIDATION_ERROR;

        customMockMvc.perform(mockHttpServletRequestBuilder, mockErrorResponse)
                .andExpect(CustomMockResultMatchersBuilders.status()
                        .isBadRequest());

        // Verify
        Mockito.verify(courierTrackingService, Mockito.never())
                .saveLocation(Mockito.any(CourierLocationSaveRequest.class));
    }

}
