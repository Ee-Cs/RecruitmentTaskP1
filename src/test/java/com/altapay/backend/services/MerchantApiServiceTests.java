package com.altapay.backend.services;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.util.HttpUtil;
import com.altapay.util.XpathUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static com.altapay.backend.TestConstants.SHOP_ORDER;

class MerchantApiServiceTests {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final MerchantApiService merchantApiService = new MerchantApiService(new HttpUtil(), new XpathUtil());

    @Test
    @DisplayName("🟩 should capture payment")
    void shouldCapturePayment() throws MerchantApiServiceException {
        // GIVEN
        // WHEN
        final CaptureResponse captureResponse = merchantApiService.capturePayment(SHOP_ORDER);
        // THEN
        Assertions.assertNotNull(captureResponse);
        Assertions.assertTrue(captureResponse.wasSuccessful());
        logger.info("shouldCapturePayment():");
    }

    @Test
    @DisplayName("🟩 should release payment")
    void shouldReleasePayment() throws MerchantApiServiceException {
        // GIVEN
        // WHEN
        final ReleaseResponse releaseResponse = merchantApiService.releasePayment(SHOP_ORDER);
        // THEN
        Assertions.assertNotNull(releaseResponse);
        Assertions.assertTrue(releaseResponse.wasSuccessful());
        logger.info("shouldReleasePayment():");
    }
}