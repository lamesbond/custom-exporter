package com.queclink.collectors.fileSize;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileOrder {

    public static File[] getFileOrder(String pathName) {
        File dir = new File(pathName);
        File[] files = dir.listFiles();
        if (files == null || 0 == files.length) {
            System.out.println('"'+pathName+'"'+"  目录不存在");
            return null;
        }
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                return f2.compareTo(f1);
            }
        });
        return files;
    }
}