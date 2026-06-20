interface Notifier{
    public String send(String msg);
}

class EmailNotifier implements Notifier{
    private Notifier notifier;
    public EmailNotifier(Notifier noti){
        notifier = noti;
    }
    public EmailNotifier(){};
    public String send(String msg){

        
        if(notifier == null){
            
            return msg+"Emailed";
        }
        String s1 = notifier.send(msg);
        return s1+" Emailed ";
    }
}

class SMSNotifier implements Notifier{
    private Notifier notifier;
    public SMSNotifier(){};
    public SMSNotifier(Notifier noti){
        notifier = noti;
    }
    public String send(String msg){
        
        if(notifier == null){
            return msg+"Smsed";
        }
        String s1 = notifier.send(msg);
        return s1+" Smsed ";
    }
}
class DbSave implements Notifier{
    private Notifier notifier;
    public DbSave(){};
    public DbSave(Notifier noti){
        notifier = noti;
    }
    public String send(String msg){
        
        if(notifier == null){
            return msg+" Saved ";
        }
        String s1 = notifier.send(msg);
        return s1+" Saved ";
    }
};

public class DecoratorStructuralPattern {
    public static void main(String[] args) {
        Notifier sav = new DbSave(new SMSNotifier(new EmailNotifier()));
        String ans = sav.send("Booked a ticket ");
        System.out.println(ans);
    }
    
    
}
