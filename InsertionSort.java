public class InsertionSort extends Sorting
{
    public InsertionSort()
    {
        super("InsertionSort Visualizer");
    }

    public static void INSERTIONSORT()
    {
        new InsertionSort();
    }

    @Override
    protected VisualizerPanel CreatePanel(int[] array)
    {
        return new InsertionSortPanel(array);
    }

    public class InsertionSortPanel extends Sorting.VisualizerPanel
    {
        public InsertionSortPanel(int[] array)
        {
            super(array);
        }

        @Override
        public void algorithm(int[] array)
        {
            int temp, j=0;
            for(int i=1 ; i<array.length ; i++)
            {
                temp = array[i];
                for(j=i-1 ; j>=0 && array[j] > temp ; j--)
                {
                    array[j+1] = array[j];
                    Index1 = j;
                    Index2 = j+1;
                    panel.repaint();

                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    panel.repaint();
                }
                array[j+1] = temp;

                Index1 = -1;
                Index2 = -1;
                panel.repaint();
            }
        }
    }
}