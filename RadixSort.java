import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class RadixSort extends JFrame {
    private int[] array;
    private Color[][] colors;
    private int[][] states;
    private String[] headings = {"Unsorted Array", "Sorted by Ones Digit", "Sorted by Tens Digit", "Sorted by Hundreds Digit"};
    private static JButton start, RandomArray;
    public RadixSort(int[] array) {
        this.array = array;
        this.colors = new Color[4][array.length];
        this.states = new int[4][array.length];

        for (int i = 0; i < 4; i++) {
            Arrays.fill(colors[i], Color.lightGray);
        }

        System.arraycopy(array, 0, states[0], 0, array.length);

        setTitle("Radix Sort Visualizer");
        setSize(800, 800);
        setVisible(true);

        JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER, 20 ,10));
        start = new JButton("Start");
        start.addActionListener(e ->
        {
            start.setEnabled(false);
            RandomArray.setEnabled(false);
            new Thread(() ->
            {
                radixSort();
                SwingUtilities.invokeLater(() ->
                {
                    start.setEnabled(true);
                    RandomArray.setEnabled(true);
                });
            }).start();
        });
        button.add(start);


        RandomArray = new JButton("Random");
        RandomArray.addActionListener(e ->
        {
            Random random = new Random();
            for(int i = 0;i <8;i++)
            {
                this.array[i] = random.nextInt(100, 999);
            }
            this.states = new int[4][array.length];
            System.arraycopy(array, 0, states[0], 0, array.length);
            repaint();
        });
        button.add(RandomArray);
        add(button, BorderLayout.SOUTH);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int width = getWidth() / array.length;
        int height = 100;

        g.setFont(new Font("Arial", Font.BOLD, 15));
        for (int stage = 0; stage < 4; stage++) {
            int yOffset = stage * 150;

            g.setColor(Color.BLACK);
            g.drawString(headings[stage], 10, yOffset + 20);

            for (int i = 0; i < array.length; i++) {
                g.setColor(colors[stage][i]);
                g.fillRect(i * width, yOffset + 50, width, height);

                g.setColor(Color.BLACK);
                g.drawRect(i * width, yOffset + 50, width, height);
                g.drawString(String.valueOf(states[stage][i]), i * width + width / 2 - 5, yOffset + 100);
            }
        }
    }

    public void radixSort() {
        int max = getMax(array);
        for (int exp = 1, stage = 1; max / exp > 0 && stage < 4; exp *= 10, stage++) {
            countingSort(exp, stage);
            sleep(1000);
        }
        repaint();
    }

    private int getMax(int[] array) {
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private void countingSort(int exp, int stage) {
        int n = array.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int value : array) {
            count[(value / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int index = (array[i] / exp) % 10;
            output[count[index] - 1] = array[i];
            count[index]--;
            System.arraycopy(output, 0, states[stage], 0, n);


            recolor(stage);
            colors[stage-1][i] = Color.GREEN;
            if (count[index] >= 0)
            {
                colors[stage][count[index]] = Color.MAGENTA;
            }

            repaint();
            sleep(800);
        }
        recolor(stage-1);
        System.arraycopy(output, 0, array, 0, n);

        System.arraycopy(array, 0, states[stage], 0, n);
        recolor(stage);
        repaint();
    }

    private void recolor(int stage)
    {
        if(stage == 0)
        {
            Arrays.fill(colors[stage], Color.lightGray);
        }
        else if(stage == 1)
        {
            Arrays.fill(colors[stage], Color.YELLOW);
        }
        else if(stage == 2)
        {
            Arrays.fill(colors[stage], Color.ORANGE);
        }
        else if(stage == 3)
        {
            Arrays.fill(colors[stage], Color.CYAN);
        }
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void RADIXSORT() {
        int[] array = {170, 456, 700, 950, 900, 243, 900, 630};
        new RadixSort(array);
    }
}
