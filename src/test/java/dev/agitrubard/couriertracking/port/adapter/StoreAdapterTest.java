package dev.agitrubard.couriertracking.port.adapter;

import dev.agitrubard.couriertracking.AbstractUnitTest;
import dev.agitrubard.couriertracking.model.Store;
import dev.agitrubard.couriertracking.model.entity.StoreEntity;
import dev.agitrubard.couriertracking.model.entity.StoreEntityBuilder;
import dev.agitrubard.couriertracking.repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.CollectionUtils;

import java.util.List;

class StoreAdapterTest extends AbstractUnitTest {


    @InjectMocks
    StoreAdapter storeAdapter;

    @Mock
    StoreRepository storeRepository;


    @Test
    void whenAllStoreEntitiesFound_thenReturnStores() {

        // When
        List<StoreEntity> mockStoreEntities = List.of(
                new StoreEntityBuilder().withValidValues().build(),
                new StoreEntityBuilder().withValidValues().build()
        );
        Mockito.when(storeRepository.findAll())
                .thenReturn(mockStoreEntities);

        // Then
        List<Store> stores = storeAdapter.findAll();

        Assertions.assertFalse(CollectionUtils.isEmpty(stores));

        // Verify
        Mockito.verify(storeRepository, Mockito.times(1))
                .findAll();
    }


    @Test
    void whenStoreEntitiesNotFound_thenReturnEmptyList() {

        // When
        List<StoreEntity> mockStoreEntities = List.of();
        Mockito.when(storeRepository.findAll())
                .thenReturn(mockStoreEntities);

        // Then
        List<Store> stores = storeAdapter.findAll();

        Assertions.assertTrue(CollectionUtils.isEmpty(stores));

        // Verify
        Mockito.verify(storeRepository, Mockito.times(1))
                .findAll();
    }

}
