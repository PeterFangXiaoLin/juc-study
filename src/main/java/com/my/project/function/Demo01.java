package com.my.project.function;

import java.util.function.Function;

/**
 * Function 函数型接口，有一个输入参数，有一个返回值
 * 只要是 函数型接口，就可以用lambda表达式简化
 */
public class Demo01 {
    public static void main(String[] args) {
//        Function<String, String> function = new Function<String, String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };

        Function<String, String> function = (str) -> str;

        System.out.println(function.apply("abc"));
    }
}
