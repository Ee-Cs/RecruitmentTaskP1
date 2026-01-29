package com.altapay.backend.services;

import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.Product;
import com.altapay.backend.repositories.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class InventoryService {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean checkInventory(Product product, int quantity) {
        final Inventory inventory = inventoryRepository.load(product.getId());
        int availableQuantity = inventory.getInventory();
        final boolean flag = availableQuantity >= quantity;
        logger.info("checkInventory(): product id[{}], product name[{}], quantity[{}], flag[{}]",
                product.getId(), product.getName(), quantity, flag);
        return flag;
    }

    public boolean takeFromInventory(Product product, int quantity) {
        final Inventory inventory = inventoryRepository.load(product.getId());
        inventory.setInventory(inventory.getInventory() - quantity);
        try {
            inventoryRepository.save(inventory);
        } catch (Exception e) {
            logger.error("takeFromInventory(): product id[{}], product name[{}], exception[{}]",
                    product.getId(), product.getName(), e.getMessage());
            return false;
        }
        logger.info("takeFromInventory(): product id[{}], product name[{}], quantity[{}]",
                product.getId(), product.getName(), quantity);
        return true;
    }
}
