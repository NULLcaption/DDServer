package com;

/**
 * Created by Administrator on 2019/12/4.
 */

public class testDemo {
    public static void main(String[] args) {
        String s = "1000-0123456";
        String result = s.substring(s.length()-7,s.length());
        System.out.println(result);    //输出结果为345
    }
}
