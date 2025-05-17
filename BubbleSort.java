public class BubbleSort extends Sorting
{
    public BubbleSort()
    {
        super("BubbleSort Visualizer");
    }

    public static void BUBBLESORT()
    {
        new BubbleSort();
    }

    @Override
    protected VisualizerPanel CreatePanel(int[] array)
    {
        return new BubbleSortPanel(array);
    }

    class BubbleSortPanel extends Sorting.VisualizerPanel
    {
        public BubbleSortPanel(int[] array)
        {
            super(array);
        }

        @Override
        public void algorithm(int[] array)
        {
            boolean swap;
            for(int i=0; i<array.length-1; i++)
            {
                swap = false;
                for (int j=0; j<array.length-i-1; j++)
                {
                    Index1 = j;
                    Index2 = j + 1;

                    repaint();
                    try
                    {
                        Thread.sleep(500);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    if(array[j] > array[j+1])
                    {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                        swap = true;

                        setArray(array);
                        repaint();
                    }
                }
                if(!swap)
                {
                    break;
                }
            }

            Index1 = -1;
            Index2 = -1;
            repaint();
        }
    }
}
