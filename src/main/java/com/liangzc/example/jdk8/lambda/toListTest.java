package com.liangzc.example.jdk8.lambda;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class toListTest {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student("lili",10,1,new Date());
        Student student2 = new Student("lucy",11,1,new Date());
        Student student3 = new Student("lei",12,1,new Date());
        Student student4 = new Student("mary",10,2,new Date());
        Student student5 = new Student("tom",9,2,new Date());
        Student student6 = new Student("jack",12,2,new Date());
        Student student7 = new Student("mali",10,3,new Date());
        Student student8 = new Student("kk",10,3,new Date());
        Student student9 = new Student("kk",20,3,new Date());
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);
        list.add(student9);
        System.out.println(JSONArray.toJSONString(list));

        //倒叙
        List<Student> collect = list.stream().filter(e -> e.getGrade() == 2).sorted((x,z)-> z.getAge().compareTo(x.getAge())).collect(Collectors.toList());
        List<String> collect1 = list.stream().map(Student::getName).distinct().collect(Collectors.toList());


        System.out.println(collect);
        System.out.println("collect1=============:"+collect1);


        System.out.println(list.stream().map(v -> v.getAge()).max(Comparator.comparing(Integer::intValue)).get());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(format.format(date.getTime()));
        System.out.println(date.getDate());


        //求和
        System.out.println(list.stream().mapToInt(Student::getGrade).sum());


        //取最大值
        Date maxDate = list.stream().map(c -> c.getCreateDate()).max(Comparator.comparing(Date::getTime)).get();
        System.out.println(format.format(maxDate));

        System.out.println("====================================求和过滤===========================================");


        Integer grade = list.stream().map(e -> e.getGrade()).max(Comparator.comparing(Integer::intValue)).get();
        System.out.println("=================grade:"+grade);
        System.out.println(list.stream().filter(e -> e.getGrade() == grade).mapToLong(Student::getAge).sum());
        System.out.println(list.stream().filter(e -> e.getGrade() == grade).mapToLong(Student::getGrade).sum());
    }

    @Test
    public void sum(){


            List<BigDecimal> list = new ArrayList<>();
            list.add(BigDecimal.valueOf(1.1));
            list.add(BigDecimal.valueOf(1.2));
            list.add(BigDecimal.valueOf(1.3));
            list.add(BigDecimal.valueOf(1.4));

            BigDecimal decimal = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            System.out.println(decimal);

    }


    @Test
    public void bigdecimalAddTest(){
        List<Student> list = new ArrayList<>();
        Student student1 = new Student("lili",10,1,new Date(),new BigDecimal(10));
        Student student2 = new Student("lucy",11,1,new Date(),new BigDecimal(20));
        Student student3 = new Student("lei",12,1,new Date(),new BigDecimal(30));
        Student student4 = new Student("mary",10,2,new Date(),new BigDecimal(40));
        Student student5 = new Student("tom",9,2,new Date(),new BigDecimal(30));
        Student student6 = new Student("jack",12,2,new Date(),new BigDecimal(20));
        Student student7 = new Student("mali",10,3,new Date(),new BigDecimal(20));
        Student student8 = new Student("kk",10,3,new Date(),new BigDecimal(10));
        Student student9 = new Student("kk",20,3,new Date(),new BigDecimal(10));
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);
        list.add(student9);


        Integer grade = list.parallelStream().map(e ->e.getGrade()).min(Comparator.comparing(Integer::intValue)).get();

        BigDecimal money = list.parallelStream().filter(e -> e.getGrade() == grade).map(Student::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(money);


        //顺序
//        List<Student> collect1 = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
//        System.out.println("先排序："+collect1);


/*        MultiValueMap<Integer, Student> listMap = new LinkedMultiValueMap<>();
        for (Student student : collect1) {
            listMap.add(student.getAge(), student);
        }
        System.out.println(listMap);*/

        /**
         *
         * 上面代码中groupingBy有三个参数
         *
         * 第一个参数：分组按照什么分类
         *
         * 第二个参数：分组最后用什么容器保存返回值（默认是HashMap::new）
         *
         * 第三个参数：按照第一个参数分类后，对应的分类的结果如何收集。（Collectors.toList）
         *
         */
        //使用LinkedHashMap是有序的，这样就会保持有序
        Map<Integer, List<Student>> collect2 = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.groupingBy(Student::getAge,LinkedHashMap::new,Collectors.toList()));

        System.out.println("再分组："+collect2);


        Map<Integer, List<Student>> collect = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.groupingBy(Student::getAge));
        System.out.println(collect);

    }


    @Test
    public void test(){
        List<Student> list = new ArrayList<>();
        Student student1 = new Student("lili",10,1,new Date(),new BigDecimal(10));
        Student student2 = new Student("lucy",11,1,new Date(),new BigDecimal(20));
        Student student3 = new Student("lei",12,1,new Date(),new BigDecimal(30));
        Student student4 = new Student("mary",10,2,new Date(),new BigDecimal(40));
        Student student5 = new Student("tom",9,2,new Date(),new BigDecimal(30));
        Student student6 = new Student("jack",12,2,new Date(),new BigDecimal(20));
        Student student7 = new Student("mali",10,3,new Date(),new BigDecimal(20));
        Student student8 = new Student("kk",10,3,new Date(),new BigDecimal(10));
        Student student9 = new Student("kk",20,3,new Date(),new BigDecimal(10));
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);
        list.add(student9);

        //
        Map<String, Student> collect = list.stream().collect(Collectors.toMap(
                student -> String.format("%d:%d:%s", student.getAge(), student.getGrade(), student.getName()), Function.identity()
        ));

        System.out.println(collect);

        Student student = list.parallelStream().sorted(Comparator.comparing(Student::getAge).reversed()).findFirst().get();
        System.out.println(student);
    }
}
