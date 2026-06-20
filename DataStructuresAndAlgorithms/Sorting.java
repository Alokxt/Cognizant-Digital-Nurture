package DataStructuresAndAlgorithms;

import java.util.Arrays;
 
class Order {
    int orderId;
    String customerName;
    double totalPrice;
 
    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }
 
    @Override
    public String toString() {
        return "[ID: " + orderId + ", Customer: " + customerName + ", Price: $" + String.format("%.2f", totalPrice) + "]";
    }
}
 
public class Sorting {
 
    static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }
 
    static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }
 
    static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
 
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
 
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
 
        return i + 1;
    }
 
    static void printOrders(Order[] orders) {
        for (Order o : orders) {
            System.out.println("  " + o);
        }
    }
 
    public static void main(String[] args) {
 
        Order[] orders = {
            new Order(1001, "Alice Johnson",  549.99),
            new Order(1002, "Bob Smith",      129.49),
            new Order(1003, "Carol White",   3200.00),
            new Order(1004, "David Brown",    899.75),
            new Order(1005, "Eva Martinez",   210.00),
            new Order(1006, "Frank Lee",     1450.60),
            new Order(1007, "Grace Kim",       75.30),
            new Order(1008, "Henry Wilson",  2800.00),
            new Order(1009, "Isla Turner",    640.20),
            new Order(1010, "Jack Nolan",     330.00)
        };
 
        Order[] bubbleOrders = Arrays.copyOf(orders, orders.length);
        Order[] quickOrders  = Arrays.copyOf(orders, orders.length);
 
       
 
        System.out.println("Original Orders:");
        printOrders(orders);
 
        System.out.println("\n--- Bubble Sort ---");
        long startBubble = System.nanoTime();
        bubbleSort(bubbleOrders);
        long endBubble = System.nanoTime();
        printOrders(bubbleOrders);
        System.out.println("Time: " + (endBubble - startBubble) + " ns");
 
        System.out.println("\n--- Quick Sort ---");
        long startQuick = System.nanoTime();
        quickSort(quickOrders, 0, quickOrders.length - 1);
        long endQuick = System.nanoTime();
        printOrders(quickOrders);
        System.out.println("Time: " + (endQuick - startQuick) + " ns");
 
    }
}
