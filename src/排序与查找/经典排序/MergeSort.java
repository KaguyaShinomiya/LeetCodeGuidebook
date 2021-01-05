package 排序与查找.经典排序;

public class MergeSort {
    public static void mergeSort(int[] arr, int[] tmpArray, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, tmpArray, left, mid);
            mergeSort(arr, tmpArray, mid + 1, right);
            merge(arr, tmpArray, left, mid + 1, right);
        } else {
            tmpArray[left] = arr[left];
        }

    }

    public static void merge(int[] arr, int[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (arr[leftPos] <= arr[rightPos]) {
                tmpArray[tempPos++] = arr[leftPos++];
            } else {
                tmpArray[tempPos++] = arr[rightPos++];
            }

            while (leftPos < leftEnd) {
                tmpArray[tempPos++] = arr[leftPos++];
            }
            while (rightPos < rightEnd) {
                tmpArray[tempPos++] = arr[rightPos++];
            }
        }
    }
}
