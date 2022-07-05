package com.liangzc.example.webservice.dom4jTest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

public class HqcCollectionXmlParseTest {


    public static void main(String[] args) throws DocumentException {

//        parse();
        parseCMBNotify();

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

        Element id = bizresult.element("id");

        System.out.println("id -------"+id);

        Element state = rootElement.element("state");
        if(state != null){
            String text = state.getText();
            System.out.println(text);
        }
    }


    public static void parseCMBNotify() throws DocumentException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<SzfsTwsCallback>\n" +
                "  <omsgtype>CONTRACTCHANGE</omsgtype>\n" +
                "  <oversion>2.0</oversion>\n" +
                "  <osubnode>C00000000X91</osubnode>\n" +
                "  <omsgno>16555194811070</omsgno>\n" +
                "  <osubdate>20220618</osubdate>\n" +
                "  <oseqid>bd618e1beeae11ec8ba7c9bdf684ce62</oseqid>\n" +
                "  <otransno>7375195</otransno>\n" +
                "  <status>00</status>\n" +
                "  <procode>FSCI0000</procode>\n" +
                "  <remark>授权成功</remark>\n" +
                "  <bizresult>\n" +
                "    <orequestid>bd618e1beeae11ec8ba7c9bdf684ce62</orequestid>\n" +
                "    <contractno>bd618e1ceeae11ec8ba7c9bdf684ce62</contractno>\n" +
                "    <authchl>01</authchl>\n" +
                "    <authurl>https://www.baidu.com/s?wd=12&amp;rsv_spt=1</authurl>\n" +
                "  </bizresult>\n" +
                "</SzfsTwsCallback>";

        Document document = DocumentHelper.parseText(xml);
        Element rootElement = document.getRootElement();

        String status = rootElement.element("status").getText();
        String contractno = rootElement.element("bizresult").element("contractno").getText();

        System.out.println(status);
        System.out.println(contractno);
    }
}
