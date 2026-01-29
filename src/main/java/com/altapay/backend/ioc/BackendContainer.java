package com.altapay.backend.ioc;

import com.altapay.backend.controllers.BackendController;
import com.altapay.backend.model.*;
import com.altapay.backend.repositories.InventoryRepository;
import com.altapay.backend.repositories.ShopOrderRepository;
import com.altapay.backend.services.InventoryService;
import com.altapay.backend.services.MerchantApiService;
import com.altapay.util.DummyDataHelper;
import com.altapay.util.HttpUtil;
import com.altapay.util.XpathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class BackendContainer implements IModelFactory {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ShopOrderRepository shopOrderRepository = null;

    public BackendController getBackendController() {
        return new BackendController(getShopOrderRepository());
    }

    public ShopOrderRepository getShopOrderRepository() {
        if (shopOrderRepository == null) {
            shopOrderRepository = new ShopOrderRepository(this);
        }
        return shopOrderRepository;
    }

    @Override
    public ShopOrder getShopOrder() {
        final InventoryRepository inventoryRepository = new InventoryRepository();
        final InventoryService inventoryService = new InventoryService(inventoryRepository);
        final MerchantApiService merchantApiService = new MerchantApiService(new HttpUtil(), new XpathUtil());
        final ShopOrder shopOrder = new ShopOrder(inventoryService, merchantApiService);
        DummyDataHelper.setShopOrderPaymentId(shopOrder);
        logger.info("getShopOrder():");
        return shopOrder;
    }

    @Override
    public Inventory getInventory() {
        final Inventory inventory = new Inventory();
        final Product product = getProduct();
        inventory.setProduct(product);
        DummyDataHelper.setInventoryQuantity(inventory);
        logger.info("getInventory(): product id[{}], inventory[{}]",
                inventory.getProduct().getId(), inventory.getInventory());
        return inventory;
    }

    @Override
    public OrderLine getOrderLine() {
        final OrderLine orderLine = new OrderLine();
        logger.info("getOrderLine():");
        return orderLine;
    }

    @Override
    public Product getProduct() {
        final Product product = new Product();
        logger.info("getProduct():");
        return product;
    }

}
