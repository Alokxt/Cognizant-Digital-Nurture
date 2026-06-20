package DataStructuresAndAlgorithms;

import java.util.*;
class Product{

    String productId;
    String productName;
    int quantity;
    int price;
    public Product(String pid,String pn,int q,int p){
        this.productId = pid;
        this.productName = pn;
        this.quantity = q;
        this.price = p;
    }
    public void setPrice(int p){
        this.price = p;
    }
    public void addItems(int q){
        this.quantity += q;
    }
    public void removeItems(int q){
        this.quantity -= q;
    }


};

class Inventory{
    private Map<String,Product> items = new HashMap<>();
    public int getItemPrice(String itemid){
        if(items.containsKey(itemid)){
            return items.get(itemid).price;
        }

        return 0;
    }
    public void setItemPrice(String itemId,int p){
        if(items.containsKey(itemId)){
            items.get(itemId).setPrice(p);
        }
    }
    public int buyItem(String itemId,int q){
        if(items.containsKey(itemId)){
            int p = items.get(itemId).price*q;
            items.get(itemId).removeItems(q);
            return p;
            
        }
        return 0;
    }
    public void addItems(String itemId,int q){
        if(items.containsKey(itemId)){
            items.get(itemId).quantity += q;
        }
        return;
    }
    public void addProduct(String pid,String pn,int q,int p){
        Product pro = new Product(pid, pn, q, p);
        items.put(pid, pro);
        return;
    }

}
public class InventoryManagementSystem{
    public static void main(String[] args) {
        Inventory invt = new Inventory();
        invt.addProduct("Jirawan", "Nidhi-Jirawan", 4, 20);
        invt.addProduct("09oi", "Brush", 100, 25);
        invt.addProduct("010oi", "toothpaste", 50, 100);

        System.out.println(invt.getItemPrice("Jirawan"));
        System.out.println(invt.buyItem("09oi", 20));
        

    }
}