package io.yeahx4;

import io.yeahx4.io.TextFileReader;

public class Main {
    public static void main(String[] args) {
        String content = TextFileReader.read("./words.txt");
        System.out.println(content);
    }
}
