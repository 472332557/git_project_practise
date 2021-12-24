package com.liangzc.example.webservice.dom4jTest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

public class HqcCollectionXmlParseTest {


    public static void main(String[] args) throws DocumentException {

        parse();

    }

    public static void parse() throws DocumentException {

        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<SzfsTwsReturn>\n" +
                "  <reqmsgtype>CONTRACTCHANGE</reqmsgtype>\n" +
                "  <reqversion>2.0</reqversion>\n" +
                "  <reqsubnode>C00000000X77</reqsubnode>\n" +
                "  <reqmsgno>16398070201358</reqmsgno>\n" +
                "  <status>19</status>\n" +
                "  <procode>FSCI0000</procode>\n" +
                "  <remark>协议申请银行已受理</remark>\n" +
                "  <bizresult>\n" +
                "    <outid>20150505629000000030</outid>\n" +
                "    <subdate>20150505</subdate>\n" +
                "    <transno>7332556</transno>\n" +
                "    <requestid>20150505629000000030</requestid>\n" +
                "    <contractno>B0212345679</contractno>\n" +
                "    <authchl>01</authchl>\n" +
                "  </bizresult>\n" +
                "</SzfsTwsReturn>";

        Document document = DocumentHelper.parseText(xmlStr);
        Element rootElement = document.getRootElement();

        Element reqmsgtype = rootElement.element("reqmsgtype");
        System.out.println(reqmsgtype.getText());

        Element bizresult = rootElement.element("bizresult");
        Element outid = bizresult.element("outid");
        System.out.println(outid.getText());

    }
}
