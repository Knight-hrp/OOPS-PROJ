import javax.swing.*;
import java.awt.*;

public class DFSvsBFS extends JFrame {
    private Graph dfs_map;
    private Graph bfs_map;
    private int n = -1;

    public DFSvsBFS() {
        Boolean flag = true;
        while (flag){
            try {
                String input = JOptionPane.showInputDialog("Enter the number of nodes (Max 20 nodes)");
                if (input == null)
                    System.exit(0);
                n = Integer.parseInt(input);
                if (n >= 0 && n <= 20)
                    flag = false;
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,"Enter a valid number between 0 to 20");
            }
        }

        dfs_map = new Graph(n);
        bfs_map = new Graph(n);
        dfs_map.generate_random();
        bfs_map.set_nodes(dfs_map.get_nodes());
        dfs_map.dfs_or_bfs = 0;
        bfs_map.dfs_or_bfs = 1;

        JSplitPane split_map = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dfs_map, bfs_map);
        split_map.setResizeWeight(0.5);

        JPanel button_panel = new JPanel();
        
        JButton traverse_button = new JButton("Traverse");
        traverse_button.addActionListener(e -> {
            try {
                String S1 = JOptionPane.showInputDialog("Enter the source vertex (0 to " + (n-1) + "):");
                String S2 = JOptionPane.showInputDialog("Enter the destination vertex (0 to " + (n-1) + "):");
                if (S1 == null || S2 == null) return;
                int source = Integer.parseInt(S1);
                int target = Integer.parseInt(S2);
                if (source >= 0 && source < n && target >= 0 && target < n) {
                    dfs_map.startDFS(source,target);
                    bfs_map.startBFS(source,target);
                } 
                else throw new NumberFormatException();
            } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid vertices between 0 and " + (n-1) + ".");
            }
        }
        );

        JButton explore_button = new JButton("Explore");
        explore_button.addActionListener(e -> {
            try {
                String S = JOptionPane.showInputDialog("Enter the source vertex (0 to " + (n-1) + "):");
                if (S == null) return;
                int source = Integer.parseInt(S);
                if (source >= 0 && source < n) {
                    dfs_map.startDFS(source);
                    bfs_map.startBFS(source);
                } 
                else throw new NumberFormatException();
            }

            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid vertices between 0 and " + (n-1) + ".");
            }
        }
        );

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> {
            Timer timer = new Timer(1, event -> {
                dfs_map.repaint();
                bfs_map.repaint();
            });
            timer.setRepeats(false);
            timer.start();
        });

        JButton random_generate = new JButton("Random Graph");
        random_generate.addActionListener(e -> {
            dfs_map.generate_random();
            bfs_map.set_nodes(dfs_map.get_nodes());
            dfs_map.repaint();
            bfs_map.repaint();
        }
        );

        JButton clear_button = new JButton("Clear Graph");
        clear_button.addActionListener(e -> {
            dfs_map.clear();
            bfs_map.clear();
        }
        );

        JButton add_edge_button = new JButton("Add Edge");
        add_edge_button.addActionListener(e -> {
            try {
                String input_1 = JOptionPane.showInputDialog("Enter vertex 1 (0 to " + (n-1) + "):");
                String input_2 = JOptionPane.showInputDialog("Enter vertex 2 (0 to " + (n-1) + "):");
                if (input_1 == null || input_2 == null) return;
                int vertex_1 = Integer.parseInt(input_1);
                int vertex_2 = Integer.parseInt(input_2);
                if (vertex_1 >= 0 && vertex_1 < 12 && vertex_2 >= 0 && vertex_2 < 12)
                    {
                        dfs_map.add_edge(vertex_1, vertex_2);
                        bfs_map.add_edge(vertex_1,vertex_2);
                    }
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid vertices between 0 and " + (n-1) + ".");
            }
        }
        );

        JButton remove_edge_button = new JButton("Remove Edge");
        remove_edge_button.addActionListener(e -> {
            try {
                String input_1 = JOptionPane.showInputDialog("Enter vertex 1 (0 to " + (n-1) + "):");
                String input_2 = JOptionPane.showInputDialog("Enter vertex 2 (0 to " + (n-1) + "):");
                if (input_1 == null || input_2 == null) return;
                int vertex_1 = Integer.parseInt(input_1);
                int vertex_2 = Integer.parseInt(input_2);
                if (vertex_1 >= 0 && vertex_1 < 12 && vertex_2 >= 0 && vertex_2 < 12)
                {
                    dfs_map.remove_edge(vertex_1, vertex_2);
                    bfs_map.remove_edge(vertex_1,vertex_2);
                }
                else throw new IllegalArgumentException();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid vertices between 0 and " + (n-1) + ".");
            }
        }
        );

        button_panel.add(traverse_button);
        button_panel.add(explore_button);
        button_panel.add(random_generate);
        button_panel.add(clear_button);
        button_panel.add(add_edge_button);
        button_panel.add(remove_edge_button);
        button_panel.add(refresh);

        setLayout(new BorderLayout());
        add(button_panel, BorderLayout.SOUTH);
        add(split_map, BorderLayout.CENTER);

        setTitle("DFS vs BFS Graph Comparison");
        setSize(1920, 820);
        setVisible(true);
    }
}
