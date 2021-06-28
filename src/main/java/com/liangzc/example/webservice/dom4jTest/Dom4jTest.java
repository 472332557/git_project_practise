package com.liangzc.example.webservice.dom4jTest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4jTest {


    public static void main(String[] args) throws DocumentException {


        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \n" +
                "<四大名著>  \n" +
                "    <西游记 id=\"x001\">  \n" +
                "        <作者>吴承恩1</作者>  \n" +
                "        <作者>吴承恩2</作者>  \n" +
                "        <朝代>明朝</朝代>  \n" +
                "    </西游记>  \n" +
                "    <红楼梦 id=\"x002\">  \n" +
                "        <作者>曹雪芹</作者>  \n" +
                "    </红楼梦>  \n" +
                "</四大名著> ";



        Document document = DocumentHelper.parseText(xmlString);
        Element rootElement = document.getRootElement();
    }
}
