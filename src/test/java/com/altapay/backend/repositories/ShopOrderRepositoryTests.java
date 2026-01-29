package com.altapay.backend.repositories;

import com.altapay.backend.ioc.BackendContainer;
import com.altapay.backend.model.ShopOrder;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static com.altapay.backend.TestConstants.*;

class ShopOrderRepositoryTests {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Mock
    private BackendContainer backendContainer;
    private ShopOrderRepository shopOrderRepository;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.when(backendContainer.getShopOrder()).thenReturn(SHOP_ORDER);
        Mockito.when(backendContainer.getProduct()).thenReturn(PRODUCT);
        Mockito.when(backendContainer.getOrderLine()).thenReturn(ORDER_LINE);
        shopOrderRepository = new ShopOrderRepository(backendContainer);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("🟩 should load shop order")
    void shouldLoadShopOrder() {
        // GIVEN
        // WHEN
        final ShopOrder actualShopOrder = shopOrderRepository.loadShopOrder(SHOP_ORDER_ID);
        // THEN
        Assertions.assertNotNull(actualShopOrder);
        Assertions.assertEquals(SHOP_ORDER, actualShopOrder);
        Mockito.verify(backendContainer).getShopOrder();
        Mockito.verify(backendContainer).getProduct();
        Mockito.verify(backendContainer).getOrderLine();
        logger.info("shouldLoadShopOrder():");
    }

    @Test
    @DisplayName("🟩 should save shop order")
    void shouldSaveShopOrder() {
        // GIVEN
        // WHEN
        shopOrderRepository.saveShopOrder(SHOP_ORDER);
        // THEN
        logger.info("shouldSaveShopOrder():");
    }
}