package DataStructuresAndAlgorithms;
import java.util.Arrays;
import java.util.Comparator;
 
class Product {
    int productId;
    String productName;
    String category;
 
    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
 
    @Override
    public String toString() {
        return "[ID: " + productId + ", Name: " + productName + ", Category: " + category + "]";
    }
}
 
public class Ecommerce{
 
    static Product linearSearch(Product[] products, int targetId) {
        for (Product product : products) {
            if (product.productId == targetId) {
                return product;
            }
        }
        return null;
    }
 
    static Product binarySearch(Product[] sortedProducts, int targetId) {
        int low = 0;
        int high = sortedProducts.length - 1;
 
        while (low <= high) {
            int mid = low + (high - low) / 2;
 
            if (sortedProducts[mid].productId == targetId) {
                return sortedProducts[mid];
            } else if (sortedProducts[mid].productId < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
 
    public static void main(String[] args) {
 
        Product[] products = {
            new Product(105, "Wireless Headphones", "Electronics"),
            new Product(203, "Running Shoes", "Footwear"),
            new Product(312, "Coffee Maker", "Appliances"),
            new Product(401, "Yoga Mat", "Sports"),
            new Product(556, "Bluetooth Speaker", "Electronics"),
            new Product(678, "Winter Jacket", "Clothing"),
            new Product(789, "Laptop Stand", "Accessories"),
            new Product(890, "Protein Powder", "Health"),
            new Product(934, "Desk Lamp", "Home"),
            new Product(999, "Smart Watch", "Electronics")
        };
 
        Product[] sortedProducts = products.clone();
        Arrays.sort(sortedProducts, Comparator.comparingInt(p -> p.productId));
 
        int targetId = 556;
 
        System.out.println("=== E-Commerce Platform Search ===\n");
 
        System.out.println("--- Linear Search ---");
        long startLinear = System.nanoTime();
        Product linearResult = linearSearch(products, targetId);
        long endLinear = System.nanoTime();
        System.out.println("Result : " + (linearResult != null ? linearResult : "Not Found"));
        System.out.println("Time   : " + (endLinear - startLinear) + " ns");
 
        System.out.println("\n--- Binary Search ---");
        long startBinary = System.nanoTime();
        Product binaryResult = binarySearch(sortedProducts, targetId);
        long endBinary = System.nanoTime();
        System.out.println("Result : " + (binaryResult != null ? binaryResult : "Not Found"));
        System.out.println("Time   : " + (endBinary - startBinary) + " ns");
 
        System.out.println("\n=== Complexity Analysis ===");
        System.out.println("Linear Search  -> Best: O(1) | Average: O(n) | Worst: O(n)");
        System.out.println("Binary Search  -> Best: O(1) | Average: O(log n) | Worst: O(log n)");
        System.out.println("\nFor large product catalogs, Binary Search is preferred.");
        System.out.println("It requires a sorted array but scales far better as n grows.");
    }
}


