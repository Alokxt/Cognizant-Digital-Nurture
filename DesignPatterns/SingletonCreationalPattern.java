class Singleton{
    private int database;
    private static Singleton instance;
    private Singleton(int d){
        this.database = d;
    }

    public static Singleton getInstance(int d){
        if(instance == null){
            instance = new Singleton(d);
            return instance;
        }
        return instance;
    }

    public int getDb(){
        return database;
    }
    

};

public class SingletonCreationalPattern{
    public static void main(String[] args) {
        Singleton db1 = Singleton.getInstance(9);
        Singleton db2 = Singleton.getInstance(10);
        System.out.println(db1.getDb());
        System.out.println(db2.getDb());
    }
}