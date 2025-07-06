package com.manager;

import java.util.GregorianCalendar;
import java.util.Scanner;

import com.alerts.lowStockAlert;
import com.inventory.Inventory;
import com.inventory.Product;
import com.orders.Orders;

public final class ManagerThread implements Runnable {
    public Thread thread;

    public ManagerThread(final String name) {
        thread = new Thread(this, name);
        thread.start();
    }

    public void run() {
        int option = 0;
        final Scanner scanner = new Scanner(System.in);
        synchronized (this) {
            while (option != 7) {
                System.out.println("Menu - " + thread.getName());
                System.out.println(
                        "1.Add Product\t2.Update Quantity\t3.Check Notifications\t4.View Statistics\t5.View Inventory\t6.View Orders\t7.Exit");
                System.out.print("Enter your choice: ");
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.print("Enter Name: ");
                        final String name = scanner.next();
                        System.out.print("Enter Price: ");
                        final double price = scanner.nextDouble();
                        System.out.print("Enter Quantity: ");
                        final int quantity = scanner.nextInt();
                        System.out.print("Enter Mode [land/sea]: ");
                        String modeOfShipment = scanner.next();
                        while (!(modeOfShipment.equalsIgnoreCase("land") || modeOfShipment.equalsIgnoreCase("sea"))) {
                            modeOfShipment = scanner.next();
                            System.out.println("Invalid Mode. Try Again!");
                        }
                        Inventory.addProduct(new Product(name, price, quantity, modeOfShipment));
                        break;
                    case 2:
                        System.out.print("Enter ID: ");
                        final int id = scanner.nextInt();
                        System.out.print("Enter Quantity: ");
                        final int qty = scanner.nextInt();
                        Inventory.addProduct(id, qty);
                        break;
                    case 3:
                        int flag = 0;
                        for (final Product p : Inventory.products) {
                            try {
                                Inventory.checkStock(p);
                            } catch (final lowStockAlert e) {
                                flag = 1;
                                System.err.println(e);
                            }
                        }
                        if (flag == 0 || Inventory.products.isEmpty()) {
                            System.out.println("No new notifications");
                        }
                        break;
                    case 4:
                        System.out.println("Total Remaining Goods: " + Inventory.goodCount());
                        System.out.println("Total Remaining Cargo: " + Inventory.cargoCount());
                        System.out.println(
                                "Total Remaining Products: " + (Inventory.goodCount() + Inventory.cargoCount()));
                        if (Orders.orders.isEmpty()) {
                            System.out.println("No orders found");
                            break;
                        }
                        System.out.println("Largest Order(Mumbai): " + Orders.maxLocation("Mumbai"));
                        System.out.println("Largest Order(Delhi): " + Orders.maxLocation("Delhi"));
                        System.out.println("Largest Order(Bangalore): " + Orders.maxLocation("Bangalore"));
                        System.out.println("Largest Order(International): " + Orders.maxLocation("International"));
                        System.out.println("Products sold between two valid dates");
                        System.out.println("Enter Start Date (DD-MM-YYYY): ");
                        int day = scanner.nextInt();
                        int month = scanner.nextInt();
                        int year = scanner.nextInt();
                        final GregorianCalendar start = new GregorianCalendar(year, month - 1, day);
                        System.out.println("Enter End Date (DD-MM-YYYY): ");
                        day = scanner.nextInt();
                        month = scanner.nextInt();
                        year = scanner.nextInt();
                        final GregorianCalendar end = new GregorianCalendar(year, month - 1, day);
                        System.out.println("Products sold over period: " + Orders.overPeriod(start, end));
                        System.out.println("Largest Order(by value): " + Orders.maxValue());
                        System.out.println("All Time Sales: " + Orders.allTimeSales());
                        break;
                    case 5:
                        Inventory.productList();
                        break;
                    case 6:
                        Orders.viewOrders();
                        break;
                    case 7:
                        break;
                    default:
                        System.out.println("Invalid Option");
                        break;
                }
            }
        }
    }
}
