package com.tarena.test;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/3/26
 * Time: 22:37
 * CopyRight:HuiMei Engine
 */
public class Person {
    private final Integer age;
    private final String name;

    protected Person() {
        this(0, (String) null);
    }

    Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public static Builder custom() {
        return new Builder();
    }

    public static class Builder {
        private Integer age;
        private String name;
        Builder(){
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Person build() {
            return new Person(this.age, this.name);
        }

    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

}
