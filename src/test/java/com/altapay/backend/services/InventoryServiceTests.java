package com.altapay.backend.services;

import com.altapay.backend.repositories.InventoryRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static com.altapay.backend.TestConstants.*;

class InventoryServiceTests {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Mock
    private InventoryRepository inventoryRepository;
    private InventoryService inventoryService;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        inventoryService = new InventoryService(inventoryRepository);
        PRODUCT.setId(PRODUCT_ID);
        PRODUCT.setName(PRODUCT_NAME);
        INVENTORY.setProduct(PRODUCT);
        INVENTORY.setInventory(AVAILABLE_QUANTITY);
        Mockito.when(inventoryRepository.load(PRODUCT_ID)).thenReturn(INVENTORY);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("🟩 should check inventory with success")
    void shouldCheckInventoryWithSuccess() {
        // GIVEN
        // WHEN
        final boolean flag = inventoryService.checkInventory(PRODUCT, REQUESTED_CORRECT_QUANTITY);
        // THEN
        Assertions.assertTrue(flag);
        Mockito.verify(inventoryRepository).load(PRODUCT_ID);
        logger.info("shouldCheckInventoryWithSuccess():");
    }

    @Test
    @DisplayName("🟥 should check inventory with failure")
    void shouldCheckInventoryWithFailure() {
        // GIVEN
        // WHEN
        final boolean flag = inventoryService.checkInventory(PRODUCT, REQUESTED_UNAVAILABLE_QUANTITY);
        // THEN
        Assertions.assertFalse(flag);
        Mockito.verify(inventoryRepository).load(PRODUCT_ID);
        logger.info("shouldCheckInventoryWithFailure():");
    }

    @Test
    @DisplayName("🟩 should take from inventory")
    void shouldTakeFromInventory() throws Exception {
        // GIVEN
        // WHEN
        final boolean flag = inventoryService.takeFromInventory(PRODUCT, AVAILABLE_QUANTITY);
        // THEN
        Assertions.assertTrue(flag);
        Mockito.verify(inventoryRepository).load(PRODUCT_ID);
        Mockito.verify(inventoryRepository).save(INVENTORY);
        logger.info("shouldTakeFromInventory():");
    }

    @Test
    @DisplayName("🟥 should not take from inventory")
    void shouldNotTakeFromInventory() throws Exception {
        // GIVEN
        Mockito.doThrow(new Exception()).when(inventoryRepository).save(INVENTORY);
        // WHEN
        final boolean flag = inventoryService.takeFromInventory(PRODUCT, AVAILABLE_QUANTITY);
        // THEN
        Assertions.assertFalse(flag);
        Mockito.verify(inventoryRepository).load(PRODUCT_ID);
        Mockito.verify(inventoryRepository).save(INVENTORY);
        logger.info("shouldNotTakeFromInventory():");
    }
}