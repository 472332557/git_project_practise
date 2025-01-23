package com.liangzc.example.webservice.dom4jTest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.List;

public class QueryTest {

    public static void main(String[] args) throws DocumentException {

        parse();
    }

    public static void parse() throws DocumentException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<SzfsTwsReturn>\n" +
                "  <reqmsgtype>AUTHCONTRACTQUERY</reqmsgtype>\n" +
                "  <reqversion>2.0</reqversion>\n" +
                "  <reqsubnode>C00000000X77</reqsubnode>\n" +
                "  <reqmsgno>16403112932845</reqmsgno>\n" +
                "  <status>00</status>\n" +
                "  <procode>FSCI0000</procode>\n" +
                "  <remark>成功</remark>\n" +
                "  <bizresult>\n" +
                "    <outid>20150505629000000048</outid>\n" +
                "    <subdate>20150505</subdate>\n" +
                "    <transno>7332794</transno>\n" +
                "    <totalnum>3</totalnum>\n" +
                "    <details>\n" +
                "      <detail>\n" +
                "        <detail>\n" +
                "          <lstno>3</lstno>\n" +
                "          <custno>ZJY10001</custno>\n" +
                "          <corpno>ZJYWS0000001  </corpno>\n" +
                "          <feeitem>00200</feeitem>\n" +
                "          <contractno>5CD2021110300100000000000000000000131</contractno>\n" +
                "          <acctname>郝劭文</acctname>\n" +
                "          <bankno>316584000013  </bankno>\n" +
                "          <acctno>622312061026003</acctno>\n" +
                "          <currency>CNY</currency>\n" +
                "          <oncelimit>500.00</oncelimit>\n" +
                "          <duedate>20221103</duedate>\n" +
                "          <signdate>20211103</signdate>\n" +
                "          <effectdate>20211103</effectdate>\n" +
                "          <inputdate>20211101</inputdate>\n" +
                "          <status>00</status>\n" +
                "          <tel>15825866206</tel>\n" +
                "          <addr>香港旺角XXX路</addr>\n" +
                "          <payername>郝劭文</payername>\n" +
                "          <payeridtype>01</payeridtype>\n" +
                "          <payerid>420225199006012506</payerid>\n" +
                "          <accttype>1</accttype>\n" +
                "          <authmodel>4</authmodel>\n" +
                "          <timeunit>2</timeunit>\n" +
                "          <timestep>1</timestep>\n" +
                "          <timedesc>月费</timedesc>\n" +
                "          <moneylimit>20000</moneylimit>\n" +
                "          <transtype>641</transtype>\n" +
                "          <subnode>C00000000X77</subnode>\n" +
                "          <subdate>20150505</subdate>\n" +
                "          <transno>7332794</transno>\n" +
                "        </detail>\n" +
                "        <detail>\n" +
                "          <lstno>10000001</lstno>\n" +
                "          <custno>ZJY10001</custno>\n" +
                "          <corpno>ZJYWS0000001  </corpno>\n" +
                "          <feeitem>00100;00101</feeitem>\n" +
                "          <contractno>5CD202111030010000000000000000000013</contractno>\n" +
                "          <acctname>郝劭文</acctname>\n" +
                "          <bankno>316584000013  </bankno>\n" +
                "          <acctno>622312061026003</acctno>\n" +
                "          <currency>CNY</currency>\n" +
                "          <oncelimit>500.00</oncelimit>\n" +
                "          <duedate>20221103</duedate>\n" +
                "          <signdate>20211103</signdate>\n" +
                "          <effectdate>20211103</effectdate>\n" +
                "          <inputdate>20211101</inputdate>\n" +
                "          <status>00</status>\n" +
                "          <tel>15825866206</tel>\n" +
                "          <addr>香港旺角XXX路</addr>\n" +
                "          <payername>郝劭文</payername>\n" +
                "          <payeridtype>01</payeridtype>\n" +
                "          <payerid>420225199006012506</payerid>\n" +
                "          <accttype>1</accttype>\n" +
                "          <authmodel>4</authmodel>\n" +
                "          <timeunit>2</timeunit>\n" +
                "          <timestep>1</timestep>\n" +
                "          <timedesc>月费</timedesc>\n" +
                "          <moneylimit>20000</moneylimit>\n" +
                "          <transtype>641</transtype>\n" +
                "          <subnode>C00000000X77</subnode>\n" +
                "          <subdate>20150505</subdate>\n" +
                "          <transno>7332794</transno>\n" +
                "        </detail>\n" +
                "        <detail>\n" +
                "          <lstno>10000002</lstno>\n" +
                "          <custno>ZJY10001</custno>\n" +
                "          <corpno>ZJYWS0000001  </corpno>\n" +
                "          <feeitem>00200;00201;00300</feeitem>\n" +
                "          <contractno>5CD20211103001000000000000000000000011</contractno>\n" +
                "          <acctname>郝劭文</acctname>\n" +
                "          <bankno>316584000013  </bankno>\n" +
                "          <acctno>622312061026003</acctno>\n" +
                "          <currency>CNY</currency>\n" +
                "          <oncelimit>200.00</oncelimit>\n" +
                "          <duedate>20251103</duedate>\n" +
                "          <signdate>20211103</signdate>\n" +
                "          <effectdate>20211103</effectdate>\n" +
                "          <inputdate>20211102</inputdate>\n" +
                "          <status>00</status>\n" +
                "          <tel>15825866206</tel>\n" +
                "          <addr>香港旺角XXX路</addr>\n" +
                "          <payername>郝劭文</payername>\n" +
                "          <payeridtype>01</payeridtype>\n" +
                "          <payerid>420225199006012506</payerid>\n" +
                "          <accttype>1</accttype>\n" +
                "          <authmodel>2</authmodel>\n" +
                "          <timeunit>2</timeunit>\n" +
                "          <timestep>3</timestep>\n" +
                "          <timedesc>季费</timedesc>\n" +
                "          <moneylimit>20000</moneylimit>\n" +
                "          <transtype>641</transtype>\n" +
                "          <subnode>C00000000X77</subnode>\n" +
                "          <subdate>20150505</subdate>\n" +
                "          <transno>7332794</transno>\n" +
                "        </detail>\n" +
                "      </detail>\n" +
                "    </details>\n" +
                "  </bizresult>\n" +
                "</SzfsTwsReturn>";

        Document document = DocumentHelper.parseText(xml);
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());


        Element element1 = rootElement.element("bizresult").element("details").element("detail");

        List elements = element1.elements();
        Iterator iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();

            System.out.println(element.getName());
            System.out.println(element.element("contractno").getText());

        }


        Element element = (Element) element1.elements().get(0);
        System.out.println(element.element("contractno").getText());
    }
}
