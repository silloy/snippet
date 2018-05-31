package com.tarena.io;

import com.tarena.util.FileUtil;

import java.io.*;

/**
 * @author SuShaohua
 * @date 2016/7/28 10:19
 * @description
 */
public class CopyDemo2 {
    public static void main(String[] args) throws IOException {
        /*
         * use fileInputStream, fileOutputStream copy file
         */
        FileInputStream fis = new FileInputStream(FileUtil.IO + File.separator + "book.pdf");
        FileOutputStream fos = new FileOutputStream(FileUtil.IO + File.separator + "bookcp.pdf");
        byte[] buf = new byte[1024 * 10];
        int len = -1;
        while (-1 != (len = fis.read(buf)))
            fos.write(buf, 0, len);
        fos.close();
        fis.close();
    }
}
