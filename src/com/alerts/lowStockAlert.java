package com.alerts;

import com.inventory.Product;

public final class lowStockAlert extends Exception {
    public lowStockAlert(final Product product) {
        super(product.toString());
    }
}
