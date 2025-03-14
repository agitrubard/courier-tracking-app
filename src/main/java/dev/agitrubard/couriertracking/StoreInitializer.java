package dev.agitrubard.couriertracking;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.agitrubard.couriertracking.model.Store;
import dev.agitrubard.couriertracking.repository.StoreRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Configuration
class StoreInitializer {

    private final StoreRepository storeRepository;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public StoreInitializer(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @PostConstruct
    void init() {

        log.info("Initializing store data...");

        final List<Store> stores = this.readStores();

        long count = storeRepository.count();
        if (stores.size() == count) {
            log.warn("Found {} store data and will not initialize again.", count);
            return;
        }

        storeRepository.deleteAll();
        stores.forEach(store -> store.setCreatedAt(LocalDateTime.now()));
        storeRepository.saveAll(stores);
        log.info("Store data initialized.");
    }

    private List<Store> readStores() {
        final String path = "files/stores.json";
        try {
            Resource resource = new ClassPathResource(path);
            Store[] stores = OBJECT_MAPPER.readValue(resource.getInputStream(), Store[].class);
            return Arrays.asList(stores);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stores from JSON file at: " + path, e);
        }
    }

}
