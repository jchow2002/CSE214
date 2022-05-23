// Jacky Chow 113268425
import java.util.*;
public class SinglyLinkedCSE214List<T> implements CSE214List<T> {
    public class Node {
        public T val;
        public Node next;

        public Node(T val) {
            this.val= val;
            this.next = null;
        }

        public String toString() {
            return "(" + val + ") -> ";
        }
    }
    Node head;

    public SinglyLinkedCSE214List(){
        head = null;
    }
    public SinglyLinkedCSE214List(T arg){
        head = new Node(arg);
    }

    public int size() {
        int count = 0;
        for (Node ptr = this.head; ptr != null; ptr=ptr.next) {
            count += 1;
        }
        return count;
    }

    public void add(T elem, int index) {
        Node ptr = head;
        int counter = 0;
        if (index == 0){
            head = new Node(elem);
        }
        else if (index == 1 && ptr.next == null) {
            ptr.next = new Node(elem);
        }
        else {
            while (ptr.next != null && counter < index - 1) {
                ptr = ptr.next;
                counter++;
            }
            Node ptr2 = new Node(elem);
            ptr2.next = ptr.next;
            ptr.next = ptr2;
        }
    }
    public void add(T elem){
        Node ptr = new Node(elem);
        ptr.next = head;
        head = ptr;
    }
    //remove index
    public T remove(int index) {
        T removedVal = head.val;

        if (this.head == null) {
            throw new NoSuchElementException("Empty LinkedList");
        } else if (this.head.next == null && index == 0) {
            this.head = null;
        }
        else if (index == 0) {
            head = head.next;
        }
        else {
            Node ptr = this.head.next;
            Node prev = this.head;
            int indexCounter = 0;
            while (ptr != null) {
                if (indexCounter == index -1) {
                    removedVal = prev.next.val;
                    prev.next = ptr.next;
                }
                ptr = ptr.next;
                prev = prev.next;
                indexCounter++;
            }

        }
        return removedVal;
    }
    public void remove(T elem) {
        // If empty
        if (this.head == null) {
            throw new NoSuchElementException("Empty LinkedList");
        }
        // If 1 element and we want to delete that element
        else if (this.head.next == null && this.head.val == elem) {
            this.head = null;
        }
        else if (elem == head.val) {
            head = head.next;
        }
        else {
        Node ptr = this.head.next;
        Node prev = this.head;

        while (ptr != null) {
            if (ptr.val == elem) {
                prev.next = ptr.next;
            }

            ptr = ptr.next;
            prev = prev.next;
            }
        }
    }

    public boolean find(T elem){
        for (Node ptr = this.head; ptr != null; ptr = ptr.next) {
            if (ptr.val == elem) {
                return true;
            }
        }
        return false;
    }

    public void print(){
        for (Node ptr = this.head; ptr != null; ptr = ptr.next) {
            System.out.print(ptr);
        }
    }
    // used for peek method in stack
    public T tail() {
        Node temp = head;
        while(temp.next != null) {
            temp = temp.next;
        }
        return temp.val;
    }

    public static void main(String[] args) {
        CSE214List<String> string = new SinglyLinkedCSE214List<>();
        CSE214List<String> strings = new SinglyLinkedCSE214List<>("a");
        string.add("123");
        string.add("456");
        string.add("798");
        string.add("1010");
        string.add("999");
        System.out.println(string.remove(1));

        strings.add("jacky", 1);
        System.out.println(strings.size());
        System.out.println(strings.find("jacky"));
    }
}
