import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectMain
{
    public static void main(String[] args)
    {
        JFrame homepage = new JFrame("Algorithm Visualizer");
        homepage.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        homepage.setSize(2880, 1800);
        homepage.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        panel.add(Box.createVerticalGlue());
        panel.add(Bubble("Bubble Sort"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(Selection("Selection Sort"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(Insertion("Insertion Sort"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(Quick("QuickSort"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(Merge("MergeSort"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(Bucket("BucketSort"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(Radix("RadixSort"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(DFS("Depth First Searching(DFS)"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(BFS("Breath First Searching(BFS)"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(DFSvsBFS("DFS vs BFS"));
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(BSTInsertion("Binary Search Tree Insertion"));
        panel.add(Box.createVerticalGlue());

        homepage.add(panel, BorderLayout.CENTER);
        homepage.setVisible(true);
    }

    private static JButton Bubble(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BubbleSort.BUBBLESORT();
            }
        });
        return Button;
    }

    private static JButton Selection(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                SelectionSort.SELECTIONSORT();
            }
        });
        return Button;
    }

    private static JButton Insertion(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                InsertionSort.INSERTIONSORT();
            }
        });
        return Button;
    }

    private static JButton Quick(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                QS.QUICKSORT();
            }
        });
        return Button;
    }

    private static JButton Merge(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MergeSort.MERGESORT();
            }
        });
        return Button;
    }

    private static JButton Bucket(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BucketSortVisualizer.BUCKETSORT();
            }
        });
        return Button;
    }

    private static JButton Radix(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                RadixSort.RADIXSORT();
            }
        });
        return Button;
    }

    private static JButton DFS(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DFS dfs = new DFS();
            }
        });
        return Button;
    }

    private static JButton BFS(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BFS bfs = new BFS();
            }
        });
        return Button;
    }

    private static JButton DFSvsBFS(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                DFSvsBFS vs = new DFSvsBFS();
            }
        });
        return Button;
    }

    private static JButton BSTInsertion(String a)
    {
        JButton Button = new JButton(a);
        Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Button.setSize(250,250);
        Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BSTVisualizer.BinarySearchTree();
            }
        });
        return Button;
    }
}