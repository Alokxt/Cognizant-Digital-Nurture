import java.util.*;
interface Stock{
    public void add(subscribers s);
    public void register();
    public void withdraw();
    public void update();
}

interface subscribers{
    public void update();
}

class sb1 implements subscribers{
    public void update(){
        System.out.println("Sb1 was updated");
    }
}

class sb2 implements subscribers{
    public void update(){
        System.out.println("Sb2 was updated");
    }
}

class Notifier implements Stock{
    private List<subscribers> names = new ArrayList<>();
    public void add(subscribers s){
        names.add(s);
    }
    public void register(){
        System.out.println("Stock was registered");
        this.update();
    }
    public void withdraw(){
        System.out.println("IPO cancelled");
        this.update();
    }

    public void update(){
        for(subscribers sb:names){
            sb.update();
        }
    }
}
public class ObserverBehavePattern {
    public static void main(String[] args) {
        Stock st = new Notifier();
        st.add(new sb1());
        st.add(new sb2());
        st.register();
        st.withdraw();
    }
}
