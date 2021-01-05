package 排序与查找.经典排序;

public class QuickSort {
    public void quickSort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    /**
     * 每轮切分，使得左边的值都小于枢纽值，右边的值都大于枢纽值
     * 返回枢纽值的位置
     *
     */
    public int partition(int[] arr, int left, int right) {
        // 注意这步操作之后，枢纽值的位置一直位于arr[left]
        int pivot = median3(arr, left, right);
        int i = left + 1;
        int j = right;
        while (true) {
            // 找到左边的比pivot大的元素
            while (i < right && arr[i] <= pivot) {
                i++;
            }
            // 找到右边的比pivot小的元素
            while (j >= left && arr[j] >= pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            // 交换两个元素的位置
            swap(arr, i, j);
        }
        // 在最后一步，把枢纽元和i的元素进行交换
        swap(arr, left, i);
        return i;
    }

    /**
     * 三数取中之后将枢纽值pivot交换到数组最左边
     *
     */
    private int median3(int[] arr, int left, int right) {
        int center = (left + right) / 2;
        if (arr[left] > arr[center]) {
            swap(arr, left, center);
        }
        if (arr[right] < arr[left]) {
            swap(arr, left, right);
        }
        if (arr[right] < arr[center]) {
            swap(arr, left, center);
        }
        // 把枢纽值放到数组最左边
        swap(arr, center, left);
        return arr[left];
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private void insertionSort(int[] arr, int left, int right) {
        int j;
        for (int p = left; p <= right; p++) {
            int temp = arr[p];
            for (j = p; j > 0 && arr[j - 1] < temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

}
