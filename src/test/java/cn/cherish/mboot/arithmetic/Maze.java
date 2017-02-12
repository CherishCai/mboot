package cn.cherish.mboot.arithmetic;

import java.util.LinkedList;

/**
 * Created by Cherish on 2017/2/9.
 */
public class Maze {

    private static int count;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int a =12; //长度
        for (int i = 0; i < a-4; i++) {
            for (int j = i+1; j < a-3 ; j++) {
                for (int k = j+1; k < a-2 ; k++) {
                    for (int l = k+1; l < a-1 ; l++) {
                        for (int m = l+1; m < a; m++) {
                            if(isOK(new int[]{i,j,k,l,m})){
//                                System.out.println(""+(i+1)+" "+(j+1)+" "+(k+1)+" "+(l+1)+" "+(m+1));
                                count++;
                            }
                        }
                    }
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(count);
        System.out.println("耗时 = " + (end-start));
    }

    private static final int ROW = 3;
    private static final int COL = 4;
    private static final int LEN = 5;

    private static boolean isOK(int[] ints) {
        int[][] graph = new int[ROW][COL];
        //初始化
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                graph[i][j] = -1;//-1表示墙，不通
            }
        }
        for (int i = 0; i < ints.length; i++) {
            int r = ints[i] / COL;
            int c = ints[i] % COL;
            graph[r][c] = 1;// 1 表示该点为通路点，长度为 1
        }

        //广度优先的遍历
        LinkedList<Integer> link = new LinkedList<>();
        //从最小点开始
        link.add(ints[0]);
        //以下开始计算相连的长度
        int len = 0;

        //使用广度优先
        while( !link.isEmpty() ){
            Integer pop = link.pop();

            //计算上下左右的点
            int right = pop + 1;
            if ( right % COL == 0 ) //本点已经是最右点，加一已经走到一下行的开始了（列的整数倍）
            {

            }else {//有右边点，判断是否通
                checkAndAdd(graph, link, right);
            }
            //判断下部是否通路
            int bottom = pop + COL;
            if (bottom >= ROW * COL){//本点已经是最底部

            }else {
                checkAndAdd(graph, link, bottom);
            }

            //判断左边
            int left = pop - 1;
            if ( pop % COL == 0){//本点已经是最左点

            }else {
                checkAndAdd(graph, link, left);
            }
            //顶部的点
            int top = pop - COL;
            if ( top < 0 ){//本点是第一行的点

            }else {
                checkAndAdd(graph, link, top);
            }

            int rPop = pop / COL;
            int cPop = pop % COL;
            len += graph[rPop][cPop];//加上长度
            graph[rPop][cPop] = 0;//把走过的点赋值为零

        }

        if ( len >= LEN )
            return true;

        return false;
    }

    private static void checkAndAdd(int[][] graph, LinkedList<Integer> link, int val) {
        int r = val / COL;
        int c = val % COL;
        if (graph[r][c] > 0){
            link.add(val);//大于零则能通，加入队列
        }
    }


}
