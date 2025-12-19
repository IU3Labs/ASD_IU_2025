package IDDFS;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 {
    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        root.addChild(n2).addChild(n3);
        n2.addChild(n4).addChild(n5);
        n3.addChild(n6);

        int target = Integer.parseInt(br.readLine());
        int maxDepth = Integer.parseInt(br.readLine());;

        List<Node> path = IDDGSAlgorithm.iddfsPindPath(root, target, maxDepth);

        System.out.println("Path: " + IDDGSAlgorithm.pathToString(path));
    }

}
