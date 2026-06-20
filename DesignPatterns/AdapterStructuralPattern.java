interface Payments{
    public String processPayment(int amnt);
}

class Paypal{
    private int balance = 100;
    public int debit(int amnt){
        if(balance>=amnt){
            balance -= amnt;
            return this.balance;
        }
        return -1;
    }
};

class RazorPay{
    private int balance =100;
    public String debit(int amnt){
        if(balance>=amnt){
            balance -= amnt;
            return "Debited";
        }
        return "Insufficient Balance";
    }
    public int getBal(){
        return balance;
    }
}

class PaypalAdapter implements Payments{
    private Paypal paypal;
    public PaypalAdapter(){
        paypal = new Paypal();
    }
    public String processPayment(int a){
        int bal = paypal.debit(a);
        if(bal == -1){
            return "Insufficient Balance";
        }
        String ans = "Debited , remaining amount"+bal;
        return ans;
    }

}

class RazorPayAdapter implements Payments{
    private RazorPay rp;
    public RazorPayAdapter(){
        rp = new RazorPay();
    }
    public String processPayment(int a){
        String b = rp.debit(a);
        if(b.equalsIgnoreCase("Insufficient Balance")){
            return "Insufficient Balance";
        }
        String ans = "Debited , remaining amount"+rp.getBal();
        return ans;
    }
}
public class AdapterStructuralPattern {
    public static void main(String[] args) {
        Payments p1 = new PaypalAdapter();
        Payments p2 = new RazorPayAdapter();
        String ans = p1.processPayment(50);
        String ans1 = p2.processPayment(90);
        System.out.println(ans);
        System.out.println(ans1);
    }
    
}
