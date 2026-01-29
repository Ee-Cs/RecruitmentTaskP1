package com.altapay.backend.repositories;

import com.altapay.backend.model.Inventory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static com.altapay.backend.TestConstants.INVENTORY;
import static com.altapay.backend.TestConstants.PRODUCT_ID;

class InventoryRepositoryTests {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final InventoryRepository inventoryRepository = new InventoryRepository();

    @Test
    @DisplayName("🟩 should load")
    void shouldLoad() {
        // GIVEN
        // WHEN
        final Inventory inventory = inventoryRepository.load(PRODUCT_ID);
        // THEN
        Assertions.assertNotNull(inventory);
        logger.info("shouldLoad():");
    }

    @Test
    @DisplayName("🟩 should save")
    void shouldSave() throws Exception {
        // GIVEN
        // WHEN
        inventoryRepository.save(INVENTORY);
        // THEN
        logger.info("shouldSave():");
    }
}