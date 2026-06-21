package DataStructuresAndAlgorithms;
class Employee{
    String employeeId;
    String name;
    String position;
    long salary;
    public Employee(String id,String name,String pos,long sal){
        this.employeeId = id;
        this.name = name;
        this.position = pos;
        this.salary = sal;
    }
};

class ManageEmployee{
    Employee[] employees;
    int i;
    public ManageEmployee(int s){
        employees = new Employee[s];
        i = -1;
    }
    public void addEmployee(Employee e){
        i++;
        if(i>=employees.length){
            i--;
            System.out.println("Limit Exceeded , increase the size");
            return;
        }
        employees[i] = e;
        System.out.println("Employee Added");
        return;
    }
    public Employee searchEmployee(String employeeId){
        for(int j=0;j<employees.length;j++){
            if(employees[j] == null){
                break;
            }
            if(employees[j].employeeId.equals(employeeId)){
                System.out.println("Found");
                System.out.println(employees[j]);
                return employees[j];
            }
        }
        System.out.println("Not Found");
        return null;
    }
    public void deleteEmployee(String employeeId){
        int idx = -1;
        for(int j=0;j<employees.length;j++){
            if(employees[j] == null){
                break;
            }
            if(employees[j].employeeId.equals(employeeId)){
                idx = j;
                break;
            }
        }
        if(idx == -1){
            System.out.println("Employee Not Found");
            return;
        }
        int j=idx+1;
        while(j<employees.length && employees[j] != null){
            employees[j-1] = employees[j];
            j++;
        }
        employees[j-1] = null;
        System.out.println("Deleted");

        return;
    }
    public void reSizeArray(int newSize){
        Employee[] arr = new Employee[newSize];
        for(int j=0;j<employees.length;j++){
            if(employees[j] == null){
                break;
            }
            arr[j] = employees[j];
        }
        this.employees = arr;
        return;
    }
    public void displayEmployee(){
        for(Employee e:employees){
            if(e == null){
                break;
            }
            System.out.println(e.name);
        }
    }
}
public class EmployeeManagement{
    public static void main(String[] args) {
        ManageEmployee me = new ManageEmployee(3);
        me.addEmployee(new Employee("nA1", "Harish", "Developer", 1000000));
        me.addEmployee(new Employee("nA2", "Ramesh", "Senior Dev", 10000000));
        me.addEmployee(new Employee("nA3", "Mukesh", "Intern", 1000));
        me.addEmployee(new Employee("nA4", "Sukesh", "Manager", 10));
        me.searchEmployee("nA2");
        me.reSizeArray(5);
        me.addEmployee(new Employee("nA5", "Sukesh", "Manager", 10));
        me.displayEmployee();
        me.deleteEmployee("nA2");
        me.displayEmployee();
        
        // Arrays Limitations : 
        // 1. Traverse through whole array to find a employee with an Id (no quick access ).
        // 2. have to realocate all the employees after deleting an employee from data ,otherwise memory will be wasted.
        
        //Complexity of Each Operation 
        // 1. add -> TC -> O(1)
        // 2. searchEmployee -> TC -> O(n) (Id based)
        // 3. delteEmployee -> TC -> O(n) 
        // 4. resize data -> TC -> O(n)


        
    }
}
