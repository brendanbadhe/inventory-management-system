# Inventory Management System

CLI-based Inventory Management System using Java

## Project Overview

This Inventory Management System is a command-line application designed to manage products, handle concurrent procurement requests, and generate useful statistics for inventory analysis. The system supports two user roles: Manager and Customer.

## Features

- **Product Management:**
  - Track products by unique ID, quantity, type (good/cargo), price, and shipment mode (land/sea).
  - Products are categorized as "good" or "cargo" based on shipment mode.
- **Concurrent Procurement:**
  - Handles multiple, simultaneous requests to procure products.
  - Notifies when a product is out of stock and allows the user to continue or abort the purchase.
  - Generates notifications when product stock falls below a specified threshold.
- **Statistics Generation:**
  - Maximum items ordered from a given location.
  - Total number of remaining products (good vs cargo).
  - Number of products ordered over a period.
- **User Roles:**
  - **Manager:** Add products, receive notifications, and view statistics.
  - **Customer:** View product list (with type, price, and details), add products to cart, and receive order confirmation (with total amount, shipment mode, and expected delivery date).

## Requirements

- Java 8 or higher
- Command-line interface (CLI)

## Project Structure

```
src/
  Main.java
  com/
    alerts/
      lowStockAlert.java
      outOfStockAlert.java
    customer/
      CustomerThread.java
    inventory/
      Inventory.java
      Product.java
    manager/
      ManagerThread.java
    orders/
      Order.java
      Orders.java
```

## How to Compile and Run

1. **Compile the project:**

   ```sh
   javac -d bin src/**/*.java
   ```

   This command compiles all Java files in the `src` directory (and its subdirectories) and places the compiled `.class` files into the `bin` directory.

2. **Run the project:**

   ```sh
   java -cp bin Main
   ```

   This command runs the `Main` class from the `bin` directory.

   - If your `Main` class is inside a package (e.g., `package com;`), use the fully qualified class name:

     ```sh
     java -cp bin com.Main
     ```

## Usage

1. Start the application from the command line as described above.
2. Follow the on-screen prompts to log in as a Manager or Customer.
3. Managers can add/view products, receive alerts, and view statistics.
4. Customers can browse products, place orders, and receive confirmations.

