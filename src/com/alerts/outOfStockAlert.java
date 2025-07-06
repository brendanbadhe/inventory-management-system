package com.alerts;

import com.inventory.Product;

public final class outOfStockAlert extends Exception {
    public outOfStockAlert(final Product product) {
        super(product.toString());
    }
}
