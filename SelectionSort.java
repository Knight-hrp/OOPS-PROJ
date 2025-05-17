public class SelectionSort extends Sorting
{

    public SelectionSort()
    {
        super("SelectionSort Visualizer");
    }

    public static void SELECTIONSORT()
    {
        new SelectionSort();
    }

    @Override
    protected VisualizerPanel CreatePanel(int[] array)
    {
        return new SelectionSortPanel(array);
    }

    public class SelectionSortPanel extends Sorting.VisualizerPanel
    {
        public SelectionSortPanel(int[] array)
        {
            super(array);
        }

        @Override
        public void algorithm(int[] array)
        {
            for(int i = 0; i < array.length - 1; i++)
            {
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++)
                {
                    Index1 = minIndex;
                    Index2 = j;
                    repaint();

                    try
                    {
                        Thread.sleep(500);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                    if(array[j] < array[minIndex])
                    {
                        minIndex = j;
                    }
                }

                if(minIndex != i)
                {
                    int temp = array[minIndex];
                    array[minIndex] = array[i];
                    array[i] = temp;

                    Index1 = i;
                    Index2 = minIndex;
                    repaint();
                    setArray(array);

                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            Index1 = -1;
            Index2 = -1;
            repaint();
        }
    }
}