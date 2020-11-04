package 排序.经典排序;

public class QuickSort {
    public static void quickSort(int[] a, int left, int right) {
        int CUTOFF = 5;
        int pivot;
        if (right - left > CUTOFF) {
            while (left < right) {
                pivot = partition(a, left, right);
                quickSort(a, left, pivot - 1);
                left = pivot + 1;
            }

        }
        else {
           insertionSort(a, left, right);
        }
    }

    public static int partition(int[] a, int left, int right) {
        int pivot = median3(a, left, right);
        int temp = a[left];
        while (left < right) {
            while (left < right && a[right] >= pivot) {
                right--;
            }
            a[left] = a[right];
            while (left < right && a[left] <= pivot) {
                left++;
            }
            a[right] = a[left];

        }
        a[left] = temp;
        return left;
    }

    public static void swap(int[] a, int left, int right) {
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    public static int median3(int[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[left] > a[center]) {
            swap(a, left, center);
        }
        if (a[right] < a[left]) {
            swap(a, left, right);
        }
        if (a[right] < a[center]) {
            swap(a, left, center);
        }

        swap(a, center, left);
        return a[left];
    }

    public static void insertionSort(int[] a, int left, int right) {
        int j;
        for (int p = left; p <= right; p++) {
            int temp = a[p];
            for (j = p; j > 0 && a[j - 1] < temp; j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

}
