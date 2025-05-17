import java.util.*;

public class Node {
    private int id;
    private int x;
    private int y;
    private LinkedList<Node> adjacency_list;

    public Node(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        adjacency_list = new LinkedList<Node>();
    }

    public int get_id(){
        return id;
    }

    public int get_x(){
        return x;
    }

    public int get_y(){
        return y;
    }

    public void set_x(int x){
        this.x = x;
    }
    
    public void set_y(int y){
        this.y = y;
    }

    public void add_adjacency(Node temp){
        adjacency_list.add(temp);
    }

    public void remove_adjacency(Node temp){
        adjacency_list.remove(temp);
    }

    public LinkedList<Node> get_adjacency() {
        return adjacency_list;
    }
}
