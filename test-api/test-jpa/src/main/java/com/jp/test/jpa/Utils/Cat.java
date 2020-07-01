package com.jp.test.jpa.Utils;

public class Cat implements Comparable<Cat> {
    int age;
    double weight;

    public Cat(int age, double weight) {
        this.age = age;
        this.weight = weight;
    }

    @Override
    public int compareTo(Cat c) {
        if (this.age > c.age) return 1;
        else if (this.age < c.age) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", weight=" + weight +
                '}';
    }
}
