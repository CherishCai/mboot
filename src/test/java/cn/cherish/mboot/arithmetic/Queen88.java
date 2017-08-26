package cn.cherish.mboot.arithmetic;

/**
 * 看书实现
 * Created by Cherish on 2017/3/9.
 */
public class Queen88 {
    private static int count;

    public static void main(String[] args) {
        queue(8);
        System.out.println("不排除对称，不考虑独立，共有解法：" + count);
    }

    private static void queue(int maxCol) {
        //保存该行下，哪一列可以放皇后
        // column_num[2]=3  表示第二行第三个位置可以
        int[] column_num = new int[maxCol + 1];
        int row = 1;//第几行，去掉0行
        //初始化数组
        for (int i = 0; i <= maxCol; i++) {
            column_num[i] = 0;
        }

        while(row > 0){
            column_num[row]++;// 表示row行下的列数加一
            //找到该行下 可行列的位置
            while (column_num[row] <= maxCol && !isPlace(column_num, row, column_num[row])) {
                column_num[row]++;
            }
            //达到八行，判断是否输出
            if (column_num[row] <= maxCol){
                if (row == maxCol){
                    //足够八行，可以输出
                    ++count;
                    printQueue(column_num);
                }else {
                    //未足八行, 从下一行行首开始
                    column_num[++row] = 0;
                }
            }else {
                //目前一行的所有摆放都不合适， 返回上一行 选取下一列
                row--;
            }
        }//end while
    }//end queue

    private static void printQueue(int[] column_num) {
        System.out.println("count = " + count);
        for (int i = 1; i < column_num.length; i++) {
            for (int j = 1; j < column_num[i]; j++) {
                System.out.print("0 ");
            }
            System.out.print("q ");
            for (int j = column_num[i] + 1; j < column_num.length; j++) {
                System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println("=================================");
    }

    //判断该位置是否可以放
    private static boolean isPlace(int[] column_num, int row, int col) {
        int col_dist = 0;
        int row_dist = 0;
        for (int i = 1; i < row; i++) {
            col_dist = Math.abs(col - column_num[i]);
            row_dist = row - i;
            //同一列 或 斜线上
            if (0 == col_dist || (col_dist == row_dist)) {
                return false;
            }
        }
        return true;
    }


}
