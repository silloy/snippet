package com.tarena.model;

import com.tarena.exception.IllegalAgeException;

import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/16
 * @description Serializable need UID
 */
public class Person implements Serializable {
    //VersionUID 控制序列化和反序列化
    private String name;
    private int age;
    private String gender;
    private transient List<String> otherInfo;
    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getOtherInfo() {
        return otherInfo;
    }

    public String getName() {
        return name;
    }

    public void setOtherInfo(List<String> otherInfo) {
        this.otherInfo = otherInfo;
    }

    public void setAge(int age) throws IllegalAgeException {
        if (age < 0 || age > 100)
            throw new IllegalAgeException("Do not match human age");
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        //null 不能调用方法
        return "Name : " + name + "\nAge : " + age + "\nGender : " + gender  + "\nOtherInfo : " + otherInfo;
    }
}
