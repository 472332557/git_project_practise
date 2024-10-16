package com.liangzc.example.webservice.dom4jTest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import java.util.List;

public class HqcCollectionXmlParseTest {


    public static void main(String[] args) throws DocumentException {


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

        System.out.println("id -------" + id);

        Element state = rootElement.element("state");
        if (state != null) {
            String text = state.getText();
            System.out.println(text);
        }
    }


    @Test
    public void parseCMBNotify() throws DocumentException {
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


    @Test
    public void parseXmlTest() throws DocumentException {

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<SOAP-ENV:Envelope\n" +
                "    xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "    xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\"\n" +
                "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "    xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n" +
                "    xmlns:corpdsf=\"http://www.ylink.com.cn/corpdsf.wsdl\">\n" +
                "    <SOAP-ENV:Body>\n" +
                "        <corpdsf:corpdsfResponse>\n" +
                "            <Body>\n" +
                "                <bodyzip>0</bodyzip>\n" +
                "                <bodyencrypt>1</bodyencrypt>\n" +
                "                <signature></signature>\n" +
                "                <msgbody>220e691491596989ce3461ea56cd6e5130014968433cafd5a9321d364b167dc934ddb47db49e4d870eb6380f7df7513767397b9869359542fe43d7ae7d96404f75014aaa7ed046b5b08cf32d57b5cad83a057022e683564e689bf3821d564c96919e7a234bafcd6995ea4701c6d4be71212cbef1a3d2cb8da583b936fa38686fe3a5b45ae31a63ffed566bd1c9b31518c0e369a33dbbd6121db1205347aea1c117925b7eb645c03bb5c4b44d12dc74bc96655e3d6166e82c0c67f71a704660ea5224439843b37429fe666f02dad2b082adba377611275b8af549ec61a7cab1a7092251afc0c90c24cdf1e5a33fc318468fd881814c1622212f47eaabc49d8b7e905cf0d731c16f8bac4bd13e705797fc2857e7ec52a09751237d1f95609d026e4d5ee6228c48ee1557c3fcb4b0c987c4c93ec0a2867bf2f4452d06b3eebf60022aa139f82058399f37d83ee12f1e5508cbe643d8b9ab4576133a5eadd24859515df60060480adba0b24288e8174f5ad6bf512895ef742afc38115090a51fc86ef4c46b9ad453aa463c1aae2a0eff5074d80df468cd394e5521aeebfdf13b0fe0632798534439991d0f42ad874c924a023f70368d169d995e8a91025684a100b4e28b1386b72c1607e74726101a94a380dbc50a0571b82272cfcfe8ef00aa810f482b602842e01f42df4374e176b4145c0b8d2258e70bed70fdc16d2b56815baa</msgbody>\n" +
                "            </Body>\n" +
                "        </corpdsf:corpdsfResponse>\n" +
                "    </SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";
        Document document = DocumentHelper.parseText(xml);
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());
        System.out.println(rootElement.element("Body").element("corpdsfResponse").element("Body").element("msgbody").getText());


    }
}
