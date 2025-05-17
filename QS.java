import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


class QSVisualizer extends JPanel {
    private int[] array;
    private int swapIndex1 = -1;
    private int swapIndex2 = -1;
    public int pivotIndex = -1;
    public int firstPointer = -1;
    public int secondPointer = -1;
    public int partition_i = -1;
    public int partition_j = -1;

    public QSVisualizer(int[] array) {
        this.array = array;
        setPreferredSize(new Dimension(800, 600));
    }

    public void highlightSwap(int index1, int index2, int pivotIndex) {
        this.swapIndex1 = index1;
        this.swapIndex2 = index2;
        this.pivotIndex = pivotIndex;
        repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int barWidth = getWidth() / array.length;

        for (int i = 0; i < array.length; i++) {
            if(i>=partition_i && i<= partition_j) {
                if (i == swapIndex1 || i == swapIndex2) {
                    g.setColor(Color.BLUE);
                    g.fillRect(i * barWidth, 100, barWidth - 5, barWidth);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 18));
                    g.drawString(String.valueOf(array[i]), i * barWidth + (barWidth - 5) / 2, 100 + barWidth / 2);
                } else if (i == pivotIndex) {
                    g.setColor(Color.red);
                    g.fillRect(i * barWidth, 100, barWidth - 5, barWidth);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 18));
                    g.drawString(String.valueOf(array[i]), i * barWidth + (barWidth - 5) / 2, 100 + barWidth / 2);
                } else if (i == firstPointer) {
                    g.setColor(Color.CYAN);
                    g.fillRect(i * barWidth, 100, barWidth - 5, barWidth);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 18));
                    g.drawString(String.valueOf(array[i]), i * barWidth + (barWidth - 5) / 2, 100 + barWidth / 2);
                } else if (i == secondPointer) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(i * barWidth, 100, barWidth - 5, barWidth);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 18));
                    g.drawString(String.valueOf(array[i]), i * barWidth + (barWidth - 5) / 2, 100 + barWidth / 2);
                } else {
                    g.setColor(Color.BLACK);
                    g.drawRect(i * barWidth, 100, barWidth - 5, barWidth);
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.BOLD, 18));
                    g.drawString(String.valueOf(array[i]), i * barWidth + (barWidth - 5) / 2, 100 + barWidth / 2); // Default color for elements
                }
            }
            else if(partition_i == array.length && partition_j == array.length)
            {
                g.setColor(Color.GREEN);
                g.fillRect(i * barWidth, 100, barWidth - 5, barWidth);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.drawString(String.valueOf(array[i]), i * barWidth + (barWidth - 5) / 2, 100 + barWidth / 2);
            }
            else
            {
                g.setColor(Color.gray);
                g.fillRect(i * barWidth, 100, barWidth - 5, barWidth);
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 18));
                g.drawString(String.valueOf(array[i]), i * barWidth + (barWidth - 5) / 2, 100 + barWidth / 2);
            }
        }
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(Color.RED);
        g.fillRect(10,217,10,10);
        g.setColor(Color.BLACK);
        g.drawString("Pivot [RED color] = " + (pivotIndex >= 0 ? array[pivotIndex] : "None"), 30, 230);
        g.setColor(Color.ORANGE);
        g.fillRect(10,217 + 31,10,10);
        g.setColor(Color.BLACK);
        g.drawString("i [ORANGE color] = " + firstPointer,30, 260);
        g.setColor(Color.CYAN);
        g.fillRect(10,217+62,10,10);
        g.setColor(Color.BLACK);
        g.drawString("j [CYAN color] = " + secondPointer, 30, 290);
        g.setColor(Color.BLUE);
        g.fillRect(10,217+93,10,10);
        g.setColor(Color.BLACK);
        g.drawString("Swap Index [BLUE color] = [" + swapIndex1 +" , "+swapIndex2+" ]", 30, 320);
        g.setColor(Color.GRAY);
        g.fillRect(10,217+124,10,10);
        g.setColor(Color.BLACK);
        g.drawString("GRAY Color: untraversed section",30 , 350);
        swapIndex1 = -1;
        swapIndex2 = -1;
    }

    public void updateArray(int[] newArray)
    {
        this.array = Arrays.copyOf(newArray, newArray.length);
        repaint();
    }
}

class Q_S{
    private QSVisualizer visualizer;

    public Q_S(int[] array, QSVisualizer visualizer)
    {
        this.visualizer = visualizer;
        quickSort(array, 0, array.length - 1);
        visualizer.partition_i = array.length;
        visualizer.partition_j = array.length;
        visualizer.updateArray(array);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            visualizer.pivotIndex = high;
            visualizer.partition_i = low;
            visualizer.partition_j = high;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int pivotIndex = partition(array, low, high);
            visualizer.updateArray(array);
            quickSort(array, low, pivotIndex-1);
            quickSort(array, pivotIndex+1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[(low+high)/2];
        visualizer.pivotIndex = (low+high)/2;
        visualizer.updateArray(array);
        swap(array,(low+high)/2,high,high);
        int i = low - 1;
        visualizer.updateArray(array);
        for (int j = low; j < high; j++) {
            visualizer.secondPointer = j;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            visualizer.updateArray(array);
            if (array[j] < pivot) {
                i++;
                visualizer.firstPointer = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                visualizer.updateArray(array);
                swap(array, i, j, high);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                visualizer.updateArray(array);
            }

        }
        i++;
        visualizer.firstPointer = i;
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        visualizer.updateArray(array);
        swap(array, i, high, high);
        return i;
    }

    private void swap(int[] array, int i, int j, int pivotIndex)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        visualizer.highlightSwap(i, j, pivotIndex);
        visualizer.updateArray(array);
    }
}

public class QS {
    public static void QUICKSORT() {
        int[] array = {10,9,8,7,6,5,4,3,2,1};

        QSVisualizer visualizer = new QSVisualizer(array);
        JFrame frame = new JFrame("QuickSort Visualization");
        frame.add(visualizer);
        frame.pack();
        frame.setVisible(true);

        JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton start = new JButton("Start");
        start.addActionListener(e ->
        {
            start.setEnabled(false);
            new Thread(() ->
            {
                new Q_S(array, visualizer);
                SwingUtilities.invokeLater(() ->
                {
                    start.setEnabled(true);
                });
            }).start();
        });
        button.add(start);
        frame.add(button, BorderLayout.SOUTH);

    }
}
