interface Image{
    public String display();
}

class RealImage implements Image{
    public String display(){
        return "Image is displaying";
    }
}

class ProxyImage implements Image{
    private RealImage rm;
    public static int c=0;
    public int cached_thresh =3;
    public String respo;
    public ProxyImage(){
    
    }
    public String display(){
        if(c<cached_thresh && c >0){
            c++;
            return respo +" from cache";
        }
        if(rm == null){
            System.out.println("Object created");
            rm = new RealImage();
        }
        respo = rm.display();
        c = 1;
        return respo+" Intialized ";
        
    }
}
public class ProxyStructuralPattern {
    public static void main(String[] args) {
        Image im = new ProxyImage();
        for(int i=0;i<6;i++){
            System.out.println(im.display());
        }
    }
    
}
