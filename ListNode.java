public class ListNode {
    public Person Person;
    public ListNode Next;

    public ListNode(int index){
        Person = new Person("Person " + index, 25);
        
    }

    public ListNode(int index, String action){
        if (action == "insert"){
            Person = new Person("Insert Person " + index, 25);
        } 
        else if (action == "append"){
            Person = new Person("Append Person " + index, 25);
        }
    }

    public ListNode(){

    }
}