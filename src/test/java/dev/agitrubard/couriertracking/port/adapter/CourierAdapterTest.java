package dev.agitrubard.couriertracking.port.adapter;

import dev.agitrubard.couriertracking.AbstractUnitTest;
import dev.agitrubard.couriertracking.model.Courier;
import dev.agitrubard.couriertracking.model.CourierBuilder;
import dev.agitrubard.couriertracking.model.entity.CourierEntity;
import dev.agitrubard.couriertracking.model.entity.CourierEntityBuilder;
import dev.agitrubard.couriertracking.model.mapper.CourierToEntityMapper;
import dev.agitrubard.couriertracking.repository.CourierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

class CourierAdapterTest extends AbstractUnitTest {

    @InjectMocks
    CourierAdapter courierAdapter;

    @Mock
    CourierRepository courierRepository;


    private final CourierToEntityMapper courierToEntityMapper = CourierToEntityMapper.INSTANCE;


    @Test
    void givenValidId_whenCourierEntityFound_thenReturnCourier() {

        // Given
        UUID mockId = UUID.fromString("554681ab-2c33-46df-9c7d-60008d8b776d");

        // When
        CourierEntity mockCourierEntity = new CourierEntityBuilder()
                .withValidValues()
                .withId(mockId)
                .build();
        Mockito.when(courierRepository.findById(mockId))
                .thenReturn(Optional.of(mockCourierEntity));

        // Then
        Optional<Courier> courier = courierAdapter.findById(mockId);

        Assertions.assertTrue(courier.isPresent());

        // Verify
        Mockito.verify(courierRepository, Mockito.times(1))
                .findById(mockId);
    }

    @Test
    void givenValidId_whenCourierEntityNotFound_thenReturnEmpty() {

        // Given
        UUID mockId = UUID.fromString("5cce0d40-906c-4062-81dc-7a02f5f9c841");

        // When
        Mockito.when(courierRepository.findById(mockId))
                .thenReturn(Optional.empty());

        // Then
        Optional<Courier> courier = courierAdapter.findById(mockId);

        Assertions.assertTrue(courier.isEmpty());

        // Verify
        Mockito.verify(courierRepository, Mockito.times(1))
                .findById(mockId);
    }


    @Test
    void givenValidCourier_whenCourierEntitySaved_thenDoNothing() {

        // Given
        Courier mockCourier = new CourierBuilder()
                .withValidValues()
                .withoutId()
                .build();

        // When
        CourierEntity mockCourierEntity = courierToEntityMapper.map(mockCourier);

        Mockito.when(courierRepository.save(Mockito.any(CourierEntity.class)))
                .thenReturn(mockCourierEntity);

        // Then
        courierAdapter.save(mockCourier);

        // Verify
        Mockito.verify(courierRepository, Mockito.times(1))
                .save(Mockito.any(CourierEntity.class));
    }

}
