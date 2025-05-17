import javax.swing.*;
import java.awt.Color;

public class BST_Insert {
    private final BSTVisualizer visualizer;

    public BST_Insert(BSTVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public void BG_Insert(BinaryNode node, int val) {
        SwingWorker<BinaryNode, Void> worker = new SwingWorker<>() {
            private BinaryNode updatedRoot;

            @Override
            protected BinaryNode doInBackground() {
                updatedRoot = insert(node, val); // Perform the insertion
                return updatedRoot;
            }

            @Override
            protected void done() {
                try {
                    visualizer.root = get(); // Update the root in the visualizer
                    visualizer.repaint(); // Refresh the display
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    BinaryNode insert(BinaryNode binaryNode, int value) {
        if (binaryNode == null) {
            binaryNode = new BinaryNode(value);
            if (visualizer.recentBinaryNode != null) {
                visualizer.recentBinaryNode.color = Color.GRAY; // Reset the last recent node
            }
            binaryNode.color = Color.RED; // Highlight the new node
            visualizer.setRecentNode(binaryNode);
            visualizer.repaint();
            visualizer.sleep(500); // Pause to visualize
            return binaryNode;
        }

        // Highlight the current node
        binaryNode.color = Color.RED;
        visualizer.repaint();
        visualizer.sleep(500);

        // Reset the current node to green after comparison
        binaryNode.color = Color.GREEN;
        visualizer.repaint();
        visualizer.sleep(500);

        if (value < binaryNode.value) {
            binaryNode.left = insert(binaryNode.left, value);
        } else if (value > binaryNode.value) {
            binaryNode.right = insert(binaryNode.right, value);
        }

        return binaryNode;
    }
}
