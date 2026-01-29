package com.altapay.backend;

import com.altapay.backend.model.Inventory;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;

public class TestConstants {
    public static final String SHOP_ORDER_ID = "order-1";
    public static final String PAYMENT_ID = "payment-1";
    public static final String PRODUCT_ID = "product-";
    public static final String PRODUCT_NAME = "Test Product ";
    public static final int[] REQUESTED_QUANTITY = {11, 22, 33};
    public static final Product[] PRODUCTS = {new Product(), new Product(), new Product()};
    public static final Inventory INVENTORY = new Inventory();
    public static final ShopOrder SHOP_ORDER = new ShopOrder(null, null);
    public static final Product PRODUCT = new Product();
    public static final OrderLine ORDER_LINE = new OrderLine();
    public static final int AVAILABLE_QUANTITY = 2;
    public static final int REQUESTED_CORRECT_QUANTITY = AVAILABLE_QUANTITY - 1;
    public static final int REQUESTED_UNAVAILABLE_QUANTITY = AVAILABLE_QUANTITY + 1;

    private TestConstants() {
    }
}
