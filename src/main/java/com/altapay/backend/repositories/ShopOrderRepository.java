package com.altapay.backend.repositories;

import com.altapay.backend.model.IModelFactory;
import com.altapay.backend.model.OrderLine;
import com.altapay.backend.model.Product;
import com.altapay.backend.model.ShopOrder;
import com.altapay.util.DummyDataHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class ShopOrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final IModelFactory modelFactory;

    public ShopOrderRepository(IModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public ShopOrder loadShopOrder(String shopOrderId) {
        final ShopOrder shopOrder = modelFactory.getShopOrder();
        shopOrder.setId(shopOrderId);
        final List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(getOrderLine());
        shopOrder.setOrderLines(orderLines);
        logger.info("loadShopOrder(): shopOrder id[{}], orderLines size[{}]", shopOrderId, orderLines.size());
        return shopOrder;
    }

    private OrderLine getOrderLine() {
        final String id = DummyDataHelper.ORDER_LINE_PRODUCT_ID;
        final String name = DummyDataHelper.ORDER_LINE_PRODUCT_NAME;
        final int quantity = DummyDataHelper.ORDER_LINE_PRODUCT_QUANTITY;
        final Product product = modelFactory.getProduct();
        product.setId(id);
        product.setName(name);
        final OrderLine orderLine = modelFactory.getOrderLine();
        orderLine.setProduct(product);
        orderLine.setQuantity(quantity);
        logger.info("getOrderLine(): product id[{}], product name[{}], quantity[{}]",
                id, name, quantity);
        return orderLine;
    }

    public void saveShopOrder(ShopOrder shopOrder) {
        logger.info("saveShopOrder(): shopOrder id[{}], paymentId[{}]",
                shopOrder.getId(), shopOrder.getPaymentId());
    }

}
