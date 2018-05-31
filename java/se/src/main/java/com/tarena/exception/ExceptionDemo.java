package com.tarena.exception;


import com.tarena.model.Person;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/17
 * @description
 */
public class ExceptionDemo {
    //RuntimeException do not need Throws
    //TODO 自定义异常
    public static void main(String[] args) throws Exception{
        Person person = new Person();
        try {
            person.setAge(1000);
        }catch (NullPointerException e){
            e.getCause();
        }catch (NumberFormatException e){
            e.getMessage();
        }
        catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        System.out.print("Age : " + person.getAge());
    }
}
