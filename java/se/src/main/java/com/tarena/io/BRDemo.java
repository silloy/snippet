package com.tarena.io;

import com.tarena.util.FileUtil;

import java.io.*;

/**
 * @author SuShaohua
 * @date 2016/7/27 19:49
 * @description
 */
public class BRDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FileUtil.IO + File.separator + "note.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        //缓冲字符输入流不支持直接将字节流转换为缓冲流,以行为单位读取
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while (null != (line = br.readLine())){
            System.out.println(line);
        }
        br.close();
    }
}
