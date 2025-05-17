import javax.swing.*;
import java.awt.*;

public class DFS extends JFrame {
    private Graph map;
    private int n = -1;
    
    public DFS() {
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

        map = new Graph(n);
        map.generate_random();
        map.dfs_or_bfs = 0;

        JButton traverse_button = new JButton("Traverse");
        traverse_button.addActionListener(e -> {
            try {
                String S1 = JOptionPane.showInputDialog("Enter the source vertex (0 to " + (n-1) + "):");
                String S2 = JOptionPane.showInputDialog("Enter the destination vertex (0 to " + (n-1) + "):");
                if (S1 == null || S2 == null) return;
                int source = Integer.parseInt(S1);
                int target = Integer.parseInt(S2);
                if (source >= 0 && source < n && target >= 0 && target < n) {
                    map.startDFS(source, target);
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
                    map.startDFS(source);
                } 
                else throw new NumberFormatException();
            }

            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid vertices between 0 and " + (n-1) + ".");
            }
        }
        );

        JButton random_button = new JButton("Random Graph");
        random_button.addActionListener(e -> map.generate_random());

        JButton clear_button = new JButton("Clear Graph");
        clear_button.addActionListener(e -> map.clear());

        JButton add_edge_button = new JButton("Add Edge");
        add_edge_button.addActionListener(e -> {
            try {
                String S1 = JOptionPane.showInputDialog("Enter the first vertex (0 to " + (n-1) + "):");
                String S2 = JOptionPane.showInputDialog("Enter the second vertex (0 to " + (n-1) + "):");
                if (S1 == null || S2 == null) return;
                int first = Integer.parseInt(S1);
                int second = Integer.parseInt(S2);
                if (first >= 0 && first < n && second >= 0 && second < n) 
                    map.add_edge(first, second);
                else throw new IllegalArgumentException();
            } 
            catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid vertices between 0 and " + (n-1) + ".");
            }
        }
        );

        JButton remove_edge_button = new JButton("Remove Edge");
        remove_edge_button.addActionListener(e -> {
            try {
                String S1 = JOptionPane.showInputDialog("Enter the first vertex (0 to " + (n-1) + "):");
                String S2 = JOptionPane.showInputDialog("Enter the second vertex (0 to " + (n-1) + "):");
                if (S1 == null || S2 == null) return;
                int first = Integer.parseInt(S1);
                int second = Integer.parseInt(S2);
                if (first >= 0 && first < n && second >= 0 && second < n) 
                    map.remove_edge(first, second);
                else throw new IllegalArgumentException();
            } 
            catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Enter a valid vertices between 0 and " + (n-1) + ".");
            }
        }
        );

        JPanel buttons_panel = new JPanel();
        buttons_panel.add(traverse_button);
        buttons_panel.add(explore_button);
        buttons_panel.add(random_button);
        buttons_panel.add(clear_button);
        buttons_panel.add(add_edge_button);
        buttons_panel.add(remove_edge_button);

        setLayout(new BorderLayout());
        add(map, BorderLayout.CENTER);
        add(buttons_panel, BorderLayout.SOUTH);

        setTitle("DFS Graph Visualizer");
        setSize(1920, 820);
        setVisible(true);
    }
}
