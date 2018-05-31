package com.tarena.io;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author SuShaohua
 * @date 2016/8/23 12:55
 * @description
 */
public class ReadFileFromURL {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter a url: ");
        String urlString = new Scanner(System.in).nextLine();
        URL url = new URL(urlString);
        int count = 0;
        Scanner in = new Scanner(url.openStream());
        while (in.hasNext()){
            String line = in.nextLine();
            count += line.length();
        }
        System.out.println(count);
    }
}
