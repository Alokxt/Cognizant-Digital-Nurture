package DataStructuresAndAlgorithms;

class Node{
    String taskId;
    String taskName;
    boolean status;
    Node next;
    Node(String id,String nam){
        this.taskId = id;
        this.taskName = nam;
        status = false;
        next = null;
    }
};
class TasksManager{
    Node head;
    Node temp;
    public TasksManager(String tid,String tn){
        head = new Node(tid, tn);
        temp = head;
    }
    public void addTask(String tid,String tn){
        temp.next = new Node(tid, tn);
        temp = temp.next;
        System.out.println("Task Added");
    }
    public void deleteTask(String tid){
        Node curr = head;
        Node prev = null;
        while(curr != null){
            if(curr.taskId.equals(tid)){
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        if(curr == null){
            System.out.println("Task Not Found");
            return;
        }
        if(prev == null){
            if(temp == head){
                temp = head.next;
            }
            head = curr.next;
            System.out.println("Delete");
            return;
        }
        if(temp == curr){
            temp = prev;
        }
        prev.next = curr.next;
        System.out.println("Delete");

    }
    public void searchTask(String tid){
        Node curr = head;
      
        while(curr != null){
            if(curr.taskId.equals(tid)){
                break;
            }
            curr = curr.next;
        }
        if(curr == null){
            System.out.println("Task Not Found");
            return;
        }
        System.out.println("task found "+curr.taskName);
        return;
    }
    public void displayTask(){
        Node curr = head;
        while(curr != null){
            System.out.println(curr.taskName);
            curr = curr.next;
        }
        return;
    }

}
public class TaskManagement {
    public static void main(String[] args) {
        TasksManager tm = new TasksManager("A1", "DSA");
        tm.addTask("A2", "Devlopment");
        tm.addTask("A3", "GenAi");
        tm.deleteTask("A2");

        tm.searchTask("A3");
        tm.addTask("A4", "Cloud");
        tm.addTask("A5", "DevOps");
        tm.deleteTask("A5");
        tm.addTask("A6", "Death");

        tm.displayTask();

        //Time Complexity of each operation 
        // 1. add task , O(1) because we using a pointer temp at tail 
        // 2. search with id O(n) (can we increased using Maps)
        // 3. delete , for search the task- O(n), for deleting -> O(1) , dont need to rearrange the whole sequence like array
        // 4. traverse O(n)

        
    }
}
