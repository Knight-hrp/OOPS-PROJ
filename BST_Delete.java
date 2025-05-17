import javax.swing.*;
import java.awt.*;

public class BST_Delete
{
    private final BSTVisualizer visualizer;

    public BST_Delete(BSTVisualizer visualizer)
    {
        this.visualizer = visualizer;
    }

    public void delete(BinaryNode root, int value)
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
                visualizer.root = deleteNode(root, value);
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

    private BinaryNode deleteNode(BinaryNode binaryNode, int value)
    {
        if(binaryNode == null)
        {
            return null;
        }

        binaryNode.color = Color.RED;
        visualizer.repaint();
        visualizer.sleep(500);

        if(value < binaryNode.value)
        {
            binaryNode.left = deleteNode(binaryNode.left, value);
        }
        else if(value > binaryNode.value)
        {
            binaryNode.right = deleteNode(binaryNode.right, value);
        }
        else
        {
            binaryNode.color = Color.BLUE;
            visualizer.repaint();
            visualizer.sleep(500);

            if(binaryNode.left == null)
            {
                return binaryNode.right;
            }
            else if
            (binaryNode.right == null)
            {
                return binaryNode.left;
            }

            binaryNode.value = minValue(binaryNode.right);
            binaryNode.color = Color.GREEN;
            visualizer.repaint();
            visualizer.sleep(500);
            binaryNode.right = deleteNode(binaryNode.right, binaryNode.value);
        }

        binaryNode.color = Color.GRAY;
        return binaryNode;
    }

    private int minValue(BinaryNode binaryNode)
    {
        int minValue = binaryNode.value;
        while(binaryNode.left != null)
        {
            binaryNode = binaryNode.left;
            minValue = binaryNode.value;
        }
        return minValue;
    }
}
