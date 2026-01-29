package com.altapay.backend.model;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.backend.services.InventoryService;
import com.altapay.backend.services.MerchantApiService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.altapay.backend.TestConstants.*;

public class ShopOrderTests {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ShopOrder shopOrder;
    @Mock
    private InventoryService inventoryService;
    @Mock
    private MerchantApiService merchantApiService;
    private AutoCloseable closeable;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        shopOrder = new ShopOrder(inventoryService, merchantApiService);
        shopOrder.setId(SHOP_ORDER_ID);
        shopOrder.setPaymentId(PAYMENT_ID);
        shopOrder.setOrderLines(prepareOrderLines());
        for (int i = 0; i < PRODUCTS.length; i++) {
            Mockito.when(inventoryService.checkInventory(PRODUCTS[i], REQUESTED_QUANTITY[i])).thenReturn(true);
            Mockito.when(inventoryService.takeFromInventory(PRODUCTS[i], REQUESTED_QUANTITY[i])).thenReturn(true);
        }
    }

    private List<OrderLine> prepareOrderLines() {
        final List<OrderLine> orderLines = new ArrayList<>();
        for (int i = 0; i < PRODUCTS.length; i++) {
            PRODUCTS[i].setId(PRODUCT_ID + i);
            PRODUCTS[i].setName(PRODUCT_NAME + i);
            final OrderLine orderLine = new OrderLine();
            orderLine.setProduct(PRODUCTS[i]);
            orderLine.setQuantity(REQUESTED_QUANTITY[i]);
            orderLines.add(orderLine);
        }
        return orderLines;
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("🟩 execute capture - inventory is checked")
    public void executeCapture_inventoryIsChecked() {
        // GIVEN
        // WHEN
        shopOrder.capture();
        // THEN
        for (int i = 0; i < PRODUCTS.length; i++) {
            Mockito.verify(inventoryService).checkInventory(PRODUCTS[i], REQUESTED_QUANTITY[i]);
        }
        logger.info("executeCapture_inventoryIsChecked():");
    }

    @Test
    @DisplayName("🟩 execute capture - payment is captured through API service")
    public void executeCapture_paymentIsCapturedThroughApiService() throws MerchantApiServiceException {
        // GIVEN
        // WHEN
        shopOrder.capture();
        // THEN
        Mockito.verify(merchantApiService, Mockito.times(PRODUCTS.length)).capturePayment(shopOrder);
        logger.info("executeCapture_paymentIsCapturedThroughApiService():");
    }

    @Test
    @DisplayName("🟩 execute capture - product is taken from inventory")
    public void executeCapture_productIsTakenFromInventory() {
        // GIVEN
        // WHEN
        shopOrder.capture();
        // THEN
        for (int i = 0; i < PRODUCTS.length; i++) {
            Mockito.verify(inventoryService).takeFromInventory(PRODUCTS[i], REQUESTED_QUANTITY[i]);
        }
        logger.info("executeCapture_productIsTakenFromInventory():");
    }

    @Test
    @DisplayName("🟥 execute capture - product is not available")
    public void executeCapture_productIsNotAvailable() throws MerchantApiServiceException {
        // GIVEN
        final Product product = new Product();
        final Random random = new Random();
        product.setId(String.valueOf(random.nextInt()));
        final OrderLine orderLine = new OrderLine();
        orderLine.setProduct(product);
        orderLine.setQuantity(1);
        final List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        shopOrder.setOrderLines(orderLines);
        // WHEN
        shopOrder.capture();
        // THEN
        Mockito.verify(inventoryService, Mockito.times(1))
                .checkInventory(Mockito.any(Product.class), Mockito.anyInt());
        Mockito.verify(merchantApiService, Mockito.never()).capturePayment(shopOrder);
        Mockito.verify(inventoryService, Mockito.never()).takeFromInventory(Mockito.any(Product.class), Mockito.anyInt());
        logger.info("executeCapture_productIsNotAvailable():");
    }

    @Test
    @DisplayName("🟥 execute capture - order is empty")
    public void executeCapture_orderIsEmpty() throws MerchantApiServiceException {
        // GIVEN
        shopOrder.setOrderLines(new ArrayList<>());
        // WHEN
        shopOrder.capture();
        // THEN
        Mockito.verify(inventoryService, Mockito.never()).checkInventory(Mockito.any(Product.class), Mockito.anyInt());
        Mockito.verify(merchantApiService, Mockito.never()).capturePayment(shopOrder);
        Mockito.verify(inventoryService, Mockito.never()).takeFromInventory(Mockito.any(Product.class), Mockito.anyInt());
        logger.info("executeCapture_orderIsEmpty():");
    }

    @Test
    @DisplayName("🟩 execute capture - payment is released through API service")
    public void executeRelease_paymentIsReleasedThroughApiService() throws MerchantApiServiceException {
        // GIVEN
        // WHEN
        shopOrder.release();
        // THEN
        Mockito.verify(merchantApiService).releasePayment(shopOrder);
        logger.info("executeRelease_paymentIsReleasedThroughApiService():");
    }
}
