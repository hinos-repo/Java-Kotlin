public class SortExample
{
    public static void main(String[] args)
    {
        String strQuestion = "85624";

        System.out.println("------ 삽입 정렬 ------");
        System.out.println("결과 : " + insertionSort(strQuestion));

        System.out.println("------ 선택 정렬 ------");
        System.out.println("결과 : " + selectionSort(strQuestion));

        System.out.println("------ 버블 정렬 ------");
        System.out.println("결과 : " + bubbleSort(strQuestion));
    }

    private static String insertionSort(String question)
    {
        char [] arrCh = question.toCharArray();

        char select;
        for(int i = 1; i < arrCh.length; i++)
        {
            System.out.print(i+"회차 : ");
            System.out.println(arrCh);
            select = arrCh[i];
            int j = i;
            while (j > 0 && select < arrCh[j-1])
            {
                arrCh[j] = arrCh[j-1]; // <<왼쪽으로 밀기
                j--;
            }
            arrCh[j] = select; // 값 체인지
        }
        return new String(arrCh);
    }

    private static String selectionSort(String question)
    {
        char [] arrCh = question.toCharArray();
        char select;
        char min;
        int minPos;
        for (int i = 0; i<arrCh.length; i++)
        {
            System.out.print((i+1)+"회차 : ");
            System.out.println(arrCh);

            min = 0;
            minPos = -1;
            select = arrCh[i];
            for (int j = i; j < arrCh.length; j++)
            {
                if (j == i)
                {
                    min = arrCh[j];
                    minPos = j;
                } else {
                    if (min > arrCh[j])
                    {
                        min = arrCh[j];
                        minPos = j;
                    }
                }
            }
            if (select > min)
            {
                arrCh[minPos] = select;
                arrCh[i] = min;
            }
        }

        return new String(arrCh);
    }

    private static String bubbleSort(String question)
    {
        char [] arrCh = question.toCharArray();
        int count = 1;
        int i = arrCh.length-1;
        while(i > 0)
        {
            System.out.print(count+"회차 : ");
            System.out.println(arrCh);
            for (int j = 0; j < i; j++)
            {
                char select = arrCh[j];
                if (select > arrCh[j+1])
                {
                    arrCh[j] = arrCh[j+1];
                    arrCh[j+1] = select;
                }
            }
            i--;
            count++;
        }

        return new String(arrCh);
    }
}
