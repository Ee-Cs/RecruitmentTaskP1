package com.altapay.backend.ioc;

import com.altapay.backend.controllers.BackendController;
import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;
import com.altapay.backend.repositories.ShopOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

class BackendContainerTests {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final BackendContainer backendContainer = new BackendContainer();

    @Test
    @DisplayName("🟩 should get backend controller")
    void shouldGetBackendController() {
        // GIVEN
        // WHEN
        final BackendController backendController = backendContainer.getBackendController();
        // THEN
        Assertions.assertNotNull(backendController);
        logger.info("shouldGetBackendController():");
    }

    @Test
    @DisplayName("🟩 should get shop order repository")
    void shouldGetShopOrderRepository() {
        // GIVEN
        // WHEN
        final ShopOrderRepository shopOrderRepository = backendContainer.getShopOrderRepository();
        // THEN
        Assertions.assertNotNull(shopOrderRepository);
        logger.info("shouldGetShopOrderRepository():");
    }

    @Test
    @DisplayName("🟩 should get shop order")
    void shouldGetShopOrder() {
        // GIVEN
        // WHEN
        final ShopOrder shopOrder = backendContainer.getShopOrder();
        // THEN
        Assertions.assertNotNull(shopOrder);
        Assertions.assertNull(shopOrder.getId());
        Assertions.assertEquals("1", shopOrder.getPaymentId());
        Assertions.assertNull(shopOrder.getOrderLines());
        logger.info("shouldGetShopOrder():");
    }

    @Test
    @DisplayName("🟩 should get inventory")
    void shouldGetInventory() {
        // GIVEN
        // WHEN
        final Inventory inventory = backendContainer.getInventory();
        // THEN
        Assertions.assertNotNull(inventory);
        Assertions.assertEquals(0, inventory.getInventory());
        logger.info("shouldGetInventory():");
    }

    @Test
    @DisplayName("🟩 should get order line")
    void shouldGetOrderLine() {
        // GIVEN
        // WHEN
        final OrderLine orderLine = backendContainer.getOrderLine();
        // THEN
        Assertions.assertNotNull(orderLine);
        Assertions.assertNull(orderLine.getProduct());
        Assertions.assertEquals(0, orderLine.getQuantity());
        logger.info("shouldGetOrderLine():");
    }

    @Test
    @DisplayName("🟩 should get product")
    void shouldGetProduct() {
        // GIVEN
        // WHEN
        final Product product = backendContainer.getProduct();
        // THEN
        Assertions.assertNotNull(product);
        Assertions.assertNull(product.getId());
        Assertions.assertNull(product.getName());
        logger.info("shouldGetProduct():");
    }
}