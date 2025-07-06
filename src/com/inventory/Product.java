package com.inventory;

public final class Product {
    private static int count = 0;
    private final String name;
    private final double price;
    private final String type;
    private int id;
    private int quantity;

    public Product(final String name, final double price, final int quantity, final String modeOfShipment) {
        this.id = ++count;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        if (modeOfShipment.equalsIgnoreCase("Land")) {
            this.type = "Good";
        } else {
            this.type = "Cargo";
        }
    }

    public Product(final int id, final String name, final double price, final int quantity, final String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return ("ID:" + getId() + "\tName:" + getName() + "\tPrice:" + getPrice() + "\tQuantity:" + getQuantity()
                + "\tType:" + getType());
    }
}
