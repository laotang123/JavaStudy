package com.golaxy.indi.chapter17;

import java.util.HashSet;
import java.util.Set;

/*
 * @Author: ljf
 * @Date: 2020-11-28 19:39:40
 * @LastEditTime: 2020-11-29 08:17:42
 * @Description: 
 */
class Groundhog {
    int number;

    Groundhog(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Groundhog [number=" + number + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Groundhog other = (Groundhog) obj;
        if (number != other.number)
            return false;
        return true;
    }

}

public class SpringDetector {
    public static void main(String[] args) {
        Set<Groundhog> hashSet = new HashSet<>();
        hashSet.add(new Groundhog(1));
        hashSet.add(new Groundhog(1));

        System.out.println(hashSet);
        // EnumSet
        long i = 5;
        long var = 1L << i;
        System.out.println(var);
        System.out.println(Long.toBinaryString(var));
    }
}
