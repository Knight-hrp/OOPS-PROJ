import javax.swing.*;
import java.awt.*;

class BinaryNode
{
    int value;
    BinaryNode left, right;
    Color color = Color.GRAY;

    BinaryNode(int value)
    {
        this.value = value;
    }
}

public class BSTVisualizer extends JPanel
{
    BinaryNode root;
    BinaryNode recentBinaryNode = null;
    private final int[] elements = {50, 30, 70, 20, 40, 60, 80, 10};

    public BSTVisualizer()
    {
        setPreferredSize(new Dimension(2880, 1800));
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JButton insertButton = new JButton("Insert");
        JButton searchButton = new JButton("Search");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(insertButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        insertButton.addActionListener(e ->
        {
            String input = JOptionPane.showInputDialog("Enter number to insert:");
            if(input != null)
            {
                try
                {
                    int value = Integer.parseInt(input);
                    resetNodeColors();
                    BST_Insert insertion = new BST_Insert(BSTVisualizer.this);
                    insertion.BG_Insert(root, value);
                    repaint();
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            }
        });

        searchButton.addActionListener(e ->
        {
            String input = JOptionPane.showInputDialog("Enter number to search:");
            if(input != null)
            {
                try
                {
                    int value = Integer.parseInt(input);
                    resetNodeColors();
                    BST_Search search = new BST_Search(BSTVisualizer.this);
                    search.search(root, value);
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            }
        });

        deleteButton.addActionListener(e ->
        {
            String input = JOptionPane.showInputDialog("Enter number to delete:");
            if(input != null)
            {
                try
                {
                    int value = Integer.parseInt(input);
                    resetNodeColors();
                    BST_Delete deletion = new BST_Delete(BSTVisualizer.this);
                    deletion.delete(root, value);
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
                }
            }
        });

        new Thread(this::runInsertion).start();
    }


    private void runInsertion()
    {
        BST_Insert insertion = new BST_Insert(BSTVisualizer.this);
        for(int value : elements)
        {
            resetNodeColors();
            root = insertion.insert(root, value);
            repaint();
            sleep(500);
        }
        resetNodeColors();

        if(recentBinaryNode != null)
        {
            recentBinaryNode.color = Color.GRAY;
            recentBinaryNode = null;
            repaint();
        }
    }

    private void resetNodeColors()
    {
        resetColors(root);
        repaint();
    }

    private void resetColors(BinaryNode binaryNode)
    {
        if(binaryNode == null)
        {
            return;
        }
        binaryNode.color = Color.GRAY;
        resetColors(binaryNode.left);
        resetColors(binaryNode.right);
    }

    public void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(root != null)
        {
            drawTree(g, root, getWidth() / 2, 40, getWidth() / 4);
        }
    }

    public void drawTree(Graphics g, BinaryNode binaryNode, int x, int y, int xOffset)
    {
        g.setColor(binaryNode.color);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(binaryNode.value), x - 7, y + 5);

        if(binaryNode.left != null)
        {
            g.drawLine(x - 10, y + 10, x - xOffset + 10, y + 50 - 10);
            drawTree(g, binaryNode.left, x - xOffset, y + 50, xOffset / 2);
        }
        if(binaryNode.right != null)
        {
            g.drawLine(x + 10, y + 10, x + xOffset - 10, y + 50 - 10);
            drawTree(g, binaryNode.right, x + xOffset, y + 50, xOffset / 2);
        }
    }

    public static void BinarySearchTree()
    {
        JFrame frame = new JFrame("Binary Search Tree Visualizer");
        BSTVisualizer visualizer = new BSTVisualizer();
        frame.add(visualizer);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setRecentNode(BinaryNode binaryNode)
    {
        recentBinaryNode = binaryNode;
    }
}
