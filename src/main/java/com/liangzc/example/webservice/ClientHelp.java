package com.liangzc.example.webservice;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientHelp {


    public static String sendMsg(String url,String reqMsg) throws IOException {

        //建立连接
        URL url1 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type","text/xml; charset=utf-8");
        connection.connect();

        //发送请求
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(getRequestParam(reqMsg).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

        //解析返回信息
        InputStream inputStream;
        if(connection.getResponseCode() == 200){
            inputStream = connection.getInputStream();
        }else {
            inputStream = connection.getErrorStream();
        }

        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";
        StringBuilder sb = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null){
            sb.append(line);
        }
        inputStream.close();
        return sb.toString();
    }

    private static String  getRequestParam(String param) {
        StringBuilder sb = new StringBuilder();
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.example.org/\">");
        sb.append("<soapenv:Header/>");
        sb.append("<soapenv:Body>");
        sb.append("<web:nameTest>");
        sb.append("<param>").append(param).append("</param>");
        sb.append("</web:nameTest>");
        sb.append("</soapenv:Body>");
        sb.append("</soapenv:Envelope>");
        return sb.toString();
    }


    public static void main(String[] args) throws IOException, DocumentException {


        String s = ClientHelp.sendMsg("http://192.168.4.48:9090/webservice?wsdl", "1");
        documentParseXML(s);



        System.out.println(s);

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
        }

    }

    private static void documentParseXML(String s) throws DocumentException {
        Document document = DocumentHelper.parseText(s);
        System.out.println(document);
        Element rootElement = document.getRootElement();
        System.out.println(rootElement);
        Element elementBody = (Element) rootElement.elements().get(0);
        System.out.println(elementBody);
        Element element = elementBody.element("nameTestResponse").element("return");
        System.out.println(element);
        System.out.println(element.getText());

    }
}
