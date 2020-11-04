package 排序.经典排序;

public class MergeSort {
    public static void mergeSort(int[] a, int[] tmpArray, int left, int right) {
        int center;
        //int TR2[];

        if (left < right) {
            center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        } else {
            tmpArray[left] = a[left];
        }

    }

    public static void merge(int[] a, int[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos] <= a[rightPos]) {
                tmpArray[tempPos++] = a[leftPos++];
            } else {
                tmpArray[tempPos++] = a[rightPos++];
            }

            while (leftPos < leftEnd) {
                tmpArray[tempPos++] = a[leftPos++];
            }
            while (rightPos < rightEnd) {
                tmpArray[tempPos++] = a[rightPos++];
            }
        }
    }
}
