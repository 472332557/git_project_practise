package com.liangzc.example.spring_demo.mvcframework.v1.servlet;

import com.liangzc.example.spring_demo.annotation.LzcAutowired;
import com.liangzc.example.spring_demo.annotation.LzcController;
import com.liangzc.example.spring_demo.annotation.LzcRequestMapping;
import com.liangzc.example.spring_demo.annotation.LzcService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class DispatchServlet extends HttpServlet {

    private static final Properties properties = new Properties();

    private static final List<String> classNames = new ArrayList<>();

    private static final Map<String, Object> ioc = new HashMap<>();

    private static final Map<String, Method> handlerMapping = new HashMap<>();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            doHandler(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("request 500");
        }
    }

    private void doHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        requestURI = requestURI.replaceAll("/+", "/");

        //cookie练习，cookie和session不同，cookie可以长期存在于客户端（浏览器）。只要不超过设定的时间，而session会随着浏览器的关闭而关闭。
        String name = req.getParameter("name");
        if ("111".equals(name)){
            Cookie cookie = new Cookie("name", name);
            cookie.setMaxAge(300);
            resp.addCookie(cookie);
        }else {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("name")){
                    System.out.println("第一次设置的cookie为："+cookie.getName() +"=" +cookie.getValue());
                }
            }
        }
        if(!handlerMapping.containsKey(requestURI)){
            resp.getWriter().write("404 Not found");
            return;
        }
        Method method = handlerMapping.get(requestURI);
        String beanName = toLowerNameFirst(method.getDeclaringClass().getSimpleName());
        Map<String, String[]> parameterMap = req.getParameterMap();
        method.invoke(ioc.get(beanName),new Object[]{req,resp,parameterMap.get("name")[0]});
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1、加载配置文件
        loadConfigFile(config.getInitParameter("contextConfig"));

        //2、扫描相关的类
        scanClasses(properties.getProperty("basePackage"));

        //3、将相关类存放至IoC容器
        doInstance();

        //4、执行依赖注入
        doDependencyInjection();

        //5、url和method映射
        doHandlerMapping();
    }

    private void doDependencyInjection() {
        if(ioc.isEmpty()){return;}
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if(declaredField.isAnnotationPresent(LzcAutowired.class)){
                    LzcAutowired lzcAutowired = declaredField.getAnnotation(LzcAutowired.class);
                    String beanName = lzcAutowired.value();
                    if("".equals(beanName)){
                        beanName = declaredField.getName();
                    }

                    declaredField.setAccessible(true);

                    try {
                        //给字段赋值
                        declaredField.set(entry.getValue(),ioc.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void doHandlerMapping() {
        if(ioc.isEmpty()){return;}
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if(clazz.isAnnotationPresent(LzcController.class)){
                LzcRequestMapping annotation = clazz.getAnnotation(LzcRequestMapping.class);
                String baseUrl = annotation.value();
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if(method.isAnnotationPresent(LzcRequestMapping.class)){
                        LzcRequestMapping lzcRequestMapping = method.getAnnotation(LzcRequestMapping.class);
                        String url = ("/"+baseUrl + "/"+lzcRequestMapping.value()).replaceAll("/+","/");
                        handlerMapping.put(url, method);
                    }
                }
            }
        }
    }

    /**
     * 只有添加了注解LzcController、LzcService、LzcRepository等的才添加进IoC容器
     */
    private void doInstance() {
        if(classNames.isEmpty()){return;}
        for (String className : classNames) {
            try {
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(LzcController.class)){
                    //需要将类名首字母小写
                    String beanName = toLowerNameFirst(clazz.getSimpleName());
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                }else if (clazz.isAnnotationPresent(LzcService.class)){
                    LzcService lzcService = clazz.getAnnotation(LzcService.class);
                    String beanName = toLowerNameFirst(clazz.getSimpleName());
                    if(!"".equals(lzcService.value())){
                        beanName = lzcService.value();
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);

                    //依赖注入时，controller中一般是service接口，所以需要将service添加进IoC容器，value是实现
                    for (Class<?> clazzInterface : clazz.getInterfaces()) {
                        if(ioc.containsKey(clazzInterface.getName())){
                            throw new Exception("接口已存在！");
                        }
                        ioc.put(clazzInterface.getName(), instance);
                    }
                }else {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String toLowerNameFirst(String simpleName) {

        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void scanClasses(String basePackage) {
        //读取该路径下的class文件,将.替换成为文件路径
        URL url = this.getClass().getClassLoader().getResource("/"+basePackage.replaceAll("\\.", "/"));
        File file = new File(url.getFile());
        for (File listFile : file.listFiles()) {
            if(listFile.isDirectory()){
                scanClasses(basePackage + "." +listFile.getName());
            }else {
                String className = listFile.getName();
                if(!className.endsWith(".class")){continue;}
                className = basePackage + "."+ className.replace(".class", "");
                classNames.add(className);
            }
        }
    }

    private void loadConfigFile(String contextConfig) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(contextConfig);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
