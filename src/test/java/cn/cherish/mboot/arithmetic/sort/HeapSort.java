package cn.cherish.mboot.arithmetic.sort;

/**
 * 堆排序
 * Created by Cherish on 2017/3/11.
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * arr.length);
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println("\r\n++================排序后================ = \r\n");
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }

    //大顶堆
    private static int[] heap;
    private static int root = 0;
    private static int tail;

    public static int[] sort(int[] source) {
        heap = source;
        tail = heap.length - 1;
        //将无序数组调整为堆（大顶堆）
        init();
        //做排序的逻辑如下：
        int rootTemp;
        while(tail >= root){
            //交换root tail
            rootTemp = heap[root];
            heap[root] = heap[tail];
            heap[tail--] = rootTemp;//堆长减一
            trickleDown(root);
        }

        return heap;
    }
    //初始化堆
    private static void init() {
        int parentRight = parent(tail);
        for (int i = parentRight; i >=0 ; i--) {
            trickleDown(i);
        }
    }
    //父节点下标
    private static int parent(int x){
        return (x - 1) / 2;
    }
    //左子节点
    private static int leftSon(int x){
        return 2 * x + 1;
    }
    //右子节点
    private static int rightSon(int x){
        return 2 * x + 2;
    }
    //向下调整
    private static void trickleDown(int x){
        int temp = heap[x];

        int leftIndex;
        int rightIndex;
        int smallerIndex;

        while(true){
            leftIndex = leftSon(x);
            rightIndex = rightSon(x);

            int left = rightIndex <= tail ? heap[leftIndex] : Integer.MIN_VALUE;
            int right = rightIndex <= tail ? heap[rightIndex] : Integer.MIN_VALUE;
            if (left == Integer.MIN_VALUE && right ==Integer.MIN_VALUE)
                break;

            if (left >= right){
                smallerIndex = leftIndex;
            }else {
                smallerIndex = rightIndex;
            }

            if (heap[smallerIndex] > temp) {
                heap[x] = heap[smallerIndex];
                x = smallerIndex;
            }else {
                break;
            }
        }//end while
        heap[x] = temp;
    }
    //向上调整
    private static void trickleUp(int x){
        int temp = heap[x];

        int parentIndex;
        while(true){
            parentIndex = parent(x);
            if (heap[parentIndex] < temp) {
                heap[x] = heap[parentIndex];
                x = parentIndex;
            }else {
                break;
            }
        }//end while
        heap[x] = temp;
    }


}
