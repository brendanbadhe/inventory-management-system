import java.util.Scanner;

import com.customer.CustomerThread;
import com.manager.ManagerThread;

final class Main {
    public static void main(final String[] args) {
        int option;
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Main Menu");
            System.out.println("1.Manager\t2.Customer\t3.Exit");
            System.out.print("Enter your choice: ");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    final ManagerThread managerThread = new ManagerThread("Manager");
                    try {
                        managerThread.thread.join();
                    } catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter Number of Customers: ");
                    final int size = scanner.nextInt();
                    final CustomerThread[] customers = new CustomerThread[size];
                    for (int i = 0; i < size; i++) {
                        customers[i] = new CustomerThread("Customer " + (i + 1));
                        try {
                            customers[i].thread.join();
                        } catch (final InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }
}
