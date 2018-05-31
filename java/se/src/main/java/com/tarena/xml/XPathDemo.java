package com.tarena.xml;

import com.tarena.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/30
 * @description
 */
public class XPathDemo {
    public static void main(String[] args){
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(FileUtil.XML + File.separator + "Emplist.xml"));
            List<Element> list = doc.selectNodes("/list/emp [salary > 4000]");
            for (Element o : list){
                System.out.println(o.element("name").getTextTrim());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
