package cn.cherish.mboot.arithmetic;

/**
 * Created by Cherish on 2017/3/4.
 */
public class Chessboard {
    private static int step = 2;
    public static void main(String[] args) {
        int dimen = 8;//棋盘的维度
        int stainX = 2;//特殊点X
        int stainY = 6;//特殊点Y
        int[][] chessboard = new int[dimen + 1][dimen + 1];
        //初始化棋盘
        for (int i = 0; i <= dimen; i++) {
            for (int j = 0; j <= dimen; j++) {
                chessboard[i][j] = 0;
            }
        }
        //加入瑕点
        chessboard[stainY][stainX] = 1;
        printChessboard(chessboard);
        System.out.println("+++++++++++++++++++++++++++++");

        int x1 = 1;
        int y1 = 1;
        int x2 = chessboard.length -1;
        int y2 = chessboard[chessboard.length - 1].length - 1;

        fun(chessboard, x1, y1, x2, y2);
        chessboard[stainY][stainX] = 1;
        System.out.println("+++++++++++++++++++++++++++++");
        printChessboard(chessboard);
    }

    private static void fun(int[][] chessboard, int x1, int y1, int x2, int y2) {
        System.out.print("x1 = " + x1 + ", y1 = " + y1 + "  ");
        System.out.print("x2 = " + x2 + ", y2 = " + y2 + "  ");
        //结束条件,只有4个格子的情况
        if (x1 == x2 -1){
            System.out.println("+++++++只有4个格子的情况，其中有一个瑕点，很容易涂色++++++++");
            /*for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (chessboard[j][i] == 0){
                        chessboard[j][i] = step;
                    }
                }
            }
            ++step;*/
            return;
        }

        //两个中心点
        int x = (x1 + x2) >> 1;//除二操作、
        int y = (y1 + y2) >> 1;
        System.out.print("x = " + x + ", y = " + y + "  ");

        int stainX = 0;
        int stainY = 0;
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (chessboard[j][i] != 0){
                    stainX = i;
                    stainY = j;
                    break;
                }
            }
        }
        System.out.print("stainX = " + stainX + ", stainY = " + stainY + "  ");
        boolean left = true;
        boolean top = true;
        //判断瑕点在中心点的哪个方位
        if (stainX <= x) {//左边
            left = true;
        } else {//右边
            left = false;
        }
        if (stainY <= y){//上
            top = true;
        }else {//下
            top = false;
        }
        System.out.print("左边 = " + left);
        System.out.println(" 上边 = " + top);

        //涂色操作
        fill(chessboard, x, y, left, top);

        fun(chessboard, x1, y1, x, y);//左上块
        fun(chessboard, x + 1, y1, x2, y);//右上块
        fun(chessboard, x1, y + 1, x, y2);//左下块
        fun(chessboard, x + 1, y + 1, x2, y2);//右下块

    }

    private static void fill(int[][] chessboard, int x, int y, boolean left, boolean top) {
        chessboard[y][x] = step;
        chessboard[y][x + 1] = step;
        chessboard[y + 1][x] = step;
        chessboard[y + 1][x + 1] = step;
        ++step;
        if (left && top){
            chessboard[y][x] = 0;
        }else if (left && !top){
            chessboard[y+1][x] = 0;
        }else if (!left && top){
            chessboard[y][x +1] = 0;
        }else if (!left && !top){
            chessboard[y +1][x +1] = 0;
        }
        System.out.println(" ================ ");
        printChessboard(chessboard);
        System.out.println(" ================ ");

    }

    public static void printChessboard(int[][] chessboard){
        for (int i = 1; i < chessboard.length; i++) {
            for (int j = 1; j < chessboard.length; j++) {
                System.out.print(chessboard[i][j] +" ");
                if (j == chessboard.length - 1) {
                    System.out.println();
                }
            }
        }
    }

}
