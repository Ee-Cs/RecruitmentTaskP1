package com.altapay.backend.controllers;

import com.altapay.backend.model.ShopOrder;
import com.altapay.backend.repositories.ShopOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class BackendController {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final ShopOrderRepository shopOrderRepository;

    public BackendController(ShopOrderRepository shopOrderRepository) {
        this.shopOrderRepository = shopOrderRepository;
    }

    public void capturePayment(String shopOrderId) {
        final ShopOrder shopOrder = shopOrderRepository.loadShopOrder(shopOrderId);
        shopOrder.capture();
        shopOrderRepository.saveShopOrder(shopOrder);
        logger.info("capturePayment(): shopOrderId[{}]", shopOrderId);
    }

    public void releasePayment(String shopOrderId) {
        final ShopOrder shopOrder = shopOrderRepository.loadShopOrder(shopOrderId);
        shopOrder.release();
        shopOrderRepository.saveShopOrder(shopOrder);
        logger.info("releasePayment(): shopOrderId[{}]", shopOrderId);
    }

}
