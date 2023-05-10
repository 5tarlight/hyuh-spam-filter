package io.yeahx4.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
}
