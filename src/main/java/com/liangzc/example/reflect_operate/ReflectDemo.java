package com.liangzc.example.reflect_operate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ReflectDemo {


    public static void main(String[] args) {

        Dog dog = new Dog();
        fieldOperate(dog);
        methodOperate(dog);
//        System.out.println(dog);
        classOperate(dog);

    }


    public static void fieldOperate(Object object){
        Class<?> clazz = object.getClass();
        System.out.println("=======================成员变量操作===========================");
        //获得该类中的所有成员变量，包含private、protected、public等
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();//获得字段名
            System.out.println("字段名为：======》"+name);
            Class<?> type = declaredField.getType();
            String tymeName = type.getName();
            String typeSimpleName = type.getSimpleName();
            System.out.println("字段类型名name====》"+tymeName);
            System.out.println("字段类型名simpleName=======》"+typeSimpleName);
            declaredField.setAccessible(true);
            try{
                /**
                 * 字段赋值field.set(Object object,Object obj)
                 * object:当前变量所对应的对象
                 * obj：具体的赋值参数
                 */
                //为字段重新赋值
                if("food".equals(name)){
                    declaredField.set(object,"狗粮！");
                }
                if("hobbies".equals(name)){
                    declaredField.set(object,"游泳！");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("-------------------------------------------------------------------------");
            System.out.println();
        }
    }

    public static void methodOperate(Object object){
        System.out.println("=======================方法操作===========================");
        Class<?> clazz = object.getClass();
        //获得本类下的所有方法，包含public、private、protected
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            String methodName = declaredMethod.getName();
            System.out.println("方法名为===============："+methodName);

            Class<?> returnType = declaredMethod.getReturnType();
            String typeName = returnType.getName();
            String typeSimpleName = returnType.getSimpleName();
            System.out.println("方法返回类型名name=============："+typeName);
            System.out.println("方法返回类型名simpleName=============："+typeSimpleName);

            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            System.out.println("参数类型数量=========："+parameterTypes.length);
            Parameter[] parameters = declaredMethod.getParameters();
            System.out.println("参数数量=========："+parameters.length);

            for (Class<?> parameterType : parameterTypes) {
                String parameterTypeName = parameterType.getName();
                String parameterTypeSimpleName = parameterType.getSimpleName();
                System.out.println("参数类型名name==================："+parameterTypeName);
                System.out.println("参数类型名simpleName==================："+parameterTypeSimpleName);
            }

            for (Parameter parameter : parameters) {
                String parameterName = parameter.getName();
                System.out.println("参数名name===================："+parameterName);
            }

            try {
                /**
                 * 执行方法的调用method.invoke(Object object,Object[] objs)
                 * object:当前方法所在对象
                 * objs：方法形参具体对应的实参
                 */
                Object invoke = null;
                if(parameters.length == 0){
                    invoke = declaredMethod.invoke(object, new Object[]{});
                }else {
                    invoke = declaredMethod.invoke(object, new Object[]{"hello","WORLD"});
                }
                System.out.println(invoke);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }

    public static void classOperate(Object object){
        System.out.println("=======================类操作===========================");
        Class<?> clazz = object.getClass();
        String clazzName = clazz.getName();
        String clazzSimpleName = clazz.getSimpleName();
        System.out.println("类名name==================："+clazzName);
        System.out.println("类名simpleName==============："+clazzSimpleName);

        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            String interfaceName = anInterface.getName();
            String interfaceSimpleName = anInterface.getSimpleName();
            System.out.println("接口名name===================："+interfaceName);
            System.out.println("接口名simpleName==============："+interfaceSimpleName);
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }
}
