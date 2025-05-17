import javax.swing.*;
import java.awt.*;

public class BST_Search
{
    private final BSTVisualizer visualizer;
    private boolean temp;

    public BST_Search(BSTVisualizer visualizer)
    {
        this.visualizer = visualizer;
    }

    public void search(BinaryNode root, int value)
    {
        if(root == null)
        {
            JOptionPane.showMessageDialog(null, "Tree is empty!");
            return;
        }

        SwingWorker<Void, Void> worker = new SwingWorker<>()
        {
            @Override
            protected Void doInBackground()
            {
                temp = searchNode(root, value);
                try
                {
                    if(temp == false)
                    {
                        throw new RuntimeException("No such element exists");
                    }
                }
                catch(RuntimeException e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
                return null;
            }

            @Override
            protected void done()
            {
                visualizer.repaint();
            }
        };
        worker.execute();
    }

    private boolean searchNode(BinaryNode binaryNode, int value)
    {
        if(binaryNode == null)
        {
            return false;
        }

        binaryNode.color = Color.RED;
        visualizer.repaint();
        visualizer.sleep(500);

        if(binaryNode.value == value)
        {
            binaryNode.color = Color.BLUE;
            visualizer.repaint();
            visualizer.sleep(500);
            return true;
        }

        binaryNode.color = Color.GREEN;
        visualizer.repaint();
        visualizer.sleep(500);

        if(value < binaryNode.value)
        {
            return searchNode(binaryNode.left, value);
        }
        else
        {
            return searchNode(binaryNode.right, value);
        }
    }
}
