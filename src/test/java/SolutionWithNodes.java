import java.util.ArrayList;
import java.util.List;

public class SolutionWithNodes {

    static class MyNode {
        String name;
        List<Link> links = new ArrayList<>();

        MyNode(String name) {
            this.name = name;
        }
    }

    static class Link {
        int through;
        int lossPercent;
        MyNode hub;

        Link(int through, int lossPercent, MyNode hub) {
            this.hub = hub;
            this.through = through;
            this.lossPercent = lossPercent;
        }
    }

    public static void main(String[] args) {
        // Инстанцирование согласно схемы графа
        MyNode A = new MyNode("A");
        MyNode B = new MyNode("B");
        MyNode C = new MyNode("C");
        MyNode D = new MyNode("D");
        MyNode E = new MyNode("E");
        MyNode F = new MyNode("F");

        A.links = List.of(new Link(1500, 90, B),
                          new Link(2000, 10, C),
                          new Link(1000, 50, D));
        B.links = List.of(new Link(1500, 60, F));
        C.links = List.of(new Link(500, 20, F),
                          new Link(900, 5, E));
        D.links = List.of(new Link(2500, 1, E));
        F.links = List.of(new Link( 300, 85, E));
    }
}


