package io.yeahx4.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TextFileReader {
    public static String read(String path) {
        File file = new File(path);
        if (!file.exists())
            return "";

        try {
            Path target = Path.of(path);
            return Files.readString(target);
        } catch (IOException io) {
            return "";
        }
    }

    public static List<String> readAsLines(String path) {
        String content = TextFileReader.read(path);

        return Arrays.stream(content.split("\n")).map(str -> {
            String line = str.trim();
            if (line.equals(""))
                return null;
            else
                return line;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private final File file;
    private final String cache;

    public TextFileReader(File file) {
        this.file = file;
        this.cache = TextFileReader.read(this.file.getPath());
    }

    public TextFileReader(String path) {
        this.file = new File(path);
        this.cache = TextFileReader.read(this.file.getPath());
    }

    @Override
    public String toString() {
        return this.cache;
    }

    public List<String> toList() {
        return Arrays.stream(this.cache.split("\n"))
                .map(String::trim)
                .filter(str -> !str.equals(""))
                .collect(Collectors.toList());
    }

    public int countMatch(String word) {
         return this
                 .toList()
                 .stream()
                 .map(str -> str.split(word).length - 1)
                 .mapToInt(i -> i)
                 .sum();
    }
}
