package com.altapay.backend.model;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.backend.services.CaptureResponse;
import com.altapay.backend.services.InventoryService;
import com.altapay.backend.services.MerchantApiService;
import com.altapay.backend.services.ReleaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class ShopOrder {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private String id;
    private String paymentId;
    private List<OrderLine> orderLines;
    final InventoryService inventoryService;
    final MerchantApiService merchantApiService;

    public ShopOrder(InventoryService inventoryService, MerchantApiService merchantApiService) {
        this.inventoryService = inventoryService;
        this.merchantApiService = merchantApiService;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void capture() {
        orderLines.forEach(this::captureOrder);
        logger.info("capture():");
    }

    private void captureOrder(OrderLine orderLine) {
        final Product product = orderLine.getProduct();
        final int quantity = orderLine.getQuantity();
        final boolean checkFlag = inventoryService.checkInventory(product, quantity);
        if (!checkFlag) {
            logger.warn("captureOrder(): not available in inventory, product id[{}], product name[{}], quantity[{}]",
                    product.getId(), product.getName(), quantity);
            return;
        }
        CaptureResponse captureResponse = null;
        try {
            captureResponse = merchantApiService.capturePayment(this);
        } catch (MerchantApiServiceException e) {
            logger.error("captureOrder(): product id[{}], product name[{}], captureResponse[{}], MerchantApiServiceException[{}]",
                    product.getId(), product.getName(), captureResponse, e.getMessage());
            return;
        }
        final boolean takeFlag = inventoryService.takeFromInventory(product, quantity);
        if (!takeFlag) {
            logger.warn("captureOrder(): taking product from inventory failed, product id[{}], product name[{}], quantity[{}]",
                    product.getId(), product.getName(), quantity);
            return;
        }
        logger.info("captureOrder(): product id[{}], product name[{}], quantity[{}]",
                product.getId(), product.getName(), quantity);
    }

    // Release is a synonym for canceling a payment
    public void release() {
        ReleaseResponse releaseResponse = null;
        try {
            releaseResponse = merchantApiService.releasePayment(this);
        } catch (MerchantApiServiceException e) {
            logger.error("release(): releaseResponse[{}], MerchantApiServiceException[{}]",
                    releaseResponse, e.getMessage());
            return;
        }
        logger.info("release():");
    }
}
