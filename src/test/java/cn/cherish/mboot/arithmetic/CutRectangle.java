package cn.cherish.mboot.arithmetic;

import java.util.*;

/**
 * Created by Cherish on 2017/2/11.
 */
public class CutRectangle {

    public static void main(String[] args) {

        Node note1 = new Node(1, 2);
        Node note2 = new Node(1, 3);
        Node note3 = new Node(2, 2);
        Node note4 = new Node(3, 1);

        List<Node> list = new ArrayList();
        list.add(note1);
        list.add(note2);
        list.add(note3);
//        list.add(note4);
        Collections.sort(list);

        System.out.println("list = " + list);

        int size = 100_001;
        int currentSize = 0;
        //建立一个维护 x 坐标的列表
        int[] xList = new int[size];//抛弃
        //存放不断加入的 y 以确定有多少 y 重合
        Set ySet = new HashSet();
        int repeatY = 0;

        int sum = 1;//默认有一块
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Node nodeI = list.get(i);
            int leftSum = insertAndGet(xList, currentSize, nodeI.x);//比当前x小的个数
            currentSize++;//当前数组实际容量加一

            if (ySet.contains(nodeI.y)){
                ++repeatY;
            }else {
                ySet.add(nodeI.y);
            }
            sum += leftSum + 1 - repeatY;//减去当前y有重合的数量!!!!!!!!但是  这个y 与上一个 y 相等时候 又不能 这样减了
        }

        System.out.println("sum = " + sum);

    }

    //插入 x 并返回 比 x 小的数有多少个
    public static int insertAndGet(int[] source, int currentSize, int x){
        if (currentSize == 0) {
            source[0] = x;
            return 0;
        }

        int index = findIndex(source, currentSize, x);
        //index 右边的全部右移一位
        for (int i = currentSize; i > index; i--) {
            source[i] = source[i-1];
        }
        // x 插进去
        source[index] = x;
        //返回数量 因为index 从零开始，所以当前小于 x 的数有 index 个
        return index;
    }

    /**
     * 找到对应的下标
     */
    public static int findIndex(int[] source, int currentSize, int x) {
        int left = 0,right = currentSize - 1;
        int pivot = 0;
        while (true) {
            pivot = (left + right)>>1;
            if (source[pivot] == x) {
                break;
            }
            if (left >= right){//这种情况是没有与相等的元素在数组中，判断当前位置的与val的比较
                if (source[pivot] < x) {
                    pivot = pivot + 1;
                }
                break;
            }

            if (source[pivot] < x) {
                left = pivot + 1;
            }else {
                right = pivot - 1;
            }
        }
        
        //找到重复值中最左的下标
        if (pivot > 0) {
            while (source[pivot] == source[pivot-1]){
                --pivot;
            }
        }
        return pivot;
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if ( o.y > this.y) return 1;
            if ( o.y == this.y ){
                if ( o.x > this.x){
                    return 1;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            return "Note{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
