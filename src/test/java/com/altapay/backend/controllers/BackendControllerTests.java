package com.altapay.backend.controllers;

import com.altapay.backend.model.ShopOrder;
import com.altapay.backend.repositories.ShopOrderRepository;
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

import static com.altapay.backend.TestConstants.SHOP_ORDER_ID;

public class BackendControllerTests {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private BackendController backendController;
    @Mock
    private ShopOrderRepository shopOrderRepository;
    @Mock
    private ShopOrder shopOrder;
    private AutoCloseable closeable;

    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        Mockito.when(shopOrderRepository.loadShopOrder(SHOP_ORDER_ID)).thenReturn(shopOrder);
        backendController = new BackendController(shopOrderRepository);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("🟩 capture reservation - gets the order from the repository")
    public void captureReservationGetsTheOrderFromTheRepository() {
        // GIVEN
        // WHEN
        backendController.capturePayment(SHOP_ORDER_ID);
        // THEN
        Mockito.verify(shopOrderRepository).loadShopOrder(SHOP_ORDER_ID);
        logger.info("captureReservationGetsTheOrderFromTheRepository():");
    }

    @Test
    @DisplayName("🟩 capture reservation - must invoke capture on the order")
    public void captureReservationMustInvokeCaptureOnTheOrder() {
        // GIVEN
        // WHEN
        backendController.capturePayment(SHOP_ORDER_ID);
        // THEN
        Mockito.verify(shopOrder).capture();
        logger.info("captureReservationMustInvokeCaptureOnTheOrder():");
    }

    @Test
    @DisplayName("🟩 capture reservation - saves the order to the repository")
    public void captureReservationSavesTheOrderToTheRepository() {
        // GIVEN
        // WHEN
        backendController.capturePayment(SHOP_ORDER_ID);
        // THEN
        Mockito.verify(shopOrderRepository).saveShopOrder(shopOrder);
        logger.info("captureReservationSavesTheOrderToTheRepository():");
    }

    @Test
    @DisplayName("🟩 release reservation - gets the order from the repository")
    public void releaseReservationGetsTheOrderFromTheRepository() {
        // GIVEN
        // WHEN
        backendController.releasePayment(SHOP_ORDER_ID);
        // THEN
        Mockito.verify(shopOrderRepository).loadShopOrder(SHOP_ORDER_ID);
        logger.info("releaseReservationGetsTheOrderFromTheRepository():");
    }

    @Test
    @DisplayName("🟩 release reservation - must invoke release on the order")
    public void releaseReservationMustInvokeReleaseOnTheOrder() {
        // GIVEN
        // WHEN
        backendController.releasePayment(SHOP_ORDER_ID);
        // THEN
        Mockito.verify(shopOrder).release();
        logger.info("releaseReservationMustInvokeReleaseOnTheOrder():");
    }

    @Test
    @DisplayName("🟩 release reservation - saves the order to the repository")
    public void releaseReservationSavesTheOrderToTheRepository() {
        // GIVEN
        // WHEN
        backendController.releasePayment(SHOP_ORDER_ID);
        // THEN
        Mockito.verify(shopOrderRepository).saveShopOrder(shopOrder);
        logger.info("releaseReservationSavesTheOrderToTheRepository():");
    }

}
