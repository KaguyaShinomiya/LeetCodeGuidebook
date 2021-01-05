package 排序与查找.经典排序;

/**
 * The Class: HeapSort
 *
 * @author: Kaguya yu
 * @since: 2021-01-05 01:53
 */
public class HeapSort {
    public void heapSort(int[] arr) {
        // 构造大顶堆
        for (int i = arr.length / 2; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 每轮都将数组开头的元素与当前的末尾元素进行交换
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            // 注意在重新调整大顶堆时，没有包含当前的末尾元素
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 将i处的元素下沉到合适的位置，构造大顶堆
     *
     */
    private void adjustHeap(int[] arr, int i, int heapSize) {
        int temp = arr[i];
        for (int k = 2 * i; k < heapSize; k = 2 * k + 1) {
            // 找出i的两个子节点k、k + 1中最大的元素
            if (k + 1 < heapSize && arr[k] < arr[k + 1]) {
                k++;
            }
            // 如果两个子节点中最大的元素比父节点的元素要大，就把父节点i的位置与子节点交换
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
