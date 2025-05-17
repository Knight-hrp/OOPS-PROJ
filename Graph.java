import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Graph extends JPanel implements MouseListener, MouseMotionListener{
    private int vertices;
    private Node[] nodes;
    private LinkedList<Integer> order = new LinkedList<Integer>();
    private Stack<Node> stack = new Stack<Node>();
    private Queue<Node> queue = new LinkedList<Node>();
    private int radius = 20;
    private int Radius = 200;
    private int pad_x = 300;
    private int pad_y = 300;
    private int[] neighbors;
    private boolean[] visited;
    private boolean found = false;
    private boolean finish = false;
    private int start;
    private int end;
    private int lastExploredNeighbor = -1;
    private Node drag = null;
    private int delay = 1000;
    public int dfs_or_bfs;

    public Graph (int vertices){
        this.vertices = vertices;
        nodes = new Node[vertices];
        visited = new boolean[vertices];
        neighbors = new int[vertices];
        for (int i = 0;i<vertices;i++){
            double angle = 2 * 3.141 / vertices * i;
            nodes[i] = new Node(i,(int)(pad_x+Radius*Math.sin(angle)),(int)(pad_y+Radius*Math.cos(angle + 3.141)));
        }
        setSize(1200, 800);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void add_edge(int node1,int node2){
        nodes[node1].add_adjacency(nodes[node2]);
        nodes[node2].add_adjacency(nodes[node1]);
        repaint();
    }

    public void remove_edge(int node1,int node2){
        nodes[node1].remove_adjacency(nodes[node2]);
        nodes[node2].remove_adjacency(nodes[node1]);
        repaint();
    }

    public Node[] get_nodes() {
    return nodes;
    }
    
    public void set_nodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public void clear(){
        nodes = new Node[vertices];
        order.clear();
        Arrays.fill(visited,false);
        Arrays.fill(neighbors,0);
        stack.clear();
        queue.clear();
        found = false;
        finish = false;
        lastExploredNeighbor = -1;
        repaint();
    }

    public void clear(int a){
        for (int i = 0; i < vertices; i++){
            for (int j = 0; j < i; j++){
                remove_edge(i, j);
            }
        }
        order.clear();
        Arrays.fill(visited,false);
        Arrays.fill(neighbors,0);
        stack.clear();
        queue.clear();
        found = false;
        finish = false;
        lastExploredNeighbor = -1;
        repaint();
    }

    public void reset(){
        stack.clear();
        queue.clear();
        Arrays.fill(neighbors,0);
        Arrays.fill(visited,false);
        order.clear();
        found = false;
        finish = false;
        lastExploredNeighbor = -1;
    }

    public void random_edge(){
        Random rand = new Random();
        int[][] arr = new int[2][15];
        int count = 0;

        while (count < 15) {
            Boolean flag = false;

            arr[0][count] = rand.nextInt(vertices);
            arr[1][count] = rand.nextInt(vertices);

            if (arr[0][count] == arr[1][count])
                continue;

            for (int i = 0; i < count; i++) {
                if ((arr[0][i] == arr[0][count] && arr[1][i] == arr[1][count]) || (arr[0][i] == arr[1][count] && arr[1][i] == arr[0][count])) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                add_edge(arr[0][count], arr[1][count]);
                count++;
            }
        }
    }

    public void random_edge(int vertices){
        Random rand = new Random();
        int[][] arr = new int[2][10];
        int count = 0;

        while (count < 10) {
            Boolean flag = false;

            arr[0][count] = rand.nextInt(vertices);
            arr[1][count] = rand.nextInt(vertices);

            if (arr[0][count] == arr[1][count])
                continue;

            for (int i = 0; i < count; i++) {
                if ((arr[0][i] == arr[0][count] && arr[1][i] == arr[1][count]) || (arr[0][i] == arr[1][count] && arr[1][i] == arr[0][count])) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                add_edge(arr[0][count], arr[1][count]);
                count++;
            }
        }
    }

    public void generate_random(){
        clear(1);
        if(vertices < 5)
        {
            for(int i=0 ; i<vertices ; i++)
            {
                for(int j=0 ; j<i ; j++)
                {
                    add_edge(i,j);
                }
            }
        }
        else if (vertices > 10)
            random_edge();
        else
            random_edge(vertices);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (Node i : nodes) {
            if (i != null) {
                for (Node j : i.get_adjacency()) {
                    g.drawLine(i.get_x(), i.get_y(), j.get_x(), j.get_y());
                }
            }
        }

        for (Node i : nodes) {
            if (i != null) {
                int id = i.get_id();
                if (!stack.isEmpty() && id == stack.peek().get_id() || dfs_or_bfs == 1 && id == lastExploredNeighbor) {
                    g.setColor(Color.GREEN);
                } else if (!queue.isEmpty() && id == queue.peek().get_id()) {
                    g.setColor(Color.PINK);
                } else if (order.contains(id)) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.YELLOW);
                }
                g.fillOval(i.get_x() - radius, i.get_y() - radius, 2 * radius, 2 * radius);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(id), i.get_x()-2, i.get_y()+5);
            }
        }

        if (dfs_or_bfs == 0) {
            g.setColor(Color.BLACK);
            g.drawString("Stack Representation:", getWidth() - 225, getHeight() - 80);
            int stack_tos = getHeight() - 100;
            for (Node temp : stack) {
                if (temp != null) {
                    g.setColor(Color.YELLOW);
                    g.fillRect(getWidth() - 200, stack_tos - 30, 60, 30);
                    g.setColor(Color.BLACK);
                    g.drawRect(getWidth() - 200, stack_tos - 30, 60, 30);
                    g.drawString(String.valueOf(temp.get_id()), getWidth() - 175, stack_tos - 10);
                    stack_tos -= 35;
                }
            }
        }

        if (dfs_or_bfs == 1) {
            g.setColor(Color.BLACK);
            g.drawString("Queue Representation:", getWidth() - 225, getHeight() - 80);
            int queue_tos = getHeight() - 100;
            for (Node temp : queue) {
                if (temp != null) {
                    g.setColor(Color.CYAN);
                    g.fillRect(getWidth() - 200, queue_tos - 30, 60, 30);
                    g.setColor(Color.BLACK);
                    g.drawRect(getWidth() - 200, queue_tos - 30, 60, 30);
                    g.drawString(String.valueOf(temp.get_id()), getWidth() - 175, queue_tos - 10);
                    queue_tos -= 35;
                }
            }
        }

        if (found) {
            g.drawString("Target found. Path exists between " + start + " and " + end, 400, 700);
        } else if (finish) {
            g.drawString("Target not found. No path exists between " + start + " and " + end, 400, 700);
        }
    }
    
    
    public void mousePressed(MouseEvent e) {
        Point mousePos = e.getPoint();
        for (Node node : nodes) {
            if (node != null && mousePos.distance(node.get_x(), node.get_y()) <= radius) {
                drag = node;
                break;
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drag = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (drag != null) {
            drag.set_x(e.getX());
            drag.set_y(e.getY());
            repaint();
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public void startDFS(int start_vertex, int end_vertex) {
        dfs_or_bfs = 0;
        reset();
        this.start = start_vertex;
        this.end = end_vertex;
        stack.push(nodes[start_vertex]);
        visited[start_vertex] = true;
        order.add(start_vertex);
    
        javax.swing.Timer dfs_timer = new javax.swing.Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!stack.isEmpty()) {
                    Node current = stack.peek();
                    if (current.get_id() == end_vertex) {
                        found = true;
                        ((javax.swing.Timer) e.getSource()).stop();
                    } else {
                        LinkedList<Node> adjacency = current.get_adjacency();
                        boolean unvisited_adjacency = false;

                        for (int i = neighbors[current.get_id()]; i < adjacency.size(); i++) {
                            Node neighbor = adjacency.get(i);
                            neighbors[current.get_id()] = i + 1;
                            if (!visited[neighbor.get_id()]) {
                                stack.push(neighbor);
                                visited[neighbor.get_id()] = true;
                                order.add(neighbor.get_id());
                                unvisited_adjacency = true;
                                break;
                            }
                        }
    
                        if (!unvisited_adjacency) {
                            stack.pop();
                        }
                    }
                } else {
                    finish = true;
                    ((javax.swing.Timer) e.getSource()).stop();
                }
                repaint();
            }
        });
        dfs_timer.start();
    }
    
    public void startDFS(int start_vertex) {
        dfs_or_bfs = 0;
        reset();
        this.start = start_vertex;
        stack.push(nodes[start_vertex]);
        visited[start_vertex] = true;
        order.add(start_vertex);
    
        javax.swing.Timer dfs_timer = new javax.swing.Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!stack.isEmpty()) {
                    Node current = stack.peek();
                        LinkedList<Node> adjacency = current.get_adjacency();
                        boolean unvisited_adjacency = false;

                        for (int i = neighbors[current.get_id()]; i < adjacency.size(); i++) {
                            Node neighbor = adjacency.get(i);
                            neighbors[current.get_id()] = i + 1;
                            if (!visited[neighbor.get_id()]) {
                                stack.push(neighbor);
                                visited[neighbor.get_id()] = true;
                                order.add(neighbor.get_id());
                                unvisited_adjacency = true;
                                break;
                            }
                        }
    
                        if (!unvisited_adjacency) {
                            stack.pop();
                        }
                    }
                else {
                    ((javax.swing.Timer) e.getSource()).stop();
                }
                repaint();
            }
        });
        dfs_timer.start();
    }
    

    public void startBFS(int start_vertex, int end_vertex) {
        dfs_or_bfs = 1;
        reset();
        this.start = start_vertex;
        this.end = end_vertex;

        queue.add(nodes[start_vertex]);
        visited[start_vertex] = true;
        order.add(start_vertex);
    
        javax.swing.Timer bfs_timer = new javax.swing.Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!queue.isEmpty()) {
                    Node current = queue.peek();
                    boolean dequeue_flag = false;

                    for (Node neighbor : current.get_adjacency()) {
                        if (!visited[neighbor.get_id()]) {

                            queue.add(neighbor);
                            visited[neighbor.get_id()] = true;
                            order.add(neighbor.get_id());
                            lastExploredNeighbor = neighbor.get_id();
                            dequeue_flag = true;

                            if (neighbor.get_id() == end_vertex) {
                                found = true;
                                ((javax.swing.Timer) e.getSource()).stop();
                            }
    
                            break;
                        }
                    }
    
                    if (!dequeue_flag) {
                        queue.poll();
                        lastExploredNeighbor = -1;
                    }
    
                } else {
                    finish = true;
                    ((javax.swing.Timer) e.getSource()).stop();
                }
    
                repaint();
            }
        });
    
        bfs_timer.start();
    }
    
    public void startBFS(int start_vertex) {
        dfs_or_bfs = 1;
        reset();
        this.start = start_vertex;

        queue.add(nodes[start_vertex]);
        visited[start_vertex] = true;
        order.add(start_vertex);
    
        javax.swing.Timer bfs_timer = new javax.swing.Timer(delay, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!queue.isEmpty()) {
                    Node current = queue.peek();
                    boolean dequeue_flag = false;

                    for (Node neighbor : current.get_adjacency()) {
                        if (!visited[neighbor.get_id()]) {

                            queue.add(neighbor);
                            visited[neighbor.get_id()] = true;
                            order.add(neighbor.get_id());
                            lastExploredNeighbor = neighbor.get_id();
                            dequeue_flag = true;
    
                            break;
                        }
                    }
    
                    if (!dequeue_flag) {
                        queue.poll();
                        lastExploredNeighbor = -1;
                    }
    
                } else {
                    ((javax.swing.Timer) e.getSource()).stop();
                }
    
                repaint();
            }
        });
    
        bfs_timer.start();
    }

}
