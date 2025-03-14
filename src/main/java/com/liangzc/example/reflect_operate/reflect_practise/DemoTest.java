package com.liangzc.example.reflect_operate.reflect_practise;

import com.liangzc.example.reflect_operate.AnnotationDemo;
import com.liangzc.example.reflect_operate.MyAnnotation;
import com.liangzc.example.spring_demo.applicationAware.ApplicationAwareDemo;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DemoTest {

    /**
     * 构造对象操作
     *
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @Test
    public void constructorTest() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取class对象
        Class<?> aClass = Class.forName("com.liangzc.example.reflect_operate.reflect_practise.Teacher");

        //获取构造对象，类型为public的
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("---------------------------以上是public类型的构造对象-----------------------");

        //获取所有类型的构造对象,public、private都获取到
        Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println(constructor);
        }
        System.out.println("---------------------------以上是所有类型的构造对象-----------------------");

        /*
            获取单独的某个public类型的构造对象，传递参数类型，根据参数类型判断返回哪个构造对象
         */

        //无参public类型的构造函数
        Constructor<?> constructor = aClass.getConstructor();
        System.out.println("public类型无参的构造对象：" + constructor);
        Object obj = constructor.newInstance();
        System.out.println(obj);

        //一个参数的public类型的构造对象
        Constructor<?> constructor1 = aClass.getConstructor(String.class);
        System.out.println("public类型一个有参的构造对象：:" + constructor1);
        Object obj2 = constructor1.newInstance("Marry");
        System.out.println(obj2);

        //两个参数的public类型的构造对象
        Constructor<?> constructor2 = aClass.getConstructor(String.class, int.class);
        System.out.println("public类型两个有参的构造对象：:" + constructor1);
        Object obj3 = constructor2.newInstance("Marry", 20);
        System.out.println(obj3);

        /**
         * 获取单独的某个private类型的构造对象，传递参数类型，根据参数类型判断返回哪个构造对象
         */
        //私有的构造函数，使用DeclaredConstructor获取
        Constructor<?> constructor3 = aClass.getDeclaredConstructor(String.class, int.class, String.class);
        System.out.println("private类型三个有参的构造对象：:" + constructor3);

        //暴力访问
        constructor3.setAccessible(true);
        Object obj4 = constructor3.newInstance("Marry", 20, "深圳");
        System.out.println(obj4);
    }


    /**
     * 成员变量的操作
     */
    @Test
    public void fieldTest() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取class对象
        Class<?> aClass = Class.forName("com.liangzc.example.reflect_operate.reflect_practise.Teacher");

        //获取成员变量，public类型：getFields()
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("---------------------------以上是public类型的成员变量集合-----------------------");

        //所有类型的：getDeclaredFields()
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        System.out.println("---------------------------以上是所有类型的成员变量集合-----------------------");

        //获取构造对象
        Constructor<?> constructor = aClass.getConstructor();
        Object obj = constructor.newInstance();

        /**
         * 获取某个public变量
         */
        Field adress = aClass.getField("adress");
        //给变量赋值,变量.set(当前的对象，要赋的值);
        adress.set(obj, "成都");
        System.out.println(obj);

        //私有类型变量name使用:getDeclaredField
        Field name = aClass.getDeclaredField("name");
        //暴力访问
        name.setAccessible(true);
        name.set(obj, "Aiden");
        System.out.println(obj);

        //默认变量：age，默认类型变量不需要暴力访问
        Field age = aClass.getDeclaredField("age");
        age.set(obj, 25);
        System.out.println(obj);
    }


    /**
     * 方法的操作
     */
    @Test
    public void methodTest() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取class对象
        Class<?> aClass = Class.forName("com.liangzc.example.reflect_operate.reflect_practise.Teacher");

        //获取方法列表，getMethods获取的是本类及其超类的方法
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("---------------------getMethods方法--------------------------------");

        //获取本类中的所有方法:getDeclaredMethods
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        System.out.println("------------------------获取本类中的所有方法:getDeclaredMethods------------------------------------------");
        Constructor<?> constructor = aClass.getConstructor();
        Object obj = constructor.newInstance();

        Method method1 = aClass.getMethod("method1");
        Object invoke = method1.invoke(obj);

        //获取私有方法使用:getDeclaredMethod
        Method method2 = aClass.getDeclaredMethod("method2", String.class);
        //暴力访问
        method2.setAccessible(true);
        Object invoke2 = method2.invoke(obj, "private method");
        String invoke2String = (String) invoke2;

        Method method3 = aClass.getMethod("method3");
        Object invoke1 = method3.invoke(obj);
        System.out.println("方法返回信息：" + invoke1);

        //获取默认方法：getDeclaredMethod，默认方法不需要暴力破解
        Method method4 = aClass.getDeclaredMethod("method4");
        method4.invoke(obj);

        System.out.println("-------------------------method5--------------------------------");
        Method method5 = aClass.getDeclaredMethod("method5", boolean.class, Long.class);
        method5.invoke(obj, false, null);
    }


    /**
     * 集合泛型测试,反射破坏集合中的泛型校验
     */
    @Test
    public void genericityTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(10);

        //无法添加字符型的
//        list.add("111");

        Class<? extends List> aClass = list.getClass();
        Method add = aClass.getMethod("add", Object.class);
        add.invoke(list, "添加字符类型");
        System.out.println(list);
    }

    @Test
    public void annotationTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        /**
         * Class类的获取方式
         */
        Class<?> c1 = Class.forName("com.liangzc.example.reflect_operate.AnnotationDemo");
        System.out.println(c1.hashCode());

        Class<AnnotationDemo> c2 = AnnotationDemo.class;
        System.out.println(c2.hashCode());

        AnnotationDemo annotationDemo = new AnnotationDemo();
        Class<? extends AnnotationDemo> c3 = annotationDemo.getClass();
        System.out.println(c3.hashCode());

        Method[] methods = c3.getMethods();
        for (Method method : methods) {
            System.out.println(method);
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation myAnnotation = method.getAnnotation(MyAnnotation.class);
                System.out.println(myAnnotation);
                int id = myAnnotation.id();
                String name = myAnnotation.name();
                System.out.println(id);
                System.out.println(name);
                for (Class<?> parameterType : method.getParameterTypes()) {
                    System.out.println("Parameter type: "+parameterType.getName());
                }
                //如果方法有MyAnnotation注解，则调用该方法
                method.invoke(annotationDemo, name);
            }
        }
    }
}


