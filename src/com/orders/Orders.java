package com.orders;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public final class Orders {
    public static ArrayList<Order> orders = new ArrayList<>();

    public static synchronized void addOrder(final Order order) {
        orders.add(order);
    }

    public static synchronized Order maxLocation(final String location) {
        int count = 0;
        Order maxOrder = null;
        for (final Order o : orders) {
            if (o.location().equalsIgnoreCase(location) && o.getQuantity() > count) {
                count = o.getQuantity();
                maxOrder = o;
            }
        }
        return maxOrder;
    }

    public static synchronized int overPeriod(final GregorianCalendar start, final GregorianCalendar end) {
        int count = 0;
        for (final Order o : orders) {
            if (start.compareTo(o.orderDate()) <= 0 && end.compareTo(o.orderDate()) >= 0) {
                count += o.getQuantity();
            }
        }
        return count;
    }

    public static synchronized Order maxValue() {
        double value = 0;
        Order maxOrder = null;
        for (final Order o : orders) {
            if (o.totalAmount() > value) {
                value = o.totalAmount();
                maxOrder = o;
            }
        }
        return maxOrder;
    }

    public static synchronized double allTimeSales() {
        double value = 0;
        for (final Order o : orders) {
            value += o.totalAmount();
        }
        return value;
    }

    public static synchronized void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found");
        } else {
            for (final Order o : orders) {
                System.out.println(o);
            }
        }
    }
}
