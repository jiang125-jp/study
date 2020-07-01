package com.jp.test.jpa.Utils;

public class DogComparator implements Comparator<Dog> {

    @Override
    public int comparator(Dog d1, Dog d2) {
        if(d1.age > d2.age) return -1;
        else if(d1.age < d2.age) return 1;
        return 0;
    }
}
