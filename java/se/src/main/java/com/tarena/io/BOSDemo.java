package com.tarena.io;

import com.tarena.util.FileUtil;

import java.io.*;

/**
 * @author SuShaohua
 * @date 2016/7/27 19:45
 * @description
 */
public class BOSDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FileUtil.IO + File.separator + "bos.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        String str = "Good good study";
        byte[] data = str.getBytes();
        bos.write(data);
        bos.flush();
        bos.close();
    }
}
