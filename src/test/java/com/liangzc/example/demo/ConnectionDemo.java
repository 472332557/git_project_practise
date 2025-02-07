package com.liangzc.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.liangzc.example.web_start.User;
import okhttp3.*;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ContextConfiguration(locations = {"classpath:spring/application-context.xml"})
@RunWith(value = SpringJUnit4ClassRunner.class)
public class ConnectionDemo {

    public static final String getUrl = "http://localhost:8081/start?id=" + 666;

    public static final String postUrl = "http://localhost:8081/postTest";

    public static final String postUrlJson = "http://localhost:8081/postJsonTest";


    //HttpURLConnection get
    @Test
    public void httpConnectionUrlTest() throws IOException {
        StringBuffer result = new StringBuffer();
        URL url1 = new URL(getUrl);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(false);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        System.out.println(result.toString());
    }

    //HttpURLConnection post
    @Test
    public void httpConnectionUrlPostTest() throws IOException {
        StringBuffer result = new StringBuffer();
        URL url1 = new URL(postUrl);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.connect();
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("id", 666);
        OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
        for (String key : requestMap.keySet()) {
            outputStream.write(key + "=" + requestMap.get(key));
        }

        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        System.out.println("响应code=" + responseCode);
        if (responseCode == 200) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        bufferedReader.close();
        System.out.println("响应报文：" + result.toString());
    }

