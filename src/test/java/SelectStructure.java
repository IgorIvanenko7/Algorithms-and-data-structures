import org.junit.jupiter.api.Test;

import java.util.*;

public class SelectStructure {

    @Test()
    public void caseStackTest1() {
        // Create a new stack
        Stack<Integer> s = new Stack<>();

        //push(1), push(2), push(3), pop(), push(4), peek()
        s.push(1);
        s.push(2);
        s.push(3);
        s.pop();
        s.push(4);
        var lastItem = s.peek();
        System.out.println(lastItem);
        System.out.println("------------------------------");
        System.out.println(s);

        Collections.reverse(s);
        // Content of the stack
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }

    @Test()
    public void caseQueueTest() {
        Queue<Character> q = new PriorityQueue();
        q.offer('A');
        q.offer('B');
        q.offer('C');
        System.out.println("------------------------------");
        System.out.println(q.poll());
        System.out.println("------------------------------");
        q.offer('D');
        System.out.println(q.peek());
        System.out.println("---sequence items---------------------------");
        System.out.println(q);
    }


    @Test()
    public void caseDynamicArrTest() {
        Vector<Character> vector = new Vector<>(2);
//        System.out.println(vector.capacity());
//        System.out.println(vector.capacity());

        vector.add('X');
        vector.add('Y');
        System.out.println(vector);
        System.out.println("------------------------------");
        vector.add('Z');
        vector.add('W');
        System.out.println("------------------------------");

    }

}
