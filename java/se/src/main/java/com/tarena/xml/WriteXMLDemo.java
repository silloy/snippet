package com.tarena.xml;

import com.google.common.collect.Lists;
import com.tarena.model.Emp;
import com.tarena.util.FileUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @param
 * @author SuShaohua
 * @date 2016/4/30
 * @description
 */
public class WriteXMLDemo {
    public static void main(String[] args){
        try{
            List<Emp> empList = Lists.newArrayList();
            empList.add(new Emp(1, "Smith", "Male", 43, 5000));
            empList.add(new Emp(2, "Jmith", "Female", 43, 5000));
            empList.add(new Emp(3, "Kmith", "Male", 23, 5000));
            empList.add(new Emp(4, "Omith", "Female", 43, 3000));
            empList.add(new Emp(5, "Pmith", "Male", 23, 5000));

            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement("list");
            for (Emp emp : empList){
                Element empEle = root.addElement("emp");
                empEle.addAttribute("id", String.valueOf(emp.getId()));
                Element nameEle = empEle.addElement("name");
                nameEle.addText(emp.getName());
                Element ageEle = empEle.addElement("age");
                ageEle.addText(String.valueOf(emp.getAge()));
                Element salaryEle = empEle.addElement("salaryEle");
                salaryEle.addText(String.valueOf(emp.getSalary()));
                Element genderEle = empEle.addElement("gender");
                genderEle.addText(emp.getGender());
            }
            XMLWriter writer = new XMLWriter();
            FileOutputStream fos = new FileOutputStream(FileUtil.XML + File.separator + "test.xml");
            writer.setOutputStream(fos);
            writer.write(doc);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
