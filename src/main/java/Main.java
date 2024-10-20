import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        IntegerList integerList = new IntegerListImpl();
        final Random RANDOM = new Random();

        for (int i = 0; i < 100000; i++) {
            integerList.add(RANDOM.nextInt());
        }

        Integer[] integerArray1 = new Integer[integerList.size()];
        System.arraycopy(integerList.toArray(), 0, integerArray1, 0, integerList.size());

        Integer[] integerArray2 = new Integer[integerList.size()];
        System.arraycopy(integerList.toArray(), 0, integerArray2, 0, integerList.size());

        Integer[] integerArray3 = new Integer[integerList.size()];
        System.arraycopy(integerList.toArray(), 0, integerArray3, 0, integerList.size());

        Integer[] integerArray4 = new Integer[integerList.size()];
        System.arraycopy(integerList.toArray(), 0, integerArray4, 0, integerList.size());

        long start = System.currentTimeMillis();
        sortBubble(integerList.toArray());
        System.out.println("Bubble sort: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sortSelection(integerArray1);
        System.out.println("Selection sort: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sortInsertion(integerArray2);
        System.out.println("Insertion sort: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        quickSort(integerArray3, 0, integerArray3.length - 1);
        System.out.println("Quick sort: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        mergeSort(integerArray4);
        System.out.println("Merge sort: " + (System.currentTimeMillis() - start));
    }

    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void sortBubble(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    public static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    public static void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
}
