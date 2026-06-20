class Student{
    String name;
    public Student(String n){
        this.name = n;
    }
    public String getName(){
        return name;
    }
}
class StudentRepository{
    private Student student;
    public void save(Student st){
        student = st;
    }
    public Student find(){
        return student;
    }

}
class StudentService{
    private StudentRepository studentRepository = new StudentRepository();
    public Student getStudent(){
        return studentRepository.find();
    }
    public void saveStudent(String m){
        studentRepository.save(new Student(m));
    }
}
//@RestController
class StudentController{
    
    private StudentService studentService = new StudentService();

    //getMapping()
    public String getUserName(){
        String name = studentService.getStudent().name;
        if(name == null || name.isEmpty()){
            return "status = NOT FOUND 404";
        }
        String reponse = name+" Status = 200 OK!";
        return reponse;
    }


    //postMapping
    public String saveUser(String s){
        studentService.saveStudent(s);
        return "Status = CREATED 201";
    }


}
//applicationcontext
public class MVC {
    public static void main(String[] args) {
        StudentController studentController = new StudentController();
        System.out.println(studentController.saveUser("Rahul"));
        System.out.println(studentController.getUserName());
    }
}
