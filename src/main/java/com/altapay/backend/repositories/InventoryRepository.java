package com.altapay.backend.repositories;

import com.altapay.backend.model.Inventory;
import com.altapay.util.DummyDataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class InventoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public Inventory load(String productId) {
        // We don't need to implement this, write the rest of the code as if this has been implemented
        logger.info("load(): productId[{}]", productId);
        return DummyDataHelper.getDummyInventory();
    }

    public void save(Inventory inventory) throws Exception {
        // We don't need to implement this, write the rest of the code as if this has been implemented
        DummyDataHelper.dummyParameterlessMethodThrowingException();
        logger.info("save(): inventory[{}]", inventory.getInventory());
    }
}
