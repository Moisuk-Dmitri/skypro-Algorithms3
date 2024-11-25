import exception.IncorrectIndexGivenException;
import exception.EmptyParameterGivenException;
import exception.IncorrectParameterGivenException;

import java.util.Objects;

public class IntegerListImpl implements IntegerList {

    private Integer[] integerArray;
    private int size;

    public IntegerListImpl() {
        integerArray = new Integer[5];
        size = 0;
    }

    @Override
    public Integer add(Integer item) {
        if (size < integerArray.length) {
            for (int i = size; i >= 1; i--) {
                integerArray[i] = integerArray[i - 1];
            }

            integerArray[0] = item;
            size++;
        } else {
            grow();

            for (int i = size; i >= 1; i--) {
                integerArray[i] = integerArray[i - 1];
            }

            integerArray[0] = item;
            size++;
        }

        return integerArray[0];
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index > size || index < 0) {
            throw new IncorrectIndexGivenException("Incorrect index given");
        }

        if (size < integerArray.length) {
            for (int i = size; i > index; i--) {
                integerArray[i] = integerArray[i - 1];
            }

            integerArray[index] = item;
            size++;
        } else {
            grow();

            for (int i = size; i > index; i--) {
                integerArray[i] = integerArray[i - 1];
            }

            integerArray[index] = item;
            size++;
        }

        return integerArray[index];
    }

    @Override
    public Integer set(int index, Integer item) {
        if (index >= size || index < 0) {
            throw new IncorrectIndexGivenException("Incorrect index given");
        }

        integerArray[index] = item;

        return integerArray[index];
    }

    @Override
    public Integer remove(Integer item) {
        if (!contains(item)) {
            throw new IncorrectParameterGivenException("No such parameter in list");
        }

        Integer str = integerArray[indexOf(item)];
        remove(indexOf(item));

        return str;
    }

    @Override
    public Integer remove(int index) {
        if (index >= size || index < 0) {
            throw new IncorrectIndexGivenException("Incorrect index given");
        }

        Integer str = integerArray[index];
        while (integerArray[index + 1] != null) {
            integerArray[index] = integerArray[index + 1];
        }
        integerArray[index + 1] = null;

        return str;
    }

    @Override
    public boolean contains(Integer element) {
        quickSort(integerArray, 0, size - 1);

        int min = 0;
        int max = size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (Objects.equals(element, integerArray[mid])) {
                return true;
            }

            if (element < integerArray[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        int i = 0;
        while (!item.equals(integerArray[i]) && i < size) {
            i++;
        }

        if (i == size) {
            return -1;
        }

        return i;
    }

    @Override
    public int lastIndexOf(Integer item) {
        int i = size;
        while (i >= 0 && !item.equals(integerArray[i])) {
            i--;
        }

        return i;
    }

    @Override
    public Integer get(int index) {
        if (index >= size || index < 0) {
            throw new IncorrectIndexGivenException("Incorrect index given");
        }

        return integerArray[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        for (int i = 0; i < size; i++) {
            if (!otherList.get(i).equals(integerArray[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            integerArray[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] otherIntegerArray = new Integer[size];

        System.arraycopy(integerArray, 0, otherIntegerArray, 0, size);

        return otherIntegerArray;
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
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

    private void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    private void grow() {
        Integer[] otherIntegerArray = new Integer[size];
        System.arraycopy(integerArray, 0, otherIntegerArray, 0, size);

        integerArray = new Integer[(int) (size * 1.5)];
        System.arraycopy(otherIntegerArray, 0, integerArray, 0, size);
    }
}

