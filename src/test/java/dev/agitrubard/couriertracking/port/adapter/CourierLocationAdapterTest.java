package dev.agitrubard.couriertracking.port.adapter;

import dev.agitrubard.couriertracking.AbstractUnitTest;
import dev.agitrubard.couriertracking.model.CourierLocation;
import dev.agitrubard.couriertracking.model.CourierLocationBuilder;
import dev.agitrubard.couriertracking.model.entity.CourierLocationEntity;
import dev.agitrubard.couriertracking.model.entity.CourierLocationEntityBuilder;
import dev.agitrubard.couriertracking.model.mapper.CourierLocationToEntityMapper;
import dev.agitrubard.couriertracking.repository.CourierLocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

class CourierLocationAdapterTest extends AbstractUnitTest {

    @InjectMocks
    CourierLocationAdapter courierLocationAdapter;

    @Mock
    CourierLocationRepository courierLocationRepository;


    private final CourierLocationToEntityMapper courierLocationToEntityMapper = CourierLocationToEntityMapper.INSTANCE;


    @Test
    void givenValidCourierId_whenLastCourierLocationEntityFound_thenReturnCourierLocation() {

        // Given
        UUID mockCourierId = UUID.fromString("185621ad-00b1-495f-9409-ec67d45c87da");

        // When
        CourierLocationEntity mockCourierLocationEntity = new CourierLocationEntityBuilder()
                .withValidValues()
                .withId(mockCourierId)
                .build();
        Mockito.when(courierLocationRepository.findTopByCourierIdOrderByCreatedAtDesc(mockCourierId))
                .thenReturn(Optional.of(mockCourierLocationEntity));

        // Then
        Optional<CourierLocation> courierLocation = courierLocationAdapter.findLastByCourierId(mockCourierId);

        Assertions.assertTrue(courierLocation.isPresent());

        // Verify
        Mockito.verify(courierLocationRepository, Mockito.times(1))
                .findTopByCourierIdOrderByCreatedAtDesc(mockCourierId);
    }

    @Test
    void givenValidCourierId_whenCourierLocationEntityNotFound_thenReturnEmpty() {

        // Given
        UUID mockCourierId = UUID.fromString("d3f7c4a4-1888-4b78-9805-7e882acb2d33");

        // When
        Mockito.when(courierLocationRepository.findById(mockCourierId))
                .thenReturn(Optional.empty());

        // Then
        Optional<CourierLocation> courierLocation = courierLocationAdapter.findLastByCourierId(mockCourierId);

        Assertions.assertTrue(courierLocation.isEmpty());

        // Verify
        Mockito.verify(courierLocationRepository, Mockito.times(1))
                .findTopByCourierIdOrderByCreatedAtDesc(mockCourierId);
    }


    @Test
    void givenValidCourierLocation_whenCourierLocationEntitySaved_thenDoNothing() {

        // Given
        CourierLocation mockCourierLocation = new CourierLocationBuilder()
                .withValidValues()
                .withoutId()
                .build();

        // When
        CourierLocationEntity mockCourierLocationEntity = courierLocationToEntityMapper.map(mockCourierLocation);

        Mockito.when(courierLocationRepository.save(Mockito.any(CourierLocationEntity.class)))
                .thenReturn(mockCourierLocationEntity);

        // Then
        courierLocationAdapter.save(mockCourierLocation);

        // Verify
        Mockito.verify(courierLocationRepository, Mockito.times(1))
                .save(Mockito.any(CourierLocationEntity.class));
    }

}
