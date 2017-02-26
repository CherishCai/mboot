package cn.cherish.mboot.arithmetic;

/**
 * Created by Cherish on 2017/2/9.
 */
public class Group3By9 {

    private static volatile int count = 0;
    private static final char[] P8 = { 'B', 'C',
                                          'D', 'E', 'F',
                                          'G', 'H', 'I' };
    private static final char A = 'A';

    private static int MAX_COL_ROW = 8;//最大行列
    private static int[] cols = new int[MAX_COL_ROW]; //定义cols数组，记录每一组的人员

    public static void main(String[] args) {
        fun(0);
        System.out.println("总组合数 = " + count);
    }

    private static void fun(int col){
        if (col > MAX_COL_ROW || col < 0 ) return;

        //遍历该列所有被选过的人，并用rows数组记录，被选rows[i]=true
        boolean[] rows = new boolean[MAX_COL_ROW];
        for (int i = 0; i < col; i++) {
            rows[cols[i]] = true;
        }
        for (int i = 0; i < MAX_COL_ROW; i++) {
            //判断该行的人员是否已被选
            if (!rows[i]) continue;
            //设置当前列所选所在行数
            cols[col] = i;
            //累计方案个数
            count++;
            //打印组合信息
            printChessBoard();
            //继续下一列
            fun(++col);
        }
    }

    private static void printChessBoard(){

        System.out.println("第"+count+"种组合");

        for (int i = 0; i < MAX_COL_ROW; i++) {
            for (int j = 0; j < MAX_COL_ROW; j++) {
                if (i == cols[j]) {
                    System.out.print("0 ");
                } else
                    System.out.print("+ ");
            }
            System.out.print("\n");
        }

    }

}