    //RestTemplate get
    @Test
    public void RestTemplateGet() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/start?id=" + 1;
        String result = restTemplate.getForObject(getUrl, String.class);
        System.out.println("返回结果：" + result);
    }


    @Test
    public void RestTemplateGetForCookie() {
        RestTemplate restTemplate = new RestTemplate();
        String cookie = "JSESSIONID=A8E097EAE0B37DCC6F0DD4C79A5C5DAE; _segiupt_ts=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHBpcmVzX2luIjoyNDE5MjAwLCJpYXQiOjE2ODE3MTQ0NzYsInNhbHQiOiIhbWokVkNVXVZpRGhzUVJRIiwidHlwZSI6InNlc3Npb25fdG9rZW4iLCJ1c2VybmFtZSI6IlNFR0lfQl9BRE1JTjAwNSJ9.iazJzCydUSFcq6kFIkCuuYUrVPRvcP1icgUdQLdIRxnPvfrtaefK-nRW9imnP0x0VHZ4zRuR6R-G4U9huHq57w; _segiupt_as=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJjbGllbnRfaWQiOiJzZWdpIiwiZXhwaXJlc19pbiI6ODY0MDAsImlhdCI6MTY4MTcxNDQ3Niwic2FsdCI6IkhUeDJjazFOUStSVWFRQSEiLCJzY29wZSI6InNfYWRtaW4iLCJ0eXBlIjoiYWNjZXNzX3Rva2VuIiwidXNlcl9pZCI6IlNFR0lfQl8yNDI3OTM4IiwidXNlcm5hbWUiOiJTRUdJX0JfQURNSU4wMDUifQ._sHevIUgP8ken4BgUJMOyffrbc8oXSoessux6RYauiRT7FzETNUrd_hKTXFFWh1foGwPgdKYCEqnpnI2lYhcXg; _segiupt_ci=segi; _segiupt_ty=SEGI_B_; aid=2427938; oid=1000";
        String url = "https://leasewx.cmsk1979.com/uhomecp-lease/admin/contract/settle/queryContractDetailForCommission?contractNo=ZGDX-2018-08-0013";
        HttpHeaders httpHeaders = new HttpHeaders();
        List<String> cookies = new ArrayList<>();
        cookies.add(cookie);
        httpHeaders.put(HttpHeaders.COOKIE, cookies);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println("返回结果：" + result);
    }


    //RestTemplate post  key-value
    @Test
    public void RestTemplatePost() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/postTest";

        //设置请求头信息
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(type);
        //设置请求参数
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap();
        multiValueMap.add("id", "666");
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(multiValueMap, httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(postUrl, entity, String.class);
        String body = responseEntity.getBody();
        System.out.println("返回信息：" + body);
    }

    //RestTemplate postJson
    @Test
    public void RestTemplatePostJson() {
        RestTemplate restTemplate = new RestTemplate();
        //设置请求头信息
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(type);
        //构建请求参数
        User user = new User();
        user.setName("lili");
        user.setAge("20");
        user.setGender("女");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(postUrlJson, user, String.class);
        String body = responseEntity.getBody();
        System.out.println("返回信息：" + body);
    }

    //httpClient get
    @Test
    public void httpClientGet() {
        String url = "http://localhost:8081/start?id=" + 1;
        //获得http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //创建get请求
        HttpGet httpGet = new HttpGet(getUrl);
        CloseableHttpResponse response = null;
        try {
            //发送get请求
            response = httpClient.execute(httpGet);
            System.out.println("响应状态为：" + response.getStatusLine());
            org.apache.http.HttpEntity entity = response.getEntity();
            if (entity != null) {
                System.out.println("响应长度为：" + entity.getContentLength());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                System.out.println("返回信息为：" + builder.toString());
                System.out.println("响应内容为：" + JSONObject.toJSONString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //httpClient post
    @Test
    public void httpClientPost() {
        //获得http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //创建get请求
        HttpPost httpPost = new HttpPost(postUrl);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        CloseableHttpResponse response = null;
        try {
            //构建请求参数
            List<NameValuePair> paramList = Lists.newArrayList();
            paramList.add(new BasicNameValuePair("id", "666"));
            //设置请求参数，放入请求体
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            //发送post请求
            response = httpClient.execute(httpPost);
            System.out.println("响应状态为：" + response.getStatusLine());
            org.apache.http.HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                System.out.println("响应长度为：" + responseEntity.getContentLength());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                System.out.println("返回信息为：" + builder.toString());
                System.out.println("响应内容为：" + JSONObject.toJSONString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //httpClient post json格式请求
    @Test
    public void httpClientPostOfJson() {
        //获得http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //创建get请求
        HttpPost httpPost = new HttpPost(postUrlJson);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        //构建请求参数
        User user = new User();
        user.setName("lili");
        user.setAge("20");
        user.setGender("女");
        StringEntity stringEntity = new StringEntity(JSONObject.toJSONString(user), "UTF-8");
        //设置请求参数，放入请求体
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            //发送post请求
            response = httpClient.execute(httpPost);
            System.out.println("响应状态为：" + response.getStatusLine());
            org.apache.http.HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                System.out.println("响应长度为：" + responseEntity.getContentLength());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                System.out.println("返回信息为：" + builder.toString());
                System.out.println("响应内容为：" + JSONObject.toJSONString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * HttpClient一般使用按下面的方式来，有EntityUtils工具类来解析请求返回的信息
     * 不需要在上面的方法中，拿到输入流然后再去自己处理了，查看了源码，源码也是如此处理
     */
    @Test
    public void httpClientPostOfJsonNew() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(postUrlJson);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        //构建请求参数
        User user = new User();
        user.setName("lili");
        user.setAge("20");
        user.setGender("女");
        StringEntity stringEntity = new StringEntity(JSONObject.toJSONString(user), "UTF-8");
        //设置请求参数，放入请求体
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            System.out.println("请求返回状态码：" + statusCode);
            org.apache.http.HttpEntity responseEntity = response.getEntity();
            String toString = EntityUtils.toString(responseEntity);
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println("请求返回：" + toString);
            } else {
                System.out.println("请求返回：" + toString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // okHttp  get
    @Test
    public void okHttpGet() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(getUrl).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {

                ResponseBody responseBody = response.body();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                System.out.println("返回信息为：" + builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

    // okHttp  post
    @Test
    public void okHttpPost() {
        OkHttpClient okHttpClient = new OkHttpClient();
        //构建请求参数
        User user = new User();
        user.setName("lili");
        user.setAge("20");
        user.setGender("女");
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSONObject.toJSONString(user), mediaType);
        Request request = new Request.Builder().url(postUrlJson).post(body).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {

                ResponseBody responseBody = response.body();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                System.out.println("返回信息为：" + builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }


    //调用DeepSeek
    @Test
    public void okHttpPostDeepSeek() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json;charset=utf-8");
        String req = "{\n  \"messages\": [\n    {\n      \"content\": \"You are a helpful assistant\",\n      \"role\": \"system\"\n    },\n    {\n      \"content\": \"Hi\",\n      \"role\": \"user\"\n    }\n  ],\n  \"model\": \"deepseek-chat\",\n  \"frequency_penalty\": 0,\n  \"max_tokens\": 2048,\n  \"presence_penalty\": 0,\n  \"response_format\": {\n    \"type\": \"text\"\n  },\n  \"stop\": null,\n  \"stream\": false,\n  \"stream_options\": null,\n  \"temperature\": 1,\n  \"top_p\": 1,\n  \"tools\": null,\n  \"tool_choice\": \"none\",\n  \"logprobs\": false,\n  \"top_logprobs\": null\n}";
        RequestBody body = RequestBody.create(req,mediaType);
        Request request = new Request.Builder()
                .url("https://api.deepseek.com/chat/completions")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer sk-315b921a5bc24bca96248f8c4a1aa7e9")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {

                ResponseBody responseBody = response.body();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseBody.byteStream()));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    builder.append(line);
                }
                System.out.println("返回信息为：" + builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

}
