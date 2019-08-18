package com.itheima.VariableParameter;

public class VariableP {
    public static void main(String[] args) {
        System.out.println(getSum(1,2,3));
        System.out.println(getSum(new int[]{1,2,2}));
        int [] arr= {1,2,3,44,34};
        System.out.println(getSum(arr));
    }

    public static int getSum(int... a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            int i1 = a[i];
            sum += i1;
        }
        return sum;
    }
}