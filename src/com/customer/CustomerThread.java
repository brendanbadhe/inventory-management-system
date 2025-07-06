package com.customer;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import com.alerts.outOfStockAlert;
import com.inventory.Inventory;
import com.inventory.Product;
import com.orders.Order;
import com.orders.Orders;

public final class CustomerThread implements Runnable {
    public Thread thread;
    ArrayList<Product> cart;

    public CustomerThread(final String name) {
        thread = new Thread(this, name);
        cart = new ArrayList<>();
        thread.start();
    }

    public void run() {
        int option = 0;
        final Scanner scanner = new Scanner(System.in);
        synchronized (this) {
            while (option != 5) {
                System.out.println("Menu - " + thread.getName());
                System.out.println("1.View Catalog\t2.Add to Cart\t3.Place Order\t4.View Cart\t5.Exit");
                System.out.print("Enter your choice: ");
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        Inventory.productList();
                        break;
                    case 2:
                        System.out.print("Enter ID: ");
                        final int id = scanner.nextInt();
                        System.out.print("Enter Quantity: ");
                        final int qty = scanner.nextInt();
                        try {
                            final Product procurement = Inventory.procureProduct(id, qty);
                            int flag = 0;
                            if (procurement != null) {
                                procurement.setId(id);
                                for (final Product p : cart) {
                                    if (p.getId() == procurement.getId()) {
                                        p.setQuantity(p.getQuantity() + qty);
                                        flag = 1;
                                        break;
                                    }
                                }
                                if (flag == 0) {
                                    cart.add(procurement);
                                }
                                System.out.println("Item added to cart");
                            } else {
                                System.out.println("Try Again");
                            }
                        } catch (final outOfStockAlert e) {
                            System.err.println(e);
                            System.out.println("Continue Shopping?[Y/N]");
                            final char answer = scanner.next().toUpperCase().charAt(0);
                            if (answer != 'Y') {
                                System.out.println("Purchase Aborted");
                                for (final Product p : cart) {
                                    Inventory.addProduct(p.getId(), p.getQuantity());
                                }
                                cart.clear();
                                break;
                            }
                        }
                        break;
                    case 3:
                        if (cart.isEmpty()) {
                            System.out.println("Please add products to cart before proceeding");
                            break;
                        }
                        System.out.println("Locations");
                        System.out.println("Mumbai\tDelhi\tBangalore\tInternational");
                        System.out.println("Enter Location: ");
                        String location;
                        while (true) {
                            location = scanner.next();
                            if (location.equalsIgnoreCase("Mumbai") || location.equalsIgnoreCase("Delhi")
                                    || location.equalsIgnoreCase("Bangalore")
                                    || location.equalsIgnoreCase("International")) {
                                break;
                            }
                            System.out.println("Invalid Location. Try Again!");
                        }
                        double amount = 0;
                        for (final Product p : cart) {
                            amount += p.getQuantity() * p.getPrice();
                        }
                        System.out.println("Enter Date (DD MM YYYY): ");
                        final int day = scanner.nextInt();
                        final int month = scanner.nextInt();
                        final int year = scanner.nextInt();
                        final GregorianCalendar orderDate = new GregorianCalendar(year, month - 1, day);
                        final ArrayList<Product> items = new ArrayList<>(cart);
                        final Order order = new Order(orderDate, location, amount, items);
                        Orders.addOrder(order);
                        System.out.println("Order Confirmed!");
                        System.out.print("Mode of Shipment: ");
                        if (order.location().equalsIgnoreCase("International")) {
                            System.out.println("Sea");
                        } else {
                            System.out.println("Land");
                        }
                        System.out.println(order);
                        System.out.print("Expected date of delivery: ");
                        final GregorianCalendar expectedDate = new GregorianCalendar(year, month, day);
                        System.out.println(expectedDate.get(GregorianCalendar.DAY_OF_MONTH) + "/"
                                + (expectedDate.get(GregorianCalendar.MONTH) + 1) + "/"
                                + expectedDate.get(GregorianCalendar.YEAR));
                        cart.clear();
                        break;
                    case 4:
                        if (cart.isEmpty()) {
                            System.out.println("Cart is Empty");
                        } else {
                            for (final Product p : cart) {
                                System.out.println(p);
                            }
                        }
                        break;
                    case 5:
                        for (final Product p : cart) {
                            Inventory.addProduct(p.getId(), p.getQuantity());
                        }
                        cart.clear();
                        break;
                    default:
                        System.out.println("Invalid Option");
                        break;
                }
            }
        }
    }
}
