package com.jp.test.jpa.Utils;

public class Dog implements Comparable<Dog> {
    int age;

    public Dog(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Dog dog) {
        if (this.age > dog.age) return 1;
        else if (this.age < dog.age) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                '}';
    }
}
