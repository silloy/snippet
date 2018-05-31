package com.tarena.io;

import com.tarena.util.FileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author SuShaohua
 * @date 2016/7/28 9:55
 * @description
 */
public class CopyDemo1 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(FileUtil.IO + File.separator + "book.pdf", "r");
        RandomAccessFile rafc  = new RandomAccessFile(FileUtil.IO + File.separator + "bookcp.pdf", "rw");
        int d = -1;
        long start = System.currentTimeMillis();
        byte[] data = new byte[1024 * 10];
        int len = -1;
        while (-1 != (len = raf.read(data)))
            rafc.write(data, 0, len);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        raf.close();
        rafc.close();
    }
}
