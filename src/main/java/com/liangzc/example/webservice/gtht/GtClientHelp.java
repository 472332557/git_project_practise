package com.liangzc.example.webservice.gtht;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GtClientHelp {

    public static String sendMsg(String url, String reqMsg) throws IOException {

        //建立连接
        URL url1 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.connect();

        //发送请求
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(getRequestParam(reqMsg).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

        //解析返回信息
        InputStream inputStream;
        if (connection.getResponseCode() == 200) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";
        StringBuilder sb = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        inputStream.close();
        System.out.println("响应消息========================result:" + sb.toString());
        return sb.toString();
    }

    private static String getRequestParam(String param) {
        StringBuilder sb = new StringBuilder();
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"webservices.nc.leatc.com\">");
        sb.append("<soapenv:Header/>");
        sb.append("<soapenv:Body>");
        sb.append("<web:getBillingToNC>");
        sb.append("<web:in0>").append(param).append("</web:in0>");
        sb.append("</web:getBillingToNC>");
        sb.append("</soapenv:Body>");
        sb.append("</soapenv:Envelope>");
        return sb.toString();
    }


    public static void main(String[] args) throws IOException, DocumentException {


        String param = "{" +
                "  \"applyDate\": \"2021-01-14\"," +
                "  \"code\": \"HTZG-6419\"," +
                "  \"companyCode\": \"C_C019128\"," +
                "  \"contractCode\": \"SJ-SP-FW-2021-0001\"," +
                "  \"def18\": \"1001A2100000000ON4RP\"," +
                "  \"finaOrg\": \"1\"," +
                "  \"ncOperator\": \"duzhenping\"," +
                "  \"orgCode\": \"103\"," +
                "  \"projCode\": \"109899\"," +
                "  \"systemId\": \"2\"," +
                "  \"vcustbank\": \"上海银行股份有限公司成都高新支行\"," +
                "  \"vcustbankacc\": \"03004334093\"," +
                "  \"vnote\": \"地址：锦城大道539号盈创动力大厦B座1203号房租,2020-12-15~2021-03-14,盈创动力大厦\"," +
                "  \"detail\": [" +
                "    {" +
                "      \"mattaxCode\": \"4\"," +
                "      \"matterCode\": \"1935468369425715372\"," +
                "      \"matterName\": \"租金\"," +
                "      \"measurementName\": \"QI\"," +
                "      \"model\": \"\"," +
                "      \"noTaxamount\": \"40092.86\"," +
                "      \"quantity\": \"1\"," +
                "      \"remarks\": \"地址：锦城大道539号盈创动力大厦B座1203号房租,2020-12-15~2021-03-14,盈创动力大厦\"," +
                "      \"spec\": \"无\"," +
                "      \"tax\": \"2004.64\"," +
                "      \"taxclass\": \"30405020204\"," +
                "      \"taxrate\": \"5.0\"," +
                "      \"total\": \"42097.5\"" +
                "    }" +
                "  ]" +
                "}";

        String s = GtClientHelp.sendMsg("http://10.240.1.23/services/LeatcNCService", param);
        System.out.println("返回信息=============================" + s);
        documentParseXML(s);


/*        System.out.println(s);

        JSONObject jsonObject = XML.toJSONObject(s);
        System.out.println(jsonObject);

        String aReturn = jsonObject.getJSONObject("S:Envelope").getJSONObject("S:Body").getJSONObject("ns2:nameTestResponse").get("return").toString();
        System.out.println(aReturn);

        Result result = JSON.parseObject(aReturn, Result.class);
        System.out.println(result);
        if(result.getCode().equals("ok")){
            System.out.println("SUCCESS");
        }else {
            System.out.println("FAIL");
        }*/

    }

    private static void documentParseXML(String s) throws DocumentException {
        Document document = DocumentHelper.parseText(s);

        Element rootElement = document.getRootElement();

//        Element element1 = rootElement.element("getBillingToNCResponse").element("out");

//        System.out.println(element1.getText());

        Element elementBody = (Element) rootElement.elements().get(0);

        Element element = elementBody.element("getBillingToNCResponse").element("out");

        System.out.println(element.getText());

    }

}
