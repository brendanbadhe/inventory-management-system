package com.inventory;

import java.util.ArrayList;

import com.alerts.lowStockAlert;
import com.alerts.outOfStockAlert;

public final class Inventory {
    public static final int MIN_QUANTITY = 15;
    public static ArrayList<Product> products = new ArrayList<>();

    public static synchronized void addProduct(final Product p) {
        products.add(p);
    }

    public static synchronized void addProduct(final int id, final int quantity) {
        for (final Product p : products) {
            if (p.getId() == id) {
                p.setQuantity(p.getQuantity() + quantity);
                return;
            }
        }
        System.out.println("Invalid Product ID");
    }

    public static synchronized Product procureProduct(final int id, final int qty) throws outOfStockAlert {
        for (final Product p : products) {
            if (p.getId() == id) {
                if (p.getQuantity() >= qty) {
                    p.setQuantity(p.getQuantity() - qty);
                    return new Product(p.getId(), p.getName(), p.getPrice(), qty, p.getType());
                } else if (p.getQuantity() == 0) {
                    throw new outOfStockAlert(p);
                } else {
                    System.out.println("Invalid Quantity");
                    return null;
                }
            }
        }
        System.out.println("Product not found");
        return null;
    }

    public static synchronized int goodCount() {
        int count = 0;
        for (final Product p : products) {
            if (p.getType().equalsIgnoreCase("Good")) {
                count += p.getQuantity();
            }
        }
        return count;
    }

    public static synchronized int cargoCount() {
        int count = 0;
        for (final Product p : products) {
            if (p.getType().equalsIgnoreCase("Cargo")) {
                count += p.getQuantity();
            }
        }
        return count;
    }

    public static synchronized void checkStock(final Product p) throws lowStockAlert {
        if (p.getQuantity() < MIN_QUANTITY) {
            throw new lowStockAlert(p);
        }
    }

    public static synchronized void productList() {
        if (products.isEmpty()) {
            System.out.println("No products found");
        } else {
            for (final Product p : products) {
                System.out.println(p);
            }
        }
    }
}
