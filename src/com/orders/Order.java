package com.orders;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.inventory.Product;

public record Order(GregorianCalendar orderDate, String location, double totalAmount, ArrayList<Product> items) {

    public int getQuantity() {
        int quantity = 0;
        for (final Product p : items) {
            quantity += p.getQuantity();
        }
        return quantity;
    }

    public String toString() {
        return ("Date:" + orderDate.get(GregorianCalendar.DAY_OF_MONTH) + "/"
                + (orderDate.get(GregorianCalendar.MONTH) + 1) + "/" + orderDate.get(GregorianCalendar.YEAR)
                + "\tLocation:" + location + "\tItems:" + items + "\tTotal:" + totalAmount);
    }
}
