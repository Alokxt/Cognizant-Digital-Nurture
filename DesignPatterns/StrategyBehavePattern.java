interface PaymentStrategy{
    public void pay();
   
}
class PaypalPayment implements PaymentStrategy{
    @Override
    public void pay() {
        System.out.println("Payment by Paypal");
        
    }
}
class BHIM implements PaymentStrategy{
    @Override
    public void pay() {
        System.out.println("Payment by BHIM UPI");
        
    }
}
class ContextPayment{
    private PaymentStrategy strategy;
    public ContextPayment(){};
    public ContextPayment(PaymentStrategy  ps){
        this.strategy = ps;
    }
    public void setStrategy(PaymentStrategy ps){
        this.strategy = ps;
    }
   
    public void pay() {
        strategy.pay();
    }

}
public class StrategyBehavePattern {
    public static void main(String[] args) {
        ContextPayment payment = new ContextPayment(new PaypalPayment());
        payment.pay();
        payment.setStrategy(new BHIM());
        payment.pay();
    }
}
