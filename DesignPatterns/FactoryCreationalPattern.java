interface  Document{
    public void createDocument();
};

class PdfDocument implements Document{
    public void createDocument(){
        System.out.println("PDF is created");
    }
}

class WordDocument implements Document{
    public void createDocument(){
        System.out.println("Word is created");
    }
}

class ExcelDocument implements Document{
    public void createDocument(){
        System.out.println("Excel sheet is created");
    }
}

class DocumentFactory {
    public static Document getInstance(String type){
        if(type.equalsIgnoreCase("PDF")){
            return new PdfDocument();
        }
        if(type.equalsIgnoreCase("Word")){
            return new WordDocument();
        }
        if(type.equalsIgnoreCase("Excel")){
            return new ExcelDocument();
        }
        return null;
    }
}

public class FactoryCreationalPattern {
    public static void main(String[] args) {
        Document d1 = DocumentFactory.getInstance("PDf");
        Document d2 = DocumentFactory.getInstance("Word");
        Document d3 = DocumentFactory.getInstance("Excel");

        d1.createDocument();
        d2.createDocument();
        d3.createDocument();

    }
}
