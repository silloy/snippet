package com.tarena.util;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SuShaohua
 * Date: 2017/3/21
 * Time: 18:21
 * CopyRight:HuiMei Engine
 */
public class CSVUtil {

    /**
     * @description exportCSV
     * @author SuShaohua
     * @date 2017-03-21 18:29
     * @param
    */
    public static boolean exportCsv(File file, List<String> dataList) {
        boolean isSuccess = false;

        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if (null != dataList) {
                for (String data : dataList) {
                    bw.append(data).append("\r");
                }
            }
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return isSuccess;
    }


    /**
     * @description importCSV
     * @author SuShaohua
     * @date 2017-03-21 18:31
     * @param
    */
    public static List<String> importCsv(File file) {
        List<String> dataList = Lists.newArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }

    public static void main(String[] args) {
        List<String> dataList=Lists.newArrayList();
        dataList.add("1,张三,男");
        dataList.add("2,李四,男");
        dataList.add("3,小红,女");
        boolean isSuccess=exportCsv(new File("D:/ljq.csv"), dataList);
        System.out.println(isSuccess);


        List<String> icsv=importCsv(new File("C:\\Users\\SuShaohua\\Downloads\\sqlresult_1254599.csv"));
        if(icsv!=null && !icsv.isEmpty()){
            for(String data : icsv){
                String[] strList = StringUtils.splitString(data, ",");
                System.out.println(strList[1]);
            }
        }
        Gson gson = new Gson();
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>(){}.getType());

        String json = "{\"name\":\"怪盗kidou\",\"age\":\"24\"}";
        JsonReader reader = new JsonReader(new StringReader(json));

    }

}
