package io.yeahx4;

import io.yeahx4.io.TextFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> content = TextFileReader.readAsLines("./words.txt");
        content.forEach(System.out::println);
    }
}
