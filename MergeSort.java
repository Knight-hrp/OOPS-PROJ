import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class MergeSort extends JFrame {
    private int[] array = new int[8];
    private int[] array1;
    private int[] array2;
    private int[] array3;
    private int[] array4;
    private int dividePadding = 1;
    private int conquerPadding = array.length;
    private int TIME = 0;
    private int Highlight1 = -1, Highlight2 = -1, Highlight3 = 4, Highlight4 = -1, Highlight5 = -1, Highlight6 = -1;

    private final AnimationPanel panel;
    private static JButton start, RandomArray;

    public MergeSort(){

        this.array1 = new int[8];
        this.array2 = new int[8];
        this.array3 = new int[8];
        this.array4 = new int[8];
        for(int i = 0;i < array.length;i++)
        {
            this.array1[i] = array[i];
        }
        mergesort(array,array2, 0, 0, 1, 2);
        mergesort(array2,array3, 0, 1, 3, 4);
        mergesort(array3,array4, 0, 3, 7, 8);

        setTitle("Merge Sort Visualization");
        setSize(1800, 1300);
        //setLocationRelativeTo(null);

        panel = new AnimationPanel();
        add(panel);

        Timer timer = new Timer(500, e -> {
            if(TIME == 0)
            {
                TIME++;
            }
            updateAnimationState();
            panel.repaint();

            if (TIME > 63) {
                ((Timer) e.getSource()).stop();
            }
        });

        JPanel button = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        start = new JButton("Start");
        start.addActionListener(e ->
        {
            start.setEnabled(false);
            new Thread(() ->
            {
                timer.start();
                SwingUtilities.invokeLater(() ->
                {
                    start.setEnabled(true);
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
                this.array[i] = random.nextInt(-20, 20);
            }

            this.array1 = new int[8];
            this.array2 = new int[8];
            this.array3 = new int[8];
            this.array4 = new int[8];
            for(int i = 0;i < array.length;i++)
            {
                this.array1[i] = array[i];
            }
            mergesort(array,array2, 0, 0, 1, 2);
            mergesort(array2,array3, 0, 1, 3, 4);
            mergesort(array3,array4, 0, 3, 7, 8);
            panel.repaint();
        });
        button.add(RandomArray);
        add(button, BorderLayout.SOUTH);
    }

    private void updateAnimationState() {
        if (TIME >= 39 && TIME <= 46) {
            Highlight1 = TIME - 39;
            mergeSort1(array1, array2[Highlight1]);
        }
        else if(TIME >=47 && TIME <=54)
        {
            Highlight3 = TIME - 47;
            mergeSort2(array2, array3[Highlight3]);
        }
        else if(TIME >=55 && TIME <=62)
        {
            Highlight5 = TIME - 55;
            mergeSort3(array3, array4[Highlight5]);
        }
        if (TIME == 7 || TIME == 15 || TIME == 23) {
            TIME++;
            repaint();
            dividePadding = 2 * dividePadding;
        } else if ((TIME == 31 || TIME == 39 || TIME == 47 || TIME == 55)) {
            TIME++;
            repaint();
            conquerPadding = conquerPadding / 2;
        } else if (TIME < 64) {
            TIME++;
            if(TIME == 3 || TIME ==11 || TIME == 19)
            {
                repaint();
            }
            else {
                repaint();
            }
        }


    }

    private class AnimationPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawArray(g);
        }

        private void drawArray(Graphics g) {
            int arr_x = getWidth()/2 - 160;// Starting x-coordinate for the first element
            int temp_x = 0;
            int arr_y = 50; // y-coordinate for the rectangles
            int temp_y = 1;
            int width = 40; // Width of each rectangle
            int height = 30; // Height of each rectangle
            int padding = 40; // Space between rectangles
            int j = 1;
            int p = 0;
            // Draw each element in the array
            for(j = 1; j <= dividePadding; j = j*2) {
                temp_x = (int)(arr_x - ((Math.pow(2,p)-1) * 20));
                p++;
                if(j == 1) {
                    temp_y = arr_y;
                }
                else {
                    temp_y = temp_y + 50 + height;
                }
                for (int i = 0; i < array.length; i++) {
                    if(TIME >=4 && TIME <=7 && j == 1)
                    {
                        if(i < 4) {
                            g.setColor(Color.ORANGE);
                        }
                        else
                        {
                            g.setColor(Color.magenta);
                        }
                    }
                    else if(TIME >=8 && TIME <=11 && (j == 2))
                    {
                        if(i < 4) {
                            g.setColor(Color.ORANGE);
                        }
                        else
                        {
                            g.setColor(Color.magenta);
                        }
                    }
                    else if(TIME >= 12 && TIME <= 15 && j ==2)
                    {
                        if (i < 2 || (i>3 && i<6))
                        {
                            g.setColor(Color.ORANGE);
                        }
                        else
                        {
                            g.setColor(Color.magenta);
                        }
                    }
                    else if(TIME >=16 && TIME <= 19 && j ==4)
                    {
                        if (i < 2 || (i>3 && i<6))
                        {
                            g.setColor(Color.ORANGE);
                        }
                        else {
                            g.setColor(Color.magenta);
                        }
                    }
                    else if(TIME >= 20 && TIME <= 23 && j == 4)
                    {
                        if(i == 0 || i == 2|| i ==4 || i ==6)
                        {
                            g.setColor(Color.ORANGE);
                        }
                        else {
                            g.setColor(Color.magenta);
                        }
                    }
                    else if(TIME >= 24 && TIME <= 31 && j == 8)
                    {
                        if(i == 0 || i == 2|| i ==4 || i ==6)
                        {
                            g.setColor(Color.ORANGE);
                        }
                        else {
                            g.setColor(Color.magenta);
                        }
                    }
                    else if(TIME >=32 && TIME <= 39 && j == 8 && (TIME-32 == i))
                    {
                        g.setColor(Color.green);
                    }
                    else {
                        g.setColor(Color.lightGray);
                    }
                    g.fillRect(temp_x, temp_y, width, height); // Draw the rectangle
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(array1[i]), temp_x + 15, temp_y + 20); // Draw the number
                    temp_x += width; // Update x-coordinate for the next element
                    if((i+1)%(array.length/j) == 0) {
                        temp_x += padding;
                    }
                }
            }
            int wid = getWidth()/2;
            if(TIME >=4)
            {
                g.drawLine(wid,80,wid - 100,130);
                g.drawLine(wid,80,wid + 100,130);
                if (TIME >= 12) {
                    g.drawLine(wid-100, 160, wid - 180, 210);
                    g.drawLine(wid-100, 160, wid - 60, 210);
                    g.drawLine(wid+100, 160, wid + 180, 210);
                    g.drawLine(wid+100, 160, wid + 60, 210);
                    if(TIME >=20)
                    {
                        g.drawLine(wid - 180, 240, wid - 280, 290);
                        g.drawLine(wid - 180, 240, wid - 200, 290);
                        g.drawLine(wid - 60, 240, wid - 120, 290);
                        g.drawLine(wid - 60, 240, wid - 40, 290);
                        g.drawLine(wid + 180, 240, wid + 280, 290);
                        g.drawLine(wid + 180, 240, wid + 200, 290);
                        g.drawLine(wid + 60, 240, wid + 120, 290);
                        g.drawLine(wid + 60, 240, wid + 40, 290);
                        if(TIME >=9*4)
                        {
                            g.drawLine(wid - 280, 400, wid - 180, 450);
                            g.drawLine(wid - 200, 400, wid - 180, 450);
                            g.drawLine(wid - 120, 400, wid - 60, 450);
                            g.drawLine(wid - 40, 400, wid - 60, 450);
                            g.drawLine(wid + 280, 400, wid + 180, 450);
                            g.drawLine(wid + 200, 400, wid + 180, 450);
                            g.drawLine(wid + 120, 400, wid + 60, 450);
                            g.drawLine(wid + 40, 400, wid + 60, 450);
                            if(TIME >= 11*4)
                            {
                                g.drawLine(wid-180, 480, wid - 100, 530);
                                g.drawLine(wid-60, 480, wid - 100, 530);
                                g.drawLine(wid+180, 480, wid + 100, 530);
                                g.drawLine(wid+60, 480, wid + 100, 530);
                                if(TIME >= 56)
                                {
                                    g.drawLine(wid - 100,560,wid,610);
                                    g.drawLine(wid+ 100,560,wid,610);
                                }
                            }
                        }
                    }
                }
            }

            j = dividePadding;
            int ind = 0;
            int print_;
            if( j > conquerPadding)
            {
                p--;
                temp_x = (int)(arr_x - ((Math.pow(2,p)-1) * 20));
                temp_y = temp_y + 50 + height;
                if(TIME >=32 && TIME <=39)
                {
                    print_ = TIME %8;
                }
                else {
                    print_ = 7;
                }
                for (int i = 0; i <= print_; i++) {
                    if(Highlight2 == i)
                    {
                        g.setColor(Color.GREEN);
                        Highlight2 = -1;
                    }
                    else if(print_ == i && TIME >=32 && TIME <=39)
                    {
                        g.setColor(Color.GREEN);
                    }
                    else {
                        g.setColor(Color.lightGray);
                    }

                    g.fillRect(temp_x, temp_y, width, height); // Draw the rectangle
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(array1[i]), temp_x + 15, temp_y + 20);// Draw the number

                    temp_x += width; // Update x-coordinate for the next element
                    if ((i + 1) % (array.length / j) == 0) {
                        temp_x += padding;
                    }
                }
                j = j/2;
            }
            if( j > conquerPadding)
            {
                p--;
                temp_x = (int)(arr_x - ((Math.pow(2,p)-1) * 20));
                temp_y = temp_y + 50 + height;

                if(TIME >=40 && TIME <=47)
                {
                    print_ = TIME %8;
                }
                else
                {
                    print_ = 7;
                }

                for (int i = 0; i <= print_; i++) {
                    if(Highlight1 == i)
                    {
                        g.setColor(Color.GREEN);
                        Highlight1 = -1;
                    }
                    else if(Highlight4 == i)
                    {
                        g.setColor(Color.GREEN);
                        Highlight4 = -1;
                    }
                    else {
                        g.setColor(Color.lightGray);
                    }

                    g.fillRect(temp_x, temp_y, width, height); // Draw the rectangle
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(array2[i]), temp_x + 15, temp_y + 20);// Draw the number

                    temp_x += width; // Update x-coordinate for the next element
                    if ((i + 1) % (array.length / j) == 0) {
                        temp_x += padding;
                    }
                }
                j = j/2;
            }
            if( j > conquerPadding)
            {
                p--;
                temp_x = (int)(arr_x - ((Math.pow(2,p)-1) * 20));
                temp_y = temp_y + 50 + height;

                if(TIME >=48 && TIME <=55)
                {
                    print_ = TIME %8;
                }
                else {
                    print_ = 7;
                }

                for (int i = 0; i <= print_; i++) {
                    if(Highlight3 == i) {
                        g.setColor(Color.GREEN);
                        Highlight3 = -1;
                    }
                    else if(Highlight6 == i)
                    {
                        g.setColor(Color.GREEN);
                        Highlight6 = -1;
                    }
                    else {
                        g.setColor(Color.lightGray);
                    }

                    g.fillRect(temp_x, temp_y, width, height); // Draw the rectangle
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(array3[i]), temp_x + 15, temp_y + 20);// Draw the number

                    temp_x += width;
                    if ((i + 1) % (array.length / j) == 0) {
                        temp_x += padding;
                    }
                }
                j = j/2;
            }
            if( j > conquerPadding)
            {
                p--;
                temp_x = (int)(arr_x - ((Math.pow(2,p)-1) * 20));
                temp_y = temp_y + 50 + height;

                if(TIME >=56 && TIME <=63)
                {
                    print_ = TIME %8;
                }
                else {
                    print_ = 7;
                }

                for (int i = 0; i <= print_; i++) {
                    if(Highlight5 == i)
                    {
                        g.setColor(Color.GREEN);
                        Highlight5 = -1;
                    }
                    else {
                        g.setColor(Color.lightGray);
                    }

                    g.fillRect(temp_x, temp_y, width, height); // Draw the rectangle
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(array4[i]), temp_x + 15, temp_y + 20);// Draw the number

                    temp_x += width; // Update x-coordinate for the next element
                    if ((i + 1) % (array.length / j) == 0) {
                        temp_x += padding;
                    }
                }
            }
        }
    }

    public void mergeSort1(int arr[],int l)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(array1[i] == l)
            {
                Highlight2 = i;
            }
        }
    }
    public void mergeSort2(int[] arr1,int l)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(array2[i] == l)
            {
                Highlight4 = i;
            }
        }
    }
    public void mergeSort3(int[] arr1,int l)
    {
        for (int i = 0; i < array.length; i++) {
            if (array3[i] == l) {
                Highlight6 = i;
                System.out.println(Highlight6);
            }
        }
    }

    void mergesort(int a[], int a1[], int l, int mid, int r, int next)
    {
        if( r < a.length) {
            int i1 = mid - l + 1;
            int i2 = r - mid;

            int[] b = new int[i1];
            int[] c = new int[i2];

            int i = 0, j = 0, k = 0;

            for (i = l; i <= r; i++) {
                if (i <= mid) {
                    b[j] = a[i];
                    j++;
                } else {
                    c[k] = a[i];
                    k++;
                }
            }

            i = 0;
            j = 0;
            k = l;

            // Merging sorted halves
            while (i <= i1 - 1 && j <= i2 - 1) {
                if (b[i] > c[j]) {
                    a1[k] = c[j];
                    k++;
                    j++;
                } else {
                    a1[k] = b[i];
                    i++;
                    k++;
                }
            }

            // Adding remaining elements
            while (j < i2) {
                a1[k] = c[j];
                k++;
                j++;
            }

            while (i < i1) {
                a1[k] = b[i];
                i++;
                k++;
            }
            mergesort(a,a1,l+next,mid + next,r + next,next);
        }
    }

    public static void MERGESORT()
    {
        MergeSort frame = new MergeSort();
        frame.setVisible(true);
    }
}
