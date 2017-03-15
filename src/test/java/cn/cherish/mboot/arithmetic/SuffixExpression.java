package cn.cherish.mboot.arithmetic;

import cn.cherish.mboot.datastructure.Stack;
import cn.cherish.mboot.datastructure.exception.DataStructureException;

/**
 * 中缀转后缀表达式
 * Created by Cherish on 2017/3/9.
 */
public class SuffixExpression {

    public static void main(String[] args) throws DataStructureException {
        //TODO 目前仅支持 0-9 的数值 和  +-*/() 操作符
//        String infixExpression = "2-3+4*5-6/(7+8*9)";
        String infixExpression = "3*(1+2)";
        char[] infix = infixExpression.toCharArray();
        //后缀表达式结果
        String suffixExpression = resolve(infix);
        System.out.println("infixExpression = " + infixExpression);
        System.out.println("suffixExpression = " + suffixExpression);
    }

    private static String resolve(char[] infix) throws DataStructureException {
        StringBuilder sb = new StringBuilder(infix.length);
        //操作符存放栈
        Stack<Character> opStack = new Stack<>(infix.length);

        for (int i = 0; i < infix.length; i++) {
            char c = infix[i];
            if (isZeroToNine(c)){//数符
                sb.append(c);
            }else if (isPlusOrMinus(c)){
                //查看操作符栈中的符号，非括号话，把操作栈符pop出加入后缀表达式
                if (!opStack.isEmpty()){
                    Character peek = opStack.peek();
                    if (!isBracket(peek)){
                        sb.append(opStack.pop());
                    }
                }
                //把本次加减符加入操作符栈
                opStack.push(c);
            }else if (isMultiplyOrDivide(c)){
                //查看操作符栈中的符号，是乘除，把操作栈符pop出加入后缀表达式
                if (!opStack.isEmpty()){
                    Character peek = opStack.peek();
                    if (isMultiplyOrDivide(peek)){
                        sb.append(opStack.pop());
                    }
                }
                //把本次乘除符加入操作符栈
                opStack.push(c);
            }else if (isBracket(c)){
                if ('(' == c){//左括号
                    opStack.push(c);//直接加入操作符栈
                }else {//右括号
                    Character pop = opStack.pop();
                    while ('(' != pop){
                        sb.append(pop);
                        pop = opStack.pop();
                    }
                }
            }else {
                //GG
            }
        }

        //检查操作符栈，把剩余的pop到后缀表达式中
        while(!opStack.isEmpty()){
            sb.append(opStack.pop());
        }

        return sb.toString();
    }

    private static boolean isZeroToNine(char c){
        return '0' <= c && c <= '9';
    }

    private static boolean isPlusOrMinus(char c){
        return '+' == c || '-' == c;
    }

    private static boolean isMultiplyOrDivide(char c){
        return '*' == c || '/' == c;
    }

    private static boolean isBracket(char c){
        return '(' == c || ')' == c;
    }


}
