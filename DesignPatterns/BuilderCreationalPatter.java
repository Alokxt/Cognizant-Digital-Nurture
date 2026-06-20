class student{
    String name;
    String id;
    long contact;
    String guardians;
    boolean isTopper;
    student(Builder bob){
        this.name = bob.name;
        this.id = bob.id;
        this.contact = bob.contact;
        this.guardians = bob.guardians;
        this.isTopper = bob.isTopper;
    }
    static class Builder{
    String name;
    String id;
    long contact;
    String guardians;
    boolean isTopper;

    public Builder setname(String name){
        this.name = name;
        return this;
    }
    public Builder setid(String id){
        this.id = id;
        return this;
    }
    public Builder setcontact(long c){
        this.contact =c;
        return this;
    }
    public Builder setguardians(String p){
        this.guardians = p;
        return this;
    }
    public Builder setisTopper(boolean t){
        this.isTopper = t;
        return this;
    }
    public student build(){
        return new student(this);
    }

}
}



public class BuilderCreationalPatter {
    public static void main(String[] args) {
        student usr = new student.Builder().setcontact(93872377).setname("Rahul").setguardians("Anita").setid("p08yu").setisTopper(true).build();
        System.out.println(usr.name+" "+usr.id+" "+usr.guardians);

    }
}
