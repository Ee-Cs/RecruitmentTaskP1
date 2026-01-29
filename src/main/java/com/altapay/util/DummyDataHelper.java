package com.altapay.util;

import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.ShopOrder;

public class DummyDataHelper {

    public static final String ORDER_LINE_PRODUCT_ID = "1";
    public static final String ORDER_LINE_PRODUCT_NAME = "Keyboard";
    public static final int ORDER_LINE_PRODUCT_QUANTITY = 1;

    public static void setShopOrderPaymentId(ShopOrder shopOrder) {
        shopOrder.setPaymentId("1");
    }

    public static void setInventoryQuantity(Inventory inventory) {
        inventory.setInventory(0);
    }

    public static Inventory getDummyInventory() {
        return new Inventory();
    }

    public static void dummyCheck(boolean successful) {
    }

    public static void dummyParameterlessMethodThrowingException() throws Exception {
    }

    private DummyDataHelper() {
    }
}
