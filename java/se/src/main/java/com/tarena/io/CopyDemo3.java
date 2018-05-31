package com.tarena.io;

import com.tarena.util.FileUtil;

import java.io.*;

/**
 * @author SuShaohua
 * @date 2016/7/28 10:23
 * @description
 */
public class CopyDemo3 {
    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream(FileUtil.IO + File.separator + "book.pdf");
        BufferedInputStream bis = new BufferedInputStream(fis);

        FileOutputStream fos = new FileOutputStream(FileUtil.IO  + File.separator + "bookcp.pdf");
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] date = new byte[1024 * 10];
        int d = -1;
        while ((d = bis.read()) != -1){
            bos.write(d);
        }
        System.out.println("Complete");
        bis.close();
        bos.close();

    }
}
