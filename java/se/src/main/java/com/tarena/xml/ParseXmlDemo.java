package com.tarena.xml;

import com.google.common.collect.Lists;
import com.tarena.model.Emp;
import com.tarena.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * @author SuShaohua
 * @date 2016/7/27 15:03
 * @description
 */
public class ParseXmlDemo {
    public static void main(String[] args) {
        try {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(FileUtil.XML) + File.separator + "EmpList.xml");
            Element root = doc.getRootElement();
            List<Emp> empList = Lists.newArrayList();
            List<Element> list = root.elements();
            for (Element element : list){
                Element nameEle = element.element("name");
                String name = nameEle.getTextTrim();
                int age = Integer.parseInt(element.elementTextTrim("age"));
                int salary = Integer.parseInt(element.elementTextTrim("salary"));
                String gender = element.elementTextTrim("gender");
                int id = Integer.parseInt(element.attribute("id").getValue());
                Emp emp = new Emp(id, name, gender, age, salary);
                empList.add(emp);
            }
            System.out.println(empList.toString());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
