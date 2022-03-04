import java.io.IOException;

public class Assignment4 {

    public static void main(final String[] args) throws IOException {
        System.out.println("Hello world - this is assignment 4 in Java!");
        System.out.println("Let's create a linked list and start adding to it.");
        ListNode first = GenerateLinkedList(5);
        PrintLinkedList(first);
        System.out.println("Now let's append another node to our list and output the result.");
        Append(first);
        PrintLinkedList(first);
        System.out.println("Now let's insert at 0.");
        ListNode newfirst = InsertAt(0, first);
        PrintLinkedList(newfirst);
        System.out.println("Now let's insert at index 2.");
        InsertAt(2, newfirst);
        PrintLinkedList(newfirst);
        System.out.println("That's all. Thanks for joining me!");
    }

    public static ListNode GenerateLinkedList(int number){
        ListNode current = new ListNode();
        ListNode first = current;
        int index = 0;
        current.Person = new Person("Person " + index, 25);
        number--;
        index++;
        
        while (number > 0){
            current.Next = new ListNode();
            current.Next.Person = new Person("Person " + index, 25);
            index++;
            number--;
            current = current.Next;
        }
        return first;
    }

    //Gets the final node of a linked list. Used for our append method.
    public static ListNode GetFinalNode(ListNode node){
        ListNode currentNode = node;
        while (currentNode.Next != null){
            currentNode = currentNode.Next;
        }
        return currentNode;
    }

    //Finds a list node based on the index so it can be replaced.
    public static ListNode FindByIndex(ListNode first, int index){
        ListNode currentNode = first;
        if (index == 0){
            return currentNode;
        }
        int currentindex = 0;
        while (currentindex < index){
            try {
                currentNode = currentNode.Next;
                currentindex++;
            } catch (Exception ex){
                //If we made it here, we tried to access a null node. Return null since no node was found at index.
                return null;
            }
        }
        return currentNode;
        
    }

    //Prints a linked list to the console so we can view it.
    public static void PrintLinkedList(ListNode first){
        ListNode currentNode = first;
        System.out.println(currentNode.Person.Name);
        while(currentNode.Next != null){
            currentNode = currentNode.Next;
            System.out.println(currentNode.Person.Name);
        }
    }

    //Shifts index of all nodes after the current node up 1. This is to accomodate the insert method so the person names are adjusted according to the index.
    public static void ShiftIndex(ListNode node){
        int index =  Integer.parseInt(node.Person.Name.replaceAll("[^\\d-]", ""));
        index++;
        node.Person.Name = "Person " + index;
        ListNode currentNode = node;
        while (currentNode.Next != null){
            currentNode = currentNode.Next;
            index++;
            currentNode.Person.Name = currentNode.Person.Name.replaceAll("\\d","") + index;
        }
    }

    //Appends to end of linked list
    public static void Append(ListNode first){
        ListNode finalNode = GetFinalNode(first);
        int index = Integer.parseInt(finalNode.Person.Name.replaceAll("[^\\d-]", ""));
        finalNode.Next = new ListNode(index + 1, "append");
    }

    //Returns first node after inserting at a particular node index
    public static ListNode InsertAt(int index, ListNode first){
        if (index == 0){
            ListNode newfirst = new ListNode(0, "insert");
            ShiftIndex(first);
            newfirst.Next = first;
            return newfirst;
        }
        ListNode insert = FindByIndex(first, index);
        ListNode before = FindByIndex(first, index - 1);
        ShiftIndex(insert);
        ListNode newinsert = new ListNode(index, "insert");
        before.Next = newinsert;
        newinsert.Next = insert;
        return first;
    }


}