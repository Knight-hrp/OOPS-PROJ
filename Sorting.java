import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Sorting extends JFrame
{
    protected Sorting.VisualizerPanel panel;
    protected List<Integer> UserArray;
    protected JButton insert, clear, random, start;

    public Sorting(String title)
    {
        setTitle(title);
        setSize(2880, 1800);
        setLayout(new BorderLayout());

        UserArray = new ArrayList<>();
        panel = CreatePanel(new int[0]);
        add(panel, BorderLayout.CENTER);

        JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        insert = new JButton("Insert");
        insert.addActionListener(e ->
        {
            String input = JOptionPane.showInputDialog(this, "Enter an integer to insert:");
            try
            {
                int value = Integer.parseInt(input);
                UserArray.add(value);
                panel.setArray(UserArray.stream().mapToInt(Integer::intValue).toArray());
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        button.add(insert);

        random = new JButton("Random");
        random.addActionListener(e ->
        {
            try
            {
                UserArray.clear();
                panel.setArray(new int[0]);
                int value;
                for(int i=0 ; i<10 ; i++)
                {
                    Random val = new Random();
                    value = val.nextInt(-20, 20);
                    UserArray.add(value);
                }
                panel.setArray(UserArray.stream().mapToInt(Integer::intValue).toArray());
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        button.add(random);


        start = new JButton("Start");
        start.addActionListener(e ->
        {
            insert.setEnabled(false);
            start.setEnabled(false);
            clear.setEnabled(false);

            new Thread(() ->
            {
                panel.algorithm(UserArray.stream().mapToInt(Integer::intValue).toArray());

                SwingUtilities.invokeLater(() ->
                {
                    insert.setEnabled(true);
                    start.setEnabled(true);
                    clear.setEnabled(true);
                });
            }).start();
        });
        button.add(start);


        clear = new JButton("Clear");
        clear.addActionListener(e -> {
            UserArray.clear();
            panel.setArray(new int[0]);
            panel.repaint();
            insert.setEnabled(true);
            start.setEnabled(true);
        });
        button.add(clear);

        add(button, BorderLayout.SOUTH);
        setVisible(true);
    }

    protected abstract VisualizerPanel CreatePanel(int[] array);

    public abstract class VisualizerPanel extends JPanel
    {
        private int[] array;
        int Index1 = -1;
        int Index2 = -1;

        public VisualizerPanel(int[] array)
        {
            this.array = array;
        }

        public void setArray(int[] array)
        {
            this.array = array;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            int width = 100;
            int height = 50;
            int x = 50;
            int y = 150;

            for (int i = 0; i < array.length; i++)
            {
                if(i == Index1 || i == Index2)
                {
                    g.setColor(Color.RED);
                }
                else
                {
                    g.setColor(Color.GREEN);
                }
                g.fillRect(x + i * width, y, width, height);
                g.setColor(Color.BLACK);
                g.drawRect(x + i * width, y, width, height);

                g.setFont(new Font("Arial", Font.BOLD, 24));
                FontMetrics metrics = g.getFontMetrics();
                int textX = x + i * width + (width - metrics.stringWidth(String.valueOf(array[i]))) / 2;
                int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
                g.drawString(String.valueOf(array[i]), textX, textY);
            }
        }

        public abstract void algorithm(int[] array);
    }
}
