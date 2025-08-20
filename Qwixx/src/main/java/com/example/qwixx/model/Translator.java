package com.example.qwixx.model;

public class Translator {


    public Translator()
    { }

    public static int nameToIndex(String color)
    {
        if(color.equals("yellow")) return 0;
        if(color.equals("red")) return 1;
        if(color.equals("green")) return 2;
        if(color.equals("blue")) return 3;

        return 1000;
    }

    public static String indexToName(int idx)
    {
        if(idx==0) return "yellow";
        if(idx==1) return "red";
        if(idx==2) return "green";
        if(idx==3) return "blue";

        return "no color";
    }
    
}
