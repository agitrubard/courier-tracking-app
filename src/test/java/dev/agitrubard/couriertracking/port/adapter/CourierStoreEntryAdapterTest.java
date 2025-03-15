package dev.agitrubard.couriertracking.port.adapter;

import dev.agitrubard.couriertracking.AbstractUnitTest;
import dev.agitrubard.couriertracking.model.CourierStoreEntry;
import dev.agitrubard.couriertracking.model.CourierStoreEntryBuilder;
import dev.agitrubard.couriertracking.model.entity.CourierStoreEntryEntity;
import dev.agitrubard.couriertracking.model.entity.CourierStoreEntryEntityBuilder;
import dev.agitrubard.couriertracking.model.mapper.CourierStoreEntryToEntityMapper;
import dev.agitrubard.couriertracking.repository.CourierStoreEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

class CourierStoreEntryAdapterTest extends AbstractUnitTest {

    @InjectMocks
    CourierStoreEntryAdapter courierStoreEntryAdapter;

    @Mock
    CourierStoreEntryRepository courierStoreEntryRepository;


    private final CourierStoreEntryToEntityMapper courierStoreEntryToEntityMapper = CourierStoreEntryToEntityMapper.INSTANCE;


    @Test
    void givenValidCourierId_whenLastCourierStoreEntryEntityFound_thenReturnCourierStoreEntry() {

        // Given
        UUID mockCourierId = UUID.fromString("185621ad-00b1-495f-9409-ec67d45c87da");

        // When
        CourierStoreEntryEntity mockCourierStoreEntryEntity = new CourierStoreEntryEntityBuilder()
                .withValidValues()
                .withCourierId(mockCourierId)
                .build();
        Mockito.when(courierStoreEntryRepository.findTopByCourierIdOrderByCreatedAtDesc(mockCourierId))
                .thenReturn(Optional.of(mockCourierStoreEntryEntity));

        // Then
        Optional<CourierStoreEntry> courierStoreEntry = courierStoreEntryAdapter.findLastByCourierId(mockCourierId);

        Assertions.assertTrue(courierStoreEntry.isPresent());

        // Verify
        Mockito.verify(courierStoreEntryRepository, Mockito.times(1))
                .findTopByCourierIdOrderByCreatedAtDesc(mockCourierId);
    }

    @Test
    void givenValidCourierId_whenCourierStoreEntryEntityNotFound_thenReturnEmpty() {

        // Given
        UUID mockCourierId = UUID.fromString("d3f7c4a4-1888-4b78-9805-7e882acb2d33");

        // When
        Mockito.when(courierStoreEntryRepository.findById(mockCourierId))
                .thenReturn(Optional.empty());

        // Then
        Optional<CourierStoreEntry> courierStoreEntry = courierStoreEntryAdapter.findLastByCourierId(mockCourierId);

        Assertions.assertTrue(courierStoreEntry.isEmpty());

        // Verify
        Mockito.verify(courierStoreEntryRepository, Mockito.times(1))
                .findTopByCourierIdOrderByCreatedAtDesc(mockCourierId);
    }


    @Test
    void givenValidCourierStoreEntry_whenCourierStoreEntryEntitySaved_thenDoNothing() {

        // Given
        CourierStoreEntry mockCourierStoreEntry = new CourierStoreEntryBuilder()
                .withValidValues()
                .withoutId()
                .build();

        // When
        CourierStoreEntryEntity mockCourierStoreEntryEntity = courierStoreEntryToEntityMapper.map(mockCourierStoreEntry);

        Mockito.when(courierStoreEntryRepository.save(Mockito.any(CourierStoreEntryEntity.class)))
                .thenReturn(mockCourierStoreEntryEntity);

        // Then
        courierStoreEntryAdapter.save(mockCourierStoreEntry);

        // Verify
        Mockito.verify(courierStoreEntryRepository, Mockito.times(1))
                .save(Mockito.any(CourierStoreEntryEntity.class));
    }

}
