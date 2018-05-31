package com.tarena.exception;

/**
 * @author SuShaohua
 * @date 2016/7/27 12:27
 * @description
 */
public class Exception extends Throwable {
    //TODO finalize object的方法，gc回收之前调用，jvm调用 Auto-generated method stub
    //1. 请说出final，finally， finalize分别是什么
    //2. 下面程序输出的结果是？
    //return结束前需结束try—catch-finally语句
    public static void main(String[] args) {
        System.out.println(test("0") + ", "
                + test(null) + ", "
                + test(""));
    }

    public static int test(String str){
        try {
            return '0' - str.charAt(0);
        }catch (NullPointerException e){
            return 1;
        }catch (java.lang.Exception e){
            return 2;
        }finally {
            return 3;
        }
    }
}
