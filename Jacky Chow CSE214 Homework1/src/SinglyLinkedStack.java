// Jacky Chow 113268425
import java.util.*;
public class SinglyLinkedStack<T> implements CSE214Stack<T>{
    private SinglyLinkedCSE214List<T> temp;

    public SinglyLinkedStack() {
        temp = new SinglyLinkedCSE214List<>();
    }
    public SinglyLinkedStack(T arg) {
        temp = new SinglyLinkedCSE214List<>(arg);
    }

    public int size() {
        return temp.size();
    }

    public T peek() {
        if (temp.size() == 0) {
            throw new NoSuchElementException("Empty Stack");
        }
        else {
            // o(n) because of linked list head being in beginning
            return temp.tail();
        }
    }
    public T pop(){
        if (temp.size() == 0)
            throw new NoSuchElementException("Empty Stack");
        else {
            return temp.remove(temp.size()-1);
        }
    }
    public void push(T elem){
        temp.add(elem, temp.size());
    }

    public boolean isEmpty() {
        return temp.size() == 0;
    }

    void print() {
        temp.print();
    }

    public static void main(String[] args) {
        SinglyLinkedStack<String> stack1 = new SinglyLinkedStack<>();
        SinglyLinkedStack<String> stack2 = new SinglyLinkedStack<>("hello");
        stack2.push("a");
        stack2.push("b");
        stack2.push("c");
        stack2.push("g");
        stack2.pop();
        System.out.println(stack1.isEmpty());
        System.out.println(stack2.peek());
        System.out.println(stack2.size());
        stack2.print();
    }
}
