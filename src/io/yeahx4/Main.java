package io.yeahx4;

import io.yeahx4.io.TextFileReader;

public class Main {
    public static void main(String[] args) {
        TextFileReader txt = new TextFileReader("./words.txt");
        System.out.println(txt.countMatch("the"));
    }
}
