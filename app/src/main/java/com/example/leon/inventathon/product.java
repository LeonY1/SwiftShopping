package com.example.leon.inventathon;

/**
 * Created by leon on 5/14/16.
 */
public class product implements Comparable<product>{
    public String name ="";
    public String productno = "";
    public int amt = 0;
    public product(String a, String b, int c) {
        name = a;
        productno = b;
        amt = c;
    }
    public boolean equals(Object other){
        if(((product)other).name.equals(name))
            return true;
        return false;
    }
    public int compareTo(product other){
        return name.compareTo(other.name);
    }
    public String toString(){
        return name;
    }
}
