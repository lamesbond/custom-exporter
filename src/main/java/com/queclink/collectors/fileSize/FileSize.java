package com.queclink.collectors.fileSize;

import java.io.File;

public class FileSize {
    public static long getFileSize(String filename) {
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            System.out.println('"'+filename+'"'+"  是目录");
            return -1;
        }
        return file.length();
    }
}