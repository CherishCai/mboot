package cn.cherish.mboot.arithmetic; /**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiangjiaze
 * @version Id: FiveStamp.java, v 0.1 2017/2/9 14:13 FancyKong Exp $$
 */
public class FiveStamp {
    private static int count;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int a =12; //长度
        for (int i = 1; i < a-3; i++) {
            for (int j = i+1; j < a-2 ; j++) {
                for (int k = j+1; k < a-1 ; k++) {
                    for (int l = k+1; l < a ; l++) {
                        for (int m = l+1; m < a+1; m++) {
                            if(isOK(new int[]{i,j,k,l,m})){
//                                System.out.println(""+(i)+" "+(j)+" "+(k)+" "+(l)+" "+(m));
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

    private static boolean isOK(int[] ints) {
        short[] s = new short[13];
        for (int anInt : ints) {
            value_quick(s,anInt);
        }
        int times = 0;
        for (int anInt : ints) {
            if (s[anInt] == 0) return false;
            if (s[anInt] == 4) return true;
            if (s[anInt] > 1) times++;
        }
        return times > 1;
    }

    /**
     * s相当于12个邮票,每走到组合的一个格子,就把相连通的格子自加
     * 这样执行下去,如果有格子是0,那么这个格子肯定是不被其他格子连通的,即 1-4 和4-1问题
     * 如果只有一个格子的值是2, 那么意味着存在2个不连通,即2-3 和 3-2 问题
     * @param s 格子被自加次数
     * @param i 当前走到哪个格子
     */
    private static void value_Smart(short[] s, int i) {
        if (i != 1 && i != 5 && i != 9)
            try {
                s[i - 1]++;
            } catch (Exception ignored) {
            }
        if (i != 4 && i != 8 && i != 12)
            try {
                s[i + 1]++;
            } catch (Exception ignored) {
            }
        try {
            s[i - 4]++;
        } catch (Exception ignored) {
        }
        try {
            s[i + 4]++;
        } catch (Exception ignored) {
        }
    }

    private static void value_quick(short[] s, int i) {
        switch (i){
            case 1:
                s[2] ++;
                s[5] ++;
                break;
            case 2:
                s[1] ++;
                s[6] ++;
                s[3] ++;
                break;
            case 3:
                s[2] ++;
                s[4] ++;
                s[7] ++;
                break;
            case 4:
                s[3] ++;
                s[8] ++;
                break;
            case 5:
                s[1] ++;
                s[6] ++;
                s[9] ++;
                break;
            case 6:
                s[2] ++;
                s[5] ++;
                s[7] ++;
                s[10] ++;
                break;
            case 7:
                s[3] ++;
                s[6] ++;
                s[8] ++;
                s[11] ++;
                break;
            case 8:
                s[4] ++;
                s[7] ++;
                s[12] ++;
                break;
            case 9:
                s[5] ++;
                s[10] ++;
                break;
            case 10:
                s[6] ++;
                s[9] ++;
                s[11] ++;
                break;
            case 11:
                s[7] ++;
                s[10] ++;
                s[12] ++;
                break;
            case 12:
                s[8] ++;
                s[11] ++;
                break;

        }
    }

    private static boolean isOK_Silly(int[] ints) {
        if(ints[0] == 0 && ints[1] == 1&& ints[2] == 2 && ints[3] == 3 && ints[4] == 5)
        {
            System.out.println();
        }
        short flag = 0;
        List<Short> flags = new ArrayList<>();
        for(int i : ints){
            switch (i){
                case 0:
                    for (int anInt : ints) {
                        if(anInt == 1 || anInt == 4){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 1:
                    for (int anInt : ints) {
                        if(anInt == 0 || anInt == 2|| anInt == 5){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 2:
                    for (int anInt : ints) {
                        if(anInt == 1 || anInt == 6|| anInt == 3){
                            flag++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 3:
                    for (int anInt : ints) {
                        if(anInt == 2 || anInt == 7){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 4:
                    for (int anInt : ints) {
                        if(anInt == 0 || anInt == 5|| anInt == 8){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 5:
                    for (int anInt : ints) {
                        if(anInt == 1 || anInt == 4|| anInt ==6|| anInt == 9){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 6:
                    for (int anInt : ints) {
                        if(anInt == 2 || anInt == 5|| anInt ==7|| anInt == 10){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 7:
                    for (int anInt : ints) {
                        if(anInt == 3 || anInt == 6|| anInt == 11){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 8:
                    for (int anInt : ints) {
                        if(anInt == 9 || anInt == 4){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 9:
                    for (int anInt : ints) {
                        if(anInt == 5 || anInt == 8|| anInt == 10){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 10:
                    for (int anInt : ints) {
                        if(anInt == 6 || anInt == 9|| anInt == 11){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
                case 11:
                    for (int anInt : ints) {
                        if(anInt == 7 || anInt == 10){
                            flag ++;
                        }
                    }
                    if(flag == 0) return false;
                    flags.add(flag);
                    flag = 0;
                    break;
            }
        }
        int times = 0;
        for(short mflag :flags){
            if(mflag > 1){
                times ++;
            }
            if(mflag == 4) return true;
            if(times >= 2) return true;
        }
        return false;
    }
}
