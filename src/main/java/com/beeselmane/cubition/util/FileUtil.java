package com.beeselmane.cubition.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    private FileUtil() {
    }

    public static String readFull(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), Charset.defaultCharset());
    }

    public static void createIfNoExist(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) return;
        if (!file.createNewFile()) throw new IOException("Could not create file: " + path);
    }
}
