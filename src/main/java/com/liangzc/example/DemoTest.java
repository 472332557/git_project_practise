package com.liangzc.example;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.liangzc.example.em.PayChannelEnum;
import com.liangzc.example.jdk8.lambda.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class DemoTest {

    private static final Logger logger = LoggerFactory.getLogger(DemoTest.class);

    public static void main(String[] args) {


        System.out.println(1 << 30);


        System.out.println(128 >>>3);
        System.out.println(128 >>3);


        List list = new LinkedList();

        System.out.println("--------------------------------------");
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));


        String temp = "dsfdsfds#{organName},都是佛挡杀佛的#{resInstName}";
        System.out.println(tempReplace(temp));

    }

    public static String tempReplace(String temp){
        return temp.replaceAll("#\\{organName}", "优家园").replaceAll("#\\{resInstName}", "lllll");
    }

    @Test
    public void subStringTest(){
        String date = "20220908000000";
        System.out.println(date.substring(0,8));
    }

    @Test
    public void forTest(){

        for (int i = 0; i < 10; i++) {
            System.out.println("当前数："+i);

            if (i == 5){
                System.out.println("=====跳出======："+i);
                return;
            }
            System.out.println("-----------------结束："+i);
        }
    }


    @Test
    public void replaceTest(){
        String temp = "dsfdsfds#{organName},都是佛挡杀佛的#{resInstName}";
        System.out.println(tempReplace(temp));
    }

    @Test
    public void StingConcatTest(){
        String rootPath = "";
        String rootPath2 = null;
        System.out.println(rootPath.concat("fileName").concat(".xlsx"));
        System.out.println(rootPath2.concat("fileName").concat(".xlsx"));

    }

    @Test
    public void logErrorTest(){
        for (int i = 10; i >= 0; i--) {
            try {
                if(i == 6){
                    throw new Exception("111111");
                }
                System.out.println(i);
            }catch (Exception e){
                logger.error("处理数据出错,值为：{}",i,e);
//                continue;
            }
        }

    }

    @Test
    public void sizeTest(){
        System.out.println(Integer.SIZE);
    }

    @Test
    public void fileTest(){

        String fileName = "D:/receive-file/DOWNLOAD_PATH/在线支付查询_20221121155215693199.xlsx";
        File file = new File(fileName);

        System.out.println("文件名："+fileName);
        System.out.println("后缀下标位置："+fileName.indexOf("."));
        System.out.println("文件名长度："+fileName.length());
        System.out.println("文件是否存在:"+file.exists());
        if(!file.exists()){
            fileName = fileName.substring(0, fileName.indexOf(".")).concat(".xls");
            System.out.println("新文件名："+fileName);
            file = new File(fileName);
            System.out.println("文件是否存在"+file.exists());
        }


    }

    @Test
    public void addTest(){

        int sum = 0;

        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    @Test
    public void mathTest(){

        //Math.ceil:向上取整
        System.out.println((int)Math.ceil((double) 10 / (double) 3));

        //向下取整
        System.out.println(Math.floor((double) 10 / (double) 3));

    }

    @Test
    public void futureTest() throws InterruptedException {

            CompletableFuture.runAsync(() -> {
                try {
                    calcTest();
                }catch (Exception e){
                    e.printStackTrace();
                }

            });
        TimeUnit.SECONDS.sleep(1);

    }

    private void calcTest() {

        for (int i = 0; i < 10; i++) {
            try {
                int i2 = i % 2;
                System.out.println(i+"对2取模后的值："+i2);
                if(i2 == 0){
                    throw new RuntimeException("错误："+i);
                }
                System.out.println(i);
            }catch (Exception e){
                logger.error("错误呀！",e);
//                System.out.println("错误呀！,"+e.getMessage());
            }
        }
    }

    @Test
    public void randomTest(){

        System.out.println((int)((Math.random()*9+1)*100000));


        System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS") .format(new Date()));
    }

    @Test
    public void qumo(){

        System.out.println(800 % 500);

    }

    @Test
    public void dateTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = "20230615";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(date));
//        calendar.add(Calendar.DATE,1);
//        Date time = calendar.getTime();

        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String start = dateFormat.format(calendar.getTime());
        System.out.println(start);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String end = dateFormat.format(calendar.getTime());
        System.out.println(end);


//        System.out.println(calendar.get(Calendar.YEAR));
//        System.out.println(calendar.get(Calendar.MONTH)+1);
//        System.out.println(calendar.get(Calendar.DATE));

        System.out.println("---------------------------------------------------");
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        System.out.println(calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        String beginStr = "20230601000000";
        String endStr = "20230630000000";

        System.out.println(beginStr.substring(0, 8).equals(start));
        System.out.println(endStr.substring(0, 8).equals(end));
    }

    @Test
    public void objTest() {
        String resp = "{\"code\":\"0\",\"message\":\"success\",\"data\":{}}";
        System.out.println(resp);
        JSONObject object = JSON.parseObject(resp);
        String code = null, msg = null, result = null;
        if (object.containsKey("code")) {
            code = object.getString("code");
        }
        if (object.containsKey("message")) {
            msg = object.getString("message");
        }
        if (object.containsKey("data")) {
            result = object.getString("data");
        }
        System.out.println(object);

        System.out.println("result:" + result);
        System.out.println(result != null);
        System.out.println(result.length());

        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println(jsonObject);
        System.out.println(jsonObject != null);
        System.out.println(jsonObject.size());

        ApartmentCalcBo apartmentCalcBo = JSON.parseObject(result, ApartmentCalcBo.class);


        System.out.println(apartmentCalcBo);

        System.out.println(apartmentCalcBo.getBillData() != null);

        System.out.println(System.currentTimeMillis());
    }


    @Test
    public void jsonTest() {
        String result = "{\"code\":\"0\",\"msg\":\"\",\"data\":{\"contractChangeDto\":{\"contractId\":11522,\"contractNo\":\"ZGDX-2018-08-0013\",\"totalRentArea\":\"81.6\",\"secondId\":1032,\"secondName\":\"深圳市汇嘉粮食有限公司\",\"secondType\":\"企业客户\",\"secondCredType\":\"营业执照\",\"secondCredNo\":\"000000000\",\"contractResource\":2001,\"startDate\":20180101000000,\"endDate\":20201231000000,\"depositMoney\":1349600,\"preTaxAmount\":1929400,\"afterTaxAmount\":1929400,\"taxAmount\":0,\"firstLease\":612000,\"averagePrice\":0,\"processTimes\":\"2018-12-03 16:02:18\",\"contractDuration\":36,\"resList\":[]}},\"message\":\"\"}";
        JSONObject jsonObject = JSON.parseObject(result);
        Object obj = jsonObject.getJSONObject("data").get("contractChangeDto");
        ContractDetaiDto contractDetaiDto = JSON.parseObject(obj.toString(), ContractDetaiDto.class);

        System.out.println(contractDetaiDto);
    }

    @Test
    public void equalsTest() {

        Long id = 1002L;
        String s = String.valueOf(id);
        String ids = "1002";

        System.out.println(s.equals(ids));

    }

    @Test
    public void numFormat() {

        String fee = "-3287.00";
        BigDecimal bigDecimal = new BigDecimal(fee);
        Long feelong = bigDecimal.longValue();
        System.out.println(bigDecimal.doubleValue());

        Long abs = Math.abs(feelong);

        System.out.println(feelong);
        System.out.println(abs);
        String s = abs.toString();
        System.out.println("string s :" + s);

//        System.out.println(Long.valueOf(fee));

    }

    @Test
    public void bigDecimalTest() {
        BigDecimal fee = new BigDecimal("870968.00");
        BigDecimal taxFee = new BigDecimal("41474.6667");

        System.out.println(fee.subtract(taxFee).setScale(0, BigDecimal.ROUND_HALF_UP).longValue());

        BigDecimal feeNotax = BigDecimal.ZERO;

        for (int i = 0; i < 3; i++) {
            feeNotax = feeNotax.add(new BigDecimal(100.3465));
            System.out.println(feeNotax);
        }

        System.out.println(feeNotax.setScale(0, BigDecimal.ROUND_HALF_UP));

        System.out.println(BigDecimal.valueOf(1000).negate());

        System.out.println(BigDecimal.valueOf(-1000).negate());

        System.out.println("----------------------------------------向上取");
        System.out.println(new BigDecimal(10).divide(BigDecimal.valueOf(3), 0, RoundingMode.UP));
    }

    @Test
    public void calc() {
        System.out.println(12 << 1);
        System.out.println(16 << 1);
        System.out.println((16 << 1) * 0.75);

        /**
         *  10
         *  00001010
         *  00000101 -> >>1
         *  00000010 -> >>2
         */

        System.out.println(10 >> 1);

        System.out.println(10 >> 2);

        System.out.println(20 >> 2);

    }

    @Test
    public void listSub() {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 562; i++) {
            list.add(i);
        }

        List<List<Integer>> subList = new ArrayList<>();

        //按1000个元素拆分
        int subBatch = 1000;

        for (int i = 0; i < list.size(); i += subBatch) {
            int endIndex = Math.min(i + subBatch, list.size());
            List<Integer> subList1 = list.subList(i, endIndex);
            subList.add(subList1);
        }

        for (List<Integer> integers : subList) {
            System.out.println(integers);
        }


        List<List<Integer>> lists = segmentationList(list, 200);
        for (List<Integer> integers : lists) {
            System.out.println(integers);
        }

    }

    @Test
    public void setSub() {

        Set<Integer> list = new HashSet<>();
        for (int i = 0; i < 562; i++) {
            list.add(i);
        }

        List<Set<Integer>> subList = new ArrayList<>();
        Iterator<Integer> iterator = list.iterator();
        //按1000个元素拆分
        int subBatch = 1000;
        while (iterator.hasNext()) {
            Set<Integer> subSet = new HashSet<>();
            for (int i = 0; i < subBatch && iterator.hasNext(); i++) {
                subSet.add(iterator.next());
            }
            subList.add(subSet);
        }
        System.out.println(subList);
        System.out.println("-------------------------------------------------------------------------------");
        List<Set<Integer>> sets = segmentationSet(list, 200);
        for (Set<Integer> set : sets) {
            System.out.println(set);
        }

    }

    @Test
    public void threadTest() {


        new Thread(() -> {

            System.out.println("111111111111111111");
        });


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(() -> {
            System.out.println("222222222222222222");
        });
        executorService.shutdown();

    }

    //大集合拆分为小的集合方法
    private <T> List<List<T>> segmentationList(List<T> list, int batchSize) {
        List<List<T>> subList = new ArrayList<>();

        for (int i = 0; i < list.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, list.size());
            subList.add(list.subList(i, endIndex));
        }
        return subList;
    }


    //大集合拆分为小的集合方法Set集合
    private static <T> List<Set<T>> segmentationSet(Set<T> set, int batchSize) {
        List<Set<T>> subSets = new ArrayList<>();
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            Set<T> subSet = new HashSet<>();
            for (int i = 0; i < batchSize && iterator.hasNext(); i++) {
                subSet.add(iterator.next());
            }
            subSets.add(subSet);
        }
        return subSets;
    }


    @Test
    public void between() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = "2023-09-01 00:00:00";
        String end = "2023-10-31 00:00:00";

        Date startDate = dateFormat.parse(start);
        Date endDate = dateFormat.parse(end);


        System.out.println(DateUtil.betweenMonth(startDate, endDate, false) + 1);

        LocalDate localDateStart = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateEnd = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Period period = Period.between(localDateStart, localDateEnd);
        int diff = period.getMonths() + period.getYears() * 12;
        System.out.println(diff);


        System.out.println(ChronoUnit.MONTHS.between(localDateStart, localDateEnd));

    }

    @Test
    public void betweenTime() {

        String start = "2023-09-01 00:00:00";
        String end = "2023-11-30 23:59:59";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(start, formatter);
        LocalDateTime endDate = LocalDateTime.parse(end, formatter);

        Period period = Period.between(startDate.toLocalDate(), endDate.toLocalDate());
        int diff = period.getMonths() + period.getYears() * 12;

        System.out.println("两个日期之间相差 " + diff + " 个月");


    }

    @Test
    public void date() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = "2023-09-01 00:00:00";
        String end = "2023-10-31 00:00:00";

        Date startDate = dateFormat.parse(start);
        Date endDate = dateFormat.parse(end);

        Date dateTime = DateUtil.beginOfDay(startDate);


        DateTime dateTime1 = DateUtil.endOfMonth(startDate);
        System.out.println("dateTime:" + dateTime);
        System.out.println("dateTime1:" + dateTime1);

        DateTime dateTime2 = DateUtil.beginOfDay(dateTime1);
        System.out.println("dateTime2:" + dateTime2);

        Date date = DateUtils.addDays(dateTime2, 1);
        System.out.println("date:" + date);

        Date dateTime3 = DateUtils.addDays(dateTime2, 1);
        System.out.println("dateTime3:" + dateTime3);

        String beginTime = "2023-09-01 00:00:00";
        String endTime = "2023-09-30 00:00:00";

        System.out.println("------------------------------beginOfDay");
        DateTime beginOfDay = DateUtil.beginOfDay(new Date());
        System.out.println(beginOfDay);
    }

    @Test
    public void numTest() {
        int number = 55556;
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        String formattedNumber = decimalFormat.format(number);
        System.out.println(formattedNumber); // 输出: 0005

        String format = String.format("%04d", number);
        System.out.println(format);

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getStartOfDay(new Date())));
    }


    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }


    @Test
    public void dateUtilTest() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = "2023-09-01 00:00:00";
        String end = "2023-10-31 00:00:00";

        Date startDate = dateFormat.parse(start);
        Date endDate = dateFormat.parse(end);


        long month = DateUtil.betweenMonth(startDate, endDate, Boolean.TRUE);
        System.out.println(month);

        long between = DateUtil.between(startDate, endDate, DateUnit.DAY);
        System.out.println(between);


        System.out.println(DateUtil.beginOfDay(new Date()));

    }

    /**
     * 给定两个值，快速的将其区间内的值添加到一个list集合
     */
    @Test
    public void forAddTest() {

        int start = 1;
        int end = 10;

        List<Integer> list = IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(list);
    }


    /**
     * 用于比较一个对象集合内，字段值是否有相等或者重复的
     */
    @Test
    public void compareTest() {
        List<FeeItemType> feeItemTypeList = new ArrayList<>();
        FeeItemType feeItemType = new FeeItemType("1", "水", "1");
        FeeItemType feeItemType1 = new FeeItemType("2", "物业", "2");
        FeeItemType feeItemType2 = new FeeItemType("3", "电", "1");
        feeItemTypeList.add(feeItemType);
        feeItemTypeList.add(feeItemType1);
        feeItemTypeList.add(feeItemType2);

        for (int i = 0; i < feeItemTypeList.size(); i++) {
            for (int j = i + 1; j < feeItemTypeList.size(); j++) {
                FeeItemType feeItemType3 = feeItemTypeList.get(i);
                FeeItemType feeItemType4 = feeItemTypeList.get(j);
                if (feeItemType3.getType().equals(feeItemType4.getType())) {
                    System.out.println("类型有交叉了");
                    System.out.println(feeItemType3);
                    System.out.println(feeItemType4);
                    break;
                }
            }
        }

    }

    @Test
    public void splitTest() {
        String code = "PLANHOUSE|6002977";
        String[] split = code.split("\\|");
        System.out.println(JSON.toJSONString(split));
        for (String s : split) {
            System.out.println(s);
        }

    }

    @Test
    public void getBeginMonth() {
        Date startDay = DateUtil.beginOfMonth(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDay);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime());
    }

    @Test
    public void getMonth() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String endDate = "2023-11-20";
        Date parse = simpleDateFormat.parse(endDate);

        Date startDate = new Date();

        String end = "2023-11-20 00:00:00";
        String start = "2023-11-10 00:00:00";

        DateTime dateTime = DateUtil.beginOfMonth(parse);
        DateTime dateTime1 = DateUtil.beginOfMonth(startDate);
        System.out.println(dateTime);
        System.out.println(dateTime1);

        System.out.println(Boolean.FALSE.toString());
    }

    @Test
    public void ListToArray() {

        ArrayList<String> strings = Lists.newArrayList("1", "2", "3", "4");
        String[] strings1 = strings.toArray(new String[]{});
        for (String s : strings1) {
            System.out.println(s);
        }
    }

    @Test
    public void setAdd() {
        Set<String> names = new HashSet<>();
        System.out.println(names.add("1"));
        System.out.println(names.add("1"));
    }

    @Test
    public void dateParse() throws ParseException {
        String s = "20121225";
        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = oldFormat.parse(s);

        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result = newFormat.format(date);

        System.out.println(result);  // 输出：2012-12-25

        System.out.println(newFormat.parse(result));


        System.out.println(DateUtil.parse(s, "yyyyMMdd"));


    }

    @Test
    public void stringJoin() {
        StringJoiner stringJoiner = new StringJoiner(",", "{", "}");

        for (int i = 0; i < 5; i++) {
            stringJoiner.add("这是第").add("" + i);
        }

        System.out.println(stringJoiner.toString());

        Set<Integer> nums = new HashSet<>();
        nums.add(1);
        nums.add(2);
        nums.add(1);

        System.out.println(nums);

        System.out.println("-----------------------------------");

        StringJoiner serialNoJoiner = new StringJoiner(",");
        serialNoJoiner.add("11111111111111111111");
        serialNoJoiner.add("\n" + "111111111222222222");
        System.out.println(serialNoJoiner.toString());
        System.out.println(serialNoJoiner.length());
    }

    @Test
    public void hashTest() {
        String key = "111";
        String value = "222";

        String end = key + value;
        System.out.println(end.hashCode());
        System.out.println(end.hashCode());

        end = key + "010" + value;
        System.out.println(end.hashCode());
    }


    @Test
    public void enumTest() {

        Optional<PayChannelEnum> first = Arrays.stream(PayChannelEnum.values()).filter(e -> e.getCode().contains("CCB_PAY")).findFirst();
        System.out.println(first.isPresent());
        System.out.println(first.get().getCode());

        System.out.println("-------------------------------------------------------------------");
        Optional<PayChannelEnum> channelEnumOptional = Arrays.stream(PayChannelEnum.values()).filter(e -> e.getCode().contains("PAY_COMM_UNION")).findFirst();
        System.out.println(channelEnumOptional.isPresent());
        System.out.println(channelEnumOptional);
        PayChannelEnum payChannelEnum = channelEnumOptional.get();
        System.out.println(payChannelEnum);
    }

    @Test
    public void joinTest() {
        String[] words = {"Java", "Python", "C++", "JavaScript", "TypeScript", "Kotlin", "Swift", "Go", "Rust", "Ruby"};
        String prefix = "【";
        String suffix = "】";
        StringJoiner sj = new StringJoiner(", ", prefix, suffix);
        int maxLength = 50; // 最大长度阈值
        StringBuilder currentLine = new StringBuilder(); // 用于构建当前行的字符串
        System.out.println(sj.toString());
        System.out.println("是否相等：" + sj.toString().equals(prefix + suffix));

        for (String word : words) {
            // 检查如果当前行加上下一个单词超过最大长度，则先换行
            if (currentLine.length() + word.length() > maxLength) {
                sj.add(currentLine.toString()).add("\n"); // 将当前行添加到StringJoiner
                currentLine = new StringBuilder(); // 重置当前行
            }
            // 如果当前行不为空，添加逗号和空格作为分隔符
            if (currentLine.length() > 0) {
                currentLine.append(", ");
            }
            currentLine.append(word); // 添加单词到当前行
        }
        // 循环结束后，添加最后一行（如果有）
        if (currentLine.length() > 0) {
            sj.add(currentLine.toString());
        }

        // 使用换行符连接每一行
//        String result = sj.toString().replace(", ", "\n");
        System.out.println(sj.toString());
        System.out.println("是否相等2：" + sj.toString().equals(prefix + suffix));
    }

    @Test
    public void beginEnd() {
        DateTime dateTime = DateUtil.beginOfMonth(new Date());

        DateTime dateTime1 = DateUtil.endOfMonth(new Date());

        DateTime dateTime2 = DateUtil.beginOfDay(dateTime1);
    }

    @Test
    public void patternTest() {
        /**
         * <img 表示字符串以<img开头。
         * .*? 表示非贪婪模式下的任意字符序列。
         * > 表示字符串以>结尾。
         * 然后，使用Pattern和Matcher类来查找与此正则表达式匹配的子字符串。matcher.find()方法用于查找下一个匹配项，如果找到，matcher.group()方法将返回匹配的字符串。
         */
        String input = "这是一段文本，包含一个图片标签<img src=\"image.jpg\" />和其他内容。";
        Pattern pattern = Pattern.compile("<img.*?>");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
        }


        System.out.println("--------------------------找到src------------------");
        /**
         * 找到src标签
         */
        String html = "<img style=\"max-width: 150px;max-height: 60px;margin-bottom:8px;display:block;\" src=\"${logoImage}\">";
        String pattern1 = "src=\"([^\"]*)\"";
        Pattern r = Pattern.compile(pattern1);
        Matcher m = r.matcher(html);
        if (m.find()) {
            System.out.println("Found src: " + m.group(1));
        } else {
            System.out.println("No src found");
        }


        System.out.println("--------------------------找到并替换src------------------");
        /**
         * 找到src并替换
         */
        String html2 = "<img style=\"max-width: 150px;max-height: 60px;margin-bottom:8px;display:block;\" src=\"https://bop-charge.oss-cn-shenzhen.aliyuncs.com/0/receiptlogo/5f95f7189df1484c8603feee9f3286dc20220412.png\">";
        String newSrc = "newImage.jpg";
        String pattern2 = "src=\"([^\"]*)\"";
        Pattern r2 = Pattern.compile(pattern2);
        Matcher m2 = r2.matcher(html2);
        if (m2.find()) {
            String oldSrc = m2.group(1);
            String replacedHtml = html2.replace(oldSrc, newSrc);
            System.out.println(replacedHtml);
        } else {
            System.out.println("No src found");
        }


        System.out.println("--------------------------找到并替换第一个src------------------");
        /**
         * 找到并替换第一个src
         */
        String html3 = "<div class=\"printArea\" style=\"margin-top: 10px;height:auto;position: relative;\">\n" +
                "    <div class=\"container-fluid receipt-box\">\n" +
                "        <div class=\"TopHead\">\n" +
                "            <div class=\"receipt-title\" style=\"height: 80px;\">\n" +
                "                <div class=\"receipt-left\">\n" +
                "                    <div class=\"eleproLogo\">\n" +
                "                        <!-- <img style=\"max-width: 150px;max-height: 60px;margin-bottom:8px;display:block;\"\n" +
                "                            src=\"https://bop-charge.oss-cn-shenzhen.aliyuncs.com/0/receiptlogo/5f95f7189df1484c8603feee9f3286dc20220412.png\"> -->\n" +
                "                    </div>\n" +
                "                    <div>\n" +
                "                        <span>收款日期：<span class=\"collectionTime\">${collectionTime}</span></span>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"elereceipt-center\">\n" +
                "                    <div class=\"elereceipt-mode\" style=\"margin-top: -12px;line-height: 28px;\"> ${projectName}电子收据</div>\n" +
                "                    <div style=\"margin-top:20px\">\n" +
                "                        <!-- <span>分享时间: <span class=\"sharingTime\">${shareTime}</span></span> -->\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"receipt-right\" style=\"right:10px;\">\n" +
                "                    <div class=\"eleNumber ff3333\">No.<span class=\"ff3333\">${no}</span><span\n" +
                "                            class=\"pipelineNum ff3333\"></span>\n" +
                "                    </div>\n" +
                "                    <div style=\"text-align:right;margin-bottom:-2px\">\n" +
                "                        <span>打印时间：<span class=\"printTime\">${printTime}</span></span>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <table class=\"table-box receipt-table\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "            <thead class=\"text-center ellipsis\">\n" +
                "                <tr>\n" +
                "                    <td>${multi}</td>\n" +
                "                    <td class=\"roomName\">${buildFloorRoom}</td>\n" +
                "                    <td class=\"w-115\">客户名称</td>\n" +
                "                    <td colspan=\"4\" class=\"customerName\">${customerName}</td>\n" +
                "                    <td>托收账号</td>\n" +
                "                    <td colspan=\"2\" class=\"pay_cardNo\"></td>\n" +
                "                    <td style=\"width:70px\">面积(m²)</td>\n" +
                "                    <td class=\"area\" style=\"width:70px\">${buildArea}</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>收款方式</td>\n" +
                "                    <td class=\"payType\">${payType}</td>\n" +
                "                    <td class=\"w-115\">开立时间</td>\n" +
                "                    <td colspan=\"4\" class=\"pay_cardNo\">${issuerTime}</td>\n" +
                "                    <td>开立人</td>\n" +
                "                    <td class=\"area\" colspan=\"2\">${issuer}</td>\n" +
                "                    <td style=\"width:60px\"></td>\n" +
                "                    <td style=\"width:60px\"></td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td rowspan=\"2\" class=\"w-115\">收费项目</td>\n" +
                "                    <td rowspan=\"2\">收费时段</td>\n" +
                "                    <td rowspan=\"2\">计费用量</td>\n" +
                "                    <td colspan=\"3\">单价</td>\n" +
                "                    <td rowspan=\"2\">实收本月</td>\n" +
                "                    <td rowspan=\"2\">实收往欠</td>\n" +
                "                    <td rowspan=\"2\">预收下月</td>\n" +
                "                    <td rowspan=\"2\">本次实收</td>\n" +
                "                    <td rowspan=\"2\">上次读数</td>\n" +
                "                    <td rowspan=\"2\">本次读数</td>\n" +
                "                </tr>\n" +
                "                <tr class=\"jt\">\n" +
                "                    <td>阶梯1</td>\n" +
                "                    <td>阶梯2</td>\n" +
                "                    <td>阶梯3</td>\n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody class=\"text-center ellipsis\">\n" +
                "                ${能耗缴费}\n" +
                "                ${非能耗缴费}\n" +
                "            </tbody>\n" +
                "            <tbody class=\"completetb\">\n" +
                "                <tr class=\"hidden splitTips\" style=\"display:none\"></tr>\n" +
                "                <tr class=\"fw_b\">\n" +
                "                    <td class=\"text-center\">实收总计</td>\n" +
                "                    <td colspan=\"9\" style=\"text-align:left\">大写：<span>${totalAmountDigitToChinese}</span></td>\n" +
                "                    <td colspan=\"2\" style=\"text-align:left\">（小写）:￥<span class=\"yingshou_zongji\">${totalAmount}</span></td>\n" +
                "                </tr>\n" +
                "                <tr style=\"height:60px\">\n" +
                "                    <td class=\"text-center\">说明</td>\n" +
                "                    <td colspan=\"11\" class=\"subRemark\">\n" +
                "                        <span></span>\n" +
                "                        <span class=\"explainRemark\" style=\"word-break:break-all;text-align:left\">${templateComment}</span>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "        <div style=\"margin-top: 10px;text-align: left\">\n" +
                "            <span>防伪代码:</span>\n" +
                "            <span class=\"antiCode\">\n" +
                "                $1$/Zh1wd47$v3MOsVe83yaTA.m/4y/5B/\n" +
                "            </span>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"receipt-seal\">\n" +
                "        <!-- <img src=\"./电子单据_files/a60d12c0ae34823c044f4d59dc81507e.png\" alt=\"印章logo\"\n" +
                "            style=\"max-width:40mm;max-height: 40mm;\"> -->\n" +
                "        ${sealImage}\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <div style=\"text-align: center;position: relative\">\n" +
                "            <!-- <h2><span style=\"display: block\">限额伍仟元整，超出无效</span></h2> -->\n" +
                "            <div style=\"top:-20px;right: 50px;position: absolute\">\n" +
                "                <img class=\"qrcodeImg\"\n" +
                "                    src=\"${checkQrImage}\"\n" +
                "                    style=\"width:60px;height:60px\">\n" +
                "                <div style=\"text-align:center\">物业校验收据</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\t${invalidBegin}\n" +
                "</div>";
        String newSrc3 = "newImage3.jpg";

        String pattern3 = "src=\"([^\"]*)\"";
        Pattern r3 = Pattern.compile(pattern3);
        Matcher m3 = r3.matcher(html3);
        if (m3.find()) {
            String oldSrc = m3.group(1);
            String replacedHtml = html3.replaceFirst(oldSrc, newSrc3);
            System.out.println(replacedHtml);
        } else {
            System.out.println("No src found");
        }
    }

    @Test
    public void dateCompareToTest() {
        Date now = new Date();

        //当前时间加一小时
        Date after = new Date(System.currentTimeMillis() + 60 * 60 * 1000);

        System.out.println(after.compareTo(now));

    }

    @Test
    public void startTest() {

        String img = "${wechatOfficialAccountImage}";

        System.out.println(img.startsWith("$"));
    }

    @Test
    public void listAddTest() {

        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setName("1");
        student1.setAge(1);
        student1.setGrade(1);
        student1.setCreateDate(new Date());
        student1.setMoney(BigDecimal.ONE);
        Student student2 = new Student();
        student2.setName("2");
        student2.setAge(2);
        student2.setCreateDate(new Date());
        student2.setMoney(BigDecimal.TEN);

        students.add(student1);
        students.add(student2);

        Map<Integer, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getGrade));
        for (Map.Entry<Integer, List<Student>> entry : collect.entrySet()) {
            System.out.println(entry);
        }

        System.out.println(students.size());
        System.out.println(students.isEmpty());
    }

    @Test
    public void commitTest() {

        System.out.println("-------------------commit 1");

        System.out.println("---------------------commit 2");
    }

    @Test
    public void testOcrEmsWayBill() {
        String upload1 = "https://bop-core-oss.itianding.com/0/default/190bd7a8968648e087b6a3b749b3db0f20240308.jpg";
        String upload2 = "https://bop-core-oss.itianding.com/0/default/5512f3402ab746ecacedabb2add1614620240308.jpg";
        String upload3 = "https://bop-core-oss.itianding.com/0/default/d72b418d99d64b26af01083c0f8c07b320240308.jpg";


        String upload4 = "https://bop-core-oss.itianding.com/0/default/1c987a6d6071410f89b905df1e8eafb120240417.jpg";
        String upload5 = "https://bop-core-oss.itianding.com/0/default/94e3b0934e3e48c49f70958e7617985720240417.jpg";

        String upload6 = "https://bop-core-oss.itianding.com/0/default/45f5c3caa5854fb7b266e9c366cc8b1020240422.jpg";
        String upload7 = "https://bop-core-oss.itianding.com/0/default/da42259f002a42d088236b8f92bcca8b20240422.jpg";
        long timestamp = System.currentTimeMillis() / 1000;
        HttpResponse execute = HttpRequest.post("https://api.regenai.com/v1/docs/fetch")
                .contentType("multipart/form-data")
                .form("app_key", "wh0jn52vxryk3vvh")
                .form("token", DigestUtils.md5DigestAsHex(String.format("%s+%d+%s", "wh0jn52vxryk3vvh", timestamp, "4ppnjxl9aoez4r6m1dana5mk6mphvw7sao0bidmm")
                        .getBytes(StandardCharsets.UTF_8)))
                .form("timestamp", timestamp)
                .form("image_url", upload7) //pdf 图片都可以识别
                .execute();
        execute.close();
        log.info("ocr识别：{}", execute.body());
        String body = execute.body();
        JSONObject jsonObject = JSON.parseObject(body);
        int result = jsonObject.getIntValue("result");
        System.out.println(result);
        if (jsonObject.getIntValue("result") != 1) {
            log.error("上传EMS面单，调用运单号识别接口出错！");
        }

        JSONObject jsonObject1 = jsonObject.getJSONObject("response").getJSONObject("data").getJSONArray("identify_results").getJSONObject(0)
                .getJSONObject("details");
        System.out.println(jsonObject1);
        String expressWaybillNumber = jsonObject1.getString("expresswaybillnumber");
        System.out.println(expressWaybillNumber);

        String Recipientname = jsonObject1.getString("Recipientname");
        System.out.println(Recipientname);
    }


    @Test
    public void dateBeginTest() {

        Date date = DateUtil.date();
        Date start = DateUtil.yesterday();
        Date end = DateUtil.tomorrow();

        boolean isIn = DateUtil.isIn(date, start, end);
        System.out.println(isIn);  // 输出 true

    }

    @Test
    public void dateParseTest() {

        String date = "202305";
        DateTime dateTime = DateUtil.parse(date, "yyyyMM");
        DateTime beginOfDay = DateUtil.beginOfDay(dateTime);
        System.out.println(beginOfDay);

        System.out.println("--------commit 11111");

        Student student = new Student();
        System.out.println(student == null);

    }

    @Test
    public void executeTest() throws InterruptedException {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.schedule(() -> {
            System.out.println("延迟10秒执行！");
        }, 10, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            int finalI = i;
            scheduledExecutorService.submit(() -> {
                System.out.println("Executing task " + finalI);
            });
        }

        // 关闭线程池并等待所有任务完成
        scheduledExecutorService.shutdown();
//        scheduledExecutorService.awaitTermination(15, TimeUnit.SECONDS);
    }

    @Test
    public void splitStream(){
        // 假设这是你的集合
        List<String> inputList = Arrays.asList("PUBLICAREA|603840", "PRIVATEAREA|603841", "PUBLICAREA|603842");

        // 使用 Stream API 处理集合
        Set<String> types = inputList.stream()
                .map(item -> item.split("\\|")[0]) // 提取 type
                .collect(Collectors.toSet()); // 收集到 Set

        List<String> ids = inputList.stream()
                .map(item -> item.split("\\|")[1]) // 提取 id
                .collect(Collectors.toList()); // 收集到 List

        // 打印结果
        System.out.println("Types: " + types); // 输出: Types: [PUBLICAREA, PRIVATEAREA, PUBLICAREA]
        System.out.println("IDs: " + ids); // 输出: IDs: [603840, 603841, 603842]
    }

    @Test
    public void jsonGet() throws IOException {

        String json = "{\"accountName\":\"\",\"accountNo\":\"\",\"advanceType\":\"NORMAL\",\"amount\":155.71,\"batchNo\":\"12241024229118094496\",\"businessCode\":100101,\"chargeBusinessType\":\"NORMAL\",\"chargeChannel\":\"TIANDING_MINI_WECHAT\",\"chargeEmployeeId\":0,\"chargeObjectId\":61517,\"chargeObjectName\":\"康郡19栋-19-1702\",\"chargeObjectType\":\"PLANHOUSE\",\"completeTime\":1729727175000,\"contractId\":0,\"contractName\":\"\",\"contractNo\":\"\",\"currency\":\"CNY\",\"errorMessage\":\"交易成功\",\"expireTime\":1729813569000,\"externalSerialNo\":\"1324102422910158793664506\",\"externalTradeNo\":\"83620241024345066265\",\"isAnnualPayment\":false,\"masterOwnerId\":37943,\"payChannel\":\"PAY_SUIXINGFU\",\"payOrderDetailList\":[{\"isPenal\":true,\"orderId\":1849235923150073924,\"orderLineAmount\":0.05,\"orderLineId\":1849235923150073925,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1778614417391383335,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922600620101,\"orderLineAmount\":0.07,\"orderLineId\":1849235922600620102,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1793087998972162616,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922667728962,\"orderLineAmount\":0.20,\"orderLineId\":1849235922667728963,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922462208067,\"orderLineAmount\":0.10,\"orderLineId\":1849235922466402382,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923494006853,\"orderLineAmount\":0.11,\"orderLineId\":1849235923494006854,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1762998678781457088,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922642563137,\"orderLineAmount\":0.53,\"orderLineId\":1849235922646757465,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923061993550,\"orderLineAmount\":0.01,\"orderLineId\":1849235923061993551,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1797826590562873607,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922449625155,\"orderLineAmount\":0.19,\"orderLineId\":1849235922453819400,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923041022028,\"orderLineAmount\":0.49,\"orderLineId\":1849235923045216311,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1744718754903781917,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923166851138,\"orderLineAmount\":0.10,\"orderLineId\":1849235923171045403,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922990690371,\"orderLineAmount\":0.05,\"orderLineId\":1849235922990690372,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1704038400000,\"relationOrderId\":1735472301702410420,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923401732171,\"orderLineAmount\":0.47,\"orderLineId\":1849235923401732172,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1778614423410209343,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922583842892,\"orderLineAmount\":0.10,\"orderLineId\":1849235922583842893,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1735482299119596189,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922885832783,\"orderLineAmount\":0.47,\"orderLineId\":1849235922885832784,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1806233511313567833,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923179434070,\"orderLineAmount\":0.48,\"orderLineId\":1849235923183628385,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1778614423410209343,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922860666967,\"orderLineAmount\":0.46,\"orderLineId\":1849235922864861237,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1706716800000,\"relationOrderId\":1735463554162651178,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922994884695,\"orderLineAmount\":0.15,\"orderLineId\":1849235922999078922,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1744925881660371163,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923322040405,\"orderLineAmount\":0.14,\"orderLineId\":1849235923322040406,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1744925881660371163,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922525122642,\"orderLineAmount\":0.10,\"orderLineId\":1849235922529316878,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1778614423410209343,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922592231507,\"orderLineAmount\":0.14,\"orderLineId\":1849235922596425792,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1793090352014454972,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923204599888,\"orderLineAmount\":0.05,\"orderLineId\":1849235923204599889,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923355594833,\"orderLineAmount\":0.33,\"orderLineId\":1849235923359789095,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1793088005364282135,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922554482782,\"orderLineAmount\":0.69,\"orderLineId\":1849235922558677058,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1806233511313567833,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922957135966,\"orderLineAmount\":0.33,\"orderLineId\":1849235922961330198,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1704038400000,\"relationOrderId\":1735463554162651178,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922739032145,\"orderLineAmount\":0.10,\"orderLineId\":1849235922743226377,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1735482299119596189,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922483179613,\"orderLineAmount\":0.08,\"orderLineId\":1849235922483179614,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1744915650951803004,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922646757466,\"orderLineAmount\":0.60,\"orderLineId\":1849235922650951711,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1762006384662049231,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922550288475,\"orderLineAmount\":0.43,\"orderLineId\":1849235922550288476,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1735463554162651178,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923087159384,\"orderLineAmount\":0.48,\"orderLineId\":1849235923087159385,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1744718754903781917,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922411876440,\"orderLineAmount\":0.46,\"orderLineId\":1849235922416070674,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1744718754903781917,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923410120789,\"orderLineAmount\":0.07,\"orderLineId\":1849235923414315020,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1735472301702410420,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922831306834,\"orderLineAmount\":0.62,\"orderLineId\":1849235922831306835,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1762006384662049231,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922852278354,\"orderLineAmount\":0.40,\"orderLineId\":1849235922856472624,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1762006384662049231,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922684506196,\"orderLineAmount\":0.14,\"orderLineId\":1849235922688700460,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1735478759835857410,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923124908130,\"orderLineAmount\":0.11,\"orderLineId\":1849235923129102350,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1762998678781457088,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922722254946,\"orderLineAmount\":0.52,\"orderLineId\":1849235922722254947,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922437042272,\"orderLineAmount\":0.11,\"orderLineId\":1849235922441236553,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1762998678781457088,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922726449249,\"orderLineAmount\":0.10,\"orderLineId\":1849235922730643493,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1793090352014454972,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923343011939,\"orderLineAmount\":0.01,\"orderLineId\":1849235923347206157,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1775063857186763241,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923418509410,\"orderLineAmount\":0.08,\"orderLineId\":1849235923422703668,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1744915650951803004,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922382516321,\"orderLineAmount\":0.07,\"orderLineId\":1849235922390904905,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1778614417391383335,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922655146080,\"orderLineAmount\":0.46,\"orderLineId\":1849235922659340299,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1812032761402257708,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923431092313,\"orderLineAmount\":0.06,\"orderLineId\":1849235923435286548,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923502395466,\"orderLineAmount\":0.10,\"orderLineId\":1849235923506589717,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1735482299119596189,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923296874560,\"orderLineAmount\":0.01,\"orderLineId\":1849235923301068810,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1797826590562873607,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922873249880,\"orderLineAmount\":0.14,\"orderLineId\":1849235922873249881,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1778617627946545620,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923036827743,\"orderLineAmount\":0.03,\"orderLineId\":1849235923041022027,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1775063857186763241,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923393343571,\"orderLineAmount\":0.14,\"orderLineId\":1849235923397537879,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1735478759835857410,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922764197986,\"orderLineAmount\":0.35,\"orderLineId\":1849235922768392224,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1706716800000,\"relationOrderId\":1744718754903781917,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922848084035,\"orderLineAmount\":0.09,\"orderLineId\":1849235922848084036,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1744932342981488943,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922407682133,\"orderLineAmount\":0.62,\"orderLineId\":1849235922407682134,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1762006384662049231,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922487373918,\"orderLineAmount\":0.05,\"orderLineId\":1849235922491568163,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923267514435,\"orderLineAmount\":0.10,\"orderLineId\":1849235923271708705,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1806236352145027664,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922395099224,\"orderLineAmount\":0.11,\"orderLineId\":1849235922395099225,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922697089117,\"orderLineAmount\":0.10,\"orderLineId\":1849235922701283350,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1706716800000,\"relationOrderId\":1744925881660371163,\"type\":\"PENAL\"},{\"isPenal\":false,\"orderId\":1849235924941041759,\"orderLineAmount\":13.52,\"orderLineId\":1849235924949430368,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1849235924941041759,\"type\":\"ADVANCE\"},{\"isPenal\":false,\"orderId\":1847443536618745947,\"orderLineAmount\":4.48,\"orderLineId\":1847443536950096277,\"productId\":1071,\"productName\":\"康郡-公共电费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1847443536618745947,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1847443536618745947,\"orderLineAmount\":2.97,\"orderLineId\":1847443536618745948,\"productId\":1071,\"productName\":\"康郡-公共电费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1847443536618745947,\"type\":\"GENERAL\"},{\"isPenal\":true,\"orderId\":1849235923028439045,\"orderLineAmount\":0.13,\"orderLineId\":1849235923032633410,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1735478759835857410,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922822918149,\"orderLineAmount\":0.11,\"orderLineId\":1849235922827112534,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1762998678781457088,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923154268163,\"orderLineAmount\":0.10,\"orderLineId\":1849235923158462528,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1735482299119596189,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923464646658,\"orderLineAmount\":0.08,\"orderLineId\":1849235923464646659,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1706716800000,\"relationOrderId\":1735472301702410420,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923116519429,\"orderLineAmount\":0.03,\"orderLineId\":1849235923116519430,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923120713742,\"orderLineAmount\":0.01,\"orderLineId\":1849235923124908129,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1775063857186763241,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922529316879,\"orderLineAmount\":0.03,\"orderLineId\":1849235922533511211,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923108130828,\"orderLineAmount\":0.11,\"orderLineId\":1849235923112325191,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922634174477,\"orderLineAmount\":0.49,\"orderLineId\":1849235922638368844,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1744718754903781917,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923456258060,\"orderLineAmount\":0.49,\"orderLineId\":1849235923460452410,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1744718754903781917,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923301068811,\"orderLineAmount\":0.10,\"orderLineId\":1849235923305263182,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923280097288,\"orderLineAmount\":0.09,\"orderLineId\":1849235923284291638,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1744932342981488943,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922709671945,\"orderLineAmount\":0.48,\"orderLineId\":1849235922713866267,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1744718754903781917,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922692894731,\"orderLineAmount\":0.33,\"orderLineId\":1849235922692894732,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1778614423410209343,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922755809289,\"orderLineAmount\":0.08,\"orderLineId\":1849235922760003655,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1744915650951803004,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922499956756,\"orderLineAmount\":0.60,\"orderLineId\":1849235922504151064,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1762006384662049231,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922818723861,\"orderLineAmount\":0.45,\"orderLineId\":1849235922818723862,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1735463554162651178,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923024244759,\"orderLineAmount\":0.07,\"orderLineId\":1849235923028439044,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1768826038852284675,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923439480851,\"orderLineAmount\":0.07,\"orderLineId\":1849235923439480852,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1768826038852284675,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922470596627,\"orderLineAmount\":0.46,\"orderLineId\":1849235922470596628,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1735463554162651178,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923326234640,\"orderLineAmount\":0.48,\"orderLineId\":1849235923330429026,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1778614423410209343,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922659340300,\"orderLineAmount\":0.07,\"orderLineId\":1849235922663534639,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1704038400000,\"relationOrderId\":1735482299119596189,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922424459295,\"orderLineAmount\":0.15,\"orderLineId\":1849235922428653584,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1830435467552649378,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922680311836,\"orderLineAmount\":0.10,\"orderLineId\":1849235922680311837,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922734837789,\"orderLineAmount\":0.01,\"orderLineId\":1849235922734837790,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1775063857186763241,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923212988442,\"orderLineAmount\":0.46,\"orderLineId\":1849235923217182762,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1793088005364282135,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923452063771,\"orderLineAmount\":0.03,\"orderLineId\":1849235923452063772,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923049410587,\"orderLineAmount\":0.53,\"orderLineId\":1849235923049410588,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922458013721,\"orderLineAmount\":0.14,\"orderLineId\":1849235922458013722,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1778617627946545620,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923162656794,\"orderLineAmount\":0.19,\"orderLineId\":1849235923162656795,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922617397287,\"orderLineAmount\":0.03,\"orderLineId\":1849235922621591617,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1775063857186763241,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922705477657,\"orderLineAmount\":0.07,\"orderLineId\":1849235922705477658,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1744915650951803004,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923011661861,\"orderLineAmount\":0.45,\"orderLineId\":1849235923015856188,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1793088005364282135,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922780975141,\"orderLineAmount\":0.05,\"orderLineId\":1849235922785169502,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1793843028352332645,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923380760611,\"orderLineAmount\":0.07,\"orderLineId\":1849235923384954929,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1778614417391383335,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923192016931,\"orderLineAmount\":0.08,\"orderLineId\":1849235923192016932,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1744915650951803004,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922751615008,\"orderLineAmount\":0.08,\"orderLineId\":1849235922755809288,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1808409218898752016,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922629980193,\"orderLineAmount\":0.14,\"orderLineId\":1849235922634174476,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1744925881660371163,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922399293487,\"orderLineAmount\":0.03,\"orderLineId\":1849235922403487809,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1797826590562873607,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922562871343,\"orderLineAmount\":0.14,\"orderLineId\":1849235922567065633,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1735478759835857410,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923141685293,\"orderLineAmount\":0.04,\"orderLineId\":1849235923145879555,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1793843028352332645,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923053604909,\"orderLineAmount\":0.05,\"orderLineId\":1849235923057799232,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1793087998972162616,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922508345369,\"orderLineAmount\":0.38,\"orderLineId\":1849235922508345370,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922445430829,\"orderLineAmount\":0.10,\"orderLineId\":1849235922445430830,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1706716800000,\"relationOrderId\":1735482299119596189,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922877444137,\"orderLineAmount\":0.07,\"orderLineId\":1849235922881638411,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923389149238,\"orderLineAmount\":0.10,\"orderLineId\":1849235923389149239,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923313651767,\"orderLineAmount\":0.10,\"orderLineId\":1849235923317846114,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1778614423410209343,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922978107444,\"orderLineAmount\":0.08,\"orderLineId\":1849235922978107445,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1744915650951803004,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923376566324,\"orderLineAmount\":0.09,\"orderLineId\":1849235923376566325,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1735482299119596189,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923250737204,\"orderLineAmount\":0.03,\"orderLineId\":1849235923250737205,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922936164403,\"orderLineAmount\":0.07,\"orderLineId\":1849235922936164404,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1778614423410209343,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922952941618,\"orderLineAmount\":0.10,\"orderLineId\":1849235922957135965,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1735482299119596189,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922793558065,\"orderLineAmount\":0.07,\"orderLineId\":1849235922797752373,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1768826038852284675,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922789363774,\"orderLineAmount\":0.11,\"orderLineId\":1849235922789363775,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1762998678781457088,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922541899839,\"orderLineAmount\":0.13,\"orderLineId\":1849235922546094088,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1778617627946545620,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923196211262,\"orderLineAmount\":0.46,\"orderLineId\":1849235923200405579,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1735463554162651178,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923510784060,\"orderLineAmount\":0.03,\"orderLineId\":1849235923514978332,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1793843028352332645,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923074576442,\"orderLineAmount\":0.09,\"orderLineId\":1849235923074576443,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1837325919455310212,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923263320123,\"orderLineAmount\":0.03,\"orderLineId\":1849235923263320124,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1775063857186763241,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923468841013,\"orderLineAmount\":0.07,\"orderLineId\":1849235923473035343,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1806236352145027664,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922537705522,\"orderLineAmount\":0.15,\"orderLineId\":1849235922537705523,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1744925881660371163,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922864861238,\"orderLineAmount\":0.10,\"orderLineId\":1849235922869055524,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1778614423410209343,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922948747322,\"orderLineAmount\":0.09,\"orderLineId\":1849235922948747323,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1778617627946545620,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922890027059,\"orderLineAmount\":0.14,\"orderLineId\":1849235922894221338,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1744925881660371163,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923103936556,\"orderLineAmount\":0.06,\"orderLineId\":1849235923108130827,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1706716800000,\"relationOrderId\":1744932342981488943,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922894221339,\"orderLineAmount\":0.13,\"orderLineId\":1849235922898415663,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1735478759835857410,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922915192880,\"orderLineAmount\":0.03,\"orderLineId\":1849235922919387208,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922902609946,\"orderLineAmount\":0.06,\"orderLineId\":1849235922906804273,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1706716800000,\"relationOrderId\":1744915650951803004,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923368177674,\"orderLineAmount\":0.07,\"orderLineId\":1849235923372371994,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1793087998972162616,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923233959978,\"orderLineAmount\":0.09,\"orderLineId\":1849235923233959979,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1744932342981488943,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922969718813,\"orderLineAmount\":0.08,\"orderLineId\":1849235922973913107,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1812034162765361635,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923338817590,\"orderLineAmount\":0.08,\"orderLineId\":1849235923343011938,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1735472301702410420,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923405926434,\"orderLineAmount\":0.15,\"orderLineId\":1849235923410120788,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1744925881660371163,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922743226378,\"orderLineAmount\":0.02,\"orderLineId\":1849235922747420765,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922940358681,\"orderLineAmount\":0.07,\"orderLineId\":1849235922944553015,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1762998678781457088,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923007467529,\"orderLineAmount\":0.52,\"orderLineId\":1849235923011661860,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922491568164,\"orderLineAmount\":0.44,\"orderLineId\":1849235922495762517,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1837325919455310212,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922588037135,\"orderLineAmount\":0.29,\"orderLineId\":1849235922592231506,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1830435467552649378,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922571259916,\"orderLineAmount\":0.07,\"orderLineId\":1849235922571259917,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1735472301702410420,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923175239715,\"orderLineAmount\":0.08,\"orderLineId\":1849235923175239716,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1735472301702410420,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922512539700,\"orderLineAmount\":0.05,\"orderLineId\":1849235922516734004,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1768826038852284675,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923254931473,\"orderLineAmount\":0.45,\"orderLineId\":1849235923259125818,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1735463554162651178,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922432847924,\"orderLineAmount\":0.09,\"orderLineId\":1849235922432847925,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1744932342981488943,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923334623257,\"orderLineAmount\":0.04,\"orderLineId\":1849235923334623258,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1808409218898752016,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922625785911,\"orderLineAmount\":0.13,\"orderLineId\":1849235922625785912,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1735478759835857410,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922474790926,\"orderLineAmount\":0.08,\"orderLineId\":1849235922478985315,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1735472301702410420,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922776780824,\"orderLineAmount\":0.09,\"orderLineId\":1849235922776780825,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1744932342981488943,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922810335274,\"orderLineAmount\":0.10,\"orderLineId\":1849235922814529553,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1704038400000,\"relationOrderId\":1735478759835857410,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923238154269,\"orderLineAmount\":0.07,\"orderLineId\":1849235923242348601,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1768826038852284675,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923481423922,\"orderLineAmount\":0.04,\"orderLineId\":1849235923485618188,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922713866268,\"orderLineAmount\":0.06,\"orderLineId\":1849235922718060599,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922604814349,\"orderLineAmount\":0.02,\"orderLineId\":1849235922609008724,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1797826590562873607,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922768392225,\"orderLineAmount\":0.46,\"orderLineId\":1849235922772586555,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1722441600000,\"relationOrderId\":1735463554162651178,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923485618189,\"orderLineAmount\":0.08,\"orderLineId\":1849235923489812482,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1709222400000,\"relationOrderId\":1744932342981488943,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922671923255,\"orderLineAmount\":0.07,\"orderLineId\":1849235922676117506,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1735472301702410420,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922923581470,\"orderLineAmount\":0.01,\"orderLineId\":1849235922923581471,\"productId\":1067,\"productName\":\"居民排水费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1775063857186763241,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922927775747,\"orderLineAmount\":0.13,\"orderLineId\":1849235922931970113,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1793090352014454972,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922420264983,\"orderLineAmount\":0.14,\"orderLineId\":1849235922420264984,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1706716800000,\"relationOrderId\":1735478759835857410,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922613202993,\"orderLineAmount\":0.10,\"orderLineId\":1849235922613202994,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922575454208,\"orderLineAmount\":0.06,\"orderLineId\":1849235922579648586,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1753264452414764030,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923292680254,\"orderLineAmount\":0.07,\"orderLineId\":1849235923292680255,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1719763200000,\"relationOrderId\":1778614417391383335,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922965524525,\"orderLineAmount\":0.14,\"orderLineId\":1849235922969718812,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923091353635,\"orderLineAmount\":0.02,\"orderLineId\":1849235923095547968,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1714492800000,\"relationOrderId\":1775063857186763241,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922835501077,\"orderLineAmount\":0.62,\"orderLineId\":1849235922839695363,\"productId\":1071,\"productName\":\"公共电费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1762006384662049231,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235923363983420,\"orderLineAmount\":0.20,\"orderLineId\":1849235923363983421,\"productId\":1059,\"productName\":\"居民水费\",\"receivableMonth\":1717171200000,\"relationOrderId\":1764575446046896985,\"type\":\"PENAL\"},{\"isPenal\":true,\"orderId\":1849235922797752374,\"orderLineAmount\":0.07,\"orderLineId\":1849235922801946637,\"productId\":1070,\"productName\":\"公共水费\",\"receivableMonth\":1711900800000,\"relationOrderId\":1768827914725388793,\"type\":\"PENAL\"},{\"isPenal\":false,\"orderId\":1847080441757069994,\"orderLineAmount\":13.05,\"orderLineId\":1847080441757069995,\"productId\":1071,\"productName\":\"康郡-公共电费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1847080441757069994,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1847080441757069994,\"orderLineAmount\":8.25,\"orderLineId\":1847450128034591404,\"productId\":1070,\"productName\":\"康郡-公共水费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1847080441757069994,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1837325919455310212,\"orderLineAmount\":8.36,\"orderLineId\":1837325920172535952,\"productId\":1070,\"productName\":\"康郡-公共水费\",\"receivableMonth\":1725120000000,\"relationOrderId\":1837325919455310212,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1837325919455310212,\"orderLineAmount\":3.15,\"orderLineId\":1837325919455310211,\"productId\":1071,\"productName\":\"康郡-公共电费\",\"receivableMonth\":1725120000000,\"relationOrderId\":1837325919455310212,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1837325919455310212,\"orderLineAmount\":13.31,\"orderLineId\":1837325919967015012,\"productId\":1071,\"productName\":\"康郡-公共电费\",\"receivableMonth\":1725120000000,\"relationOrderId\":1837325919455310212,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1837325919455310212,\"orderLineAmount\":4.55,\"orderLineId\":1837325919967015229,\"productId\":1071,\"productName\":\"康郡-公共电费\",\"receivableMonth\":1725120000000,\"relationOrderId\":1837325919455310212,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1842461020824564530,\"orderLineAmount\":9.15,\"orderLineId\":1842461020824564531,\"productId\":1059,\"productName\":\"康郡-居民水费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1842461020824564530,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1842461020824564530,\"orderLineAmount\":4.75,\"orderLineId\":1842461020824564125,\"productId\":1067,\"productName\":\"康郡-居民排水费\",\"receivableMonth\":1727712000000,\"relationOrderId\":1842461020824564530,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1830435467552649378,\"orderLineAmount\":14.25,\"orderLineId\":1830435467552650029,\"productId\":1067,\"productName\":\"康郡-居民排水费\",\"receivableMonth\":1725120000000,\"relationOrderId\":1830435467552649378,\"type\":\"GENERAL\"},{\"isPenal\":false,\"orderId\":1830435467552649378,\"orderLineAmount\":27.45,\"orderLineId\":1830435467552649377,\"productId\":1059,\"productName\":\"康郡-居民水费\",\"receivableMonth\":1725120000000,\"relationOrderId\":1830435467552649378,\"type\":\"GENERAL\"}],\"paySubChannel\":\"WECHAT\",\"payTime\":1729727175000,\"payUser\":\"\",\"payWay\":\"ONLINE_PAY\",\"payeeUser\":\"\",\"productNames\":\"康郡-公共电费,康郡-公共水费,康郡-居民排水费,康郡-居民水费,居民排水费(违约金),公共水费(违约金),居民水费(违约金),公共电费(违约金)\",\"projectId\":92,\"projectName\":\"福州东方名城\",\"receiptRemark\":\"\",\"remark\":\"\",\"serialNo\":\"13241024229101587936\",\"sourceSerialNo\":\"\",\"state\":\"SUCCESS\",\"submitTime\":1729727166000,\"surplusPayPenal\":true,\"tenantId\":1}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode payOrderDetailList = rootNode.path("payOrderDetailList");

        List<Long> relationOrderIds = new ArrayList<>();

        payOrderDetailList.forEach(orderDetail -> {
            if (orderDetail.path("isPenal").asBoolean()) {
                relationOrderIds.add(orderDetail.path("relationOrderId").asLong());
            }
        });

        // 打印结果
        System.out.println("Relation Order IDs: " + JSON.toJSONString(relationOrderIds));
    }


    class A {
        int id;
        int aField; // 用于关联 B 表

        public A(int id, int aField) {
            this.id = id;
            this.aField = aField;
        }

        @Override
        public String toString() {
            return "A{id=" + id + ", aField=" + aField + "}";
        }
    }

    class B {
        int id;
        int bField; // 用于排序

        public B(int id, int bField) {
            this.id = id;
            this.bField = bField;
        }
    }

    @Test
    public void sortedTest(){
        // 假设 A 表的数据
        List<A> aList = Arrays.asList(
                new A(1, 100),
                new A(2, 200),
                new A(3, 300)
        );

        // 假设 B 表的数据
        List<B> bList = Arrays.asList(
                new B(100, 3),
                new B(200, 1),
                new B(300, 2)
        );

        // 创建一个 Map 来存储 B 表的排序依据
        Map<Integer, Integer> bFieldMap = bList.stream()
                .collect(Collectors.toMap(b -> b.id, b -> b.bField));

        // 对 A 表进行排序，依据是 B 表中 bField 的值
        List<A> sortedAList = aList.stream()
                .sorted(Comparator.comparingInt(a -> bFieldMap.getOrDefault(a.aField, Integer.MAX_VALUE)))
                .collect(Collectors.toList());

        // 打印排序后的 A 表
        sortedAList.forEach(System.out::println);
    }

    @Test
    public void lineFeed(){
        StringJoiner joiner  = new StringJoiner(",");

        joiner.add("常规：ddddd").add("\n");

        joiner.add("合同：aaaaa").add("ccccc").add("\n");

        System.out.printf(joiner.toString());
    }

    @Test
    /**
     * 排序测试方法
     * 该方法使用冒泡排序算法对一个整数数组进行降序排序，并打印排序后的结果
     */
    public void soretdTest(){
        // 初始化待排序的数组
        int[] nums = {5,4,8,10,25,100,54,66,12,88,9};

        // 外层循环控制排序的轮数
        /**
         *
         * 外层循环控制排序的轮数。每一轮排序都会将当前未排序部分的最大值移动到未排序部分的末尾。因此，在第 i 轮排序后，最后 i 个元素已经是有序的，无需再参与比较。
         *
         * (1) 减少不必要的循环
             使用 nums.length - 1 可以避免最后一轮的外层循环，因为此时数组已经完全排序完成。
             使用 nums.length 会导致多进行一次无意义的外层循环，增加代码运行的时间复杂度。
         (2) 逻辑清晰
         冒泡排序的核心思想是通过 n-1 轮操作将数组排序完成。
         使用 nums.length - 1 更符合冒泡排序的逻辑，代码意图更加清晰。

         */
        for (int i = 0; i < nums.length - 1; i++) {
            // 内层循环控制每轮排序的比较和交换操作
            for (int j = 0; j < nums.length - 1 -i; j++) {
                int temp;
                // 如果当前元素小于下一个元素，则交换它们的位置
                // 这里进行降序排序，因此交换条件为当前元素小于下一个元素
                if(nums[j] < nums[j+1]){
                    temp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        // 遍历并打印排序后的数组
        for (int num : nums) {
            System.out.print(num+"\t");
        }
    }

    /**
     * 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，
     * 返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     *
     * 作者：LeetCode
     * 链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2gy9m/
     * 来源：力扣（LeetCode）
     */
    @Test
    public void removeDuplicates() {
//        0,0,1,1,1,2,2,3,3,4
        // TODO 2025/3/7 问题，将重复值的设置为-1，后续通过-1来判断，但是当数组中有-1时，就有问题，设置为啥值，数组中出现这个值，都会有问题，如何解决？
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int[] tempNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tempNums[i] = nums[i];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i+1; j <= nums.length -1; j++) {
                int compare = nums[i];
                if(compare == nums[j]){
                    if(tempNums[j] == -1){continue;}
                    count++;
                    System.out.println("有相同的值："+compare+",index:"+j);
                    tempNums[j] = -1;
                }
            }
        }
        System.out.println(Arrays.toString(tempNums));
        System.out.println("count:"+count);
        int[] newNums = new int[nums.length - count];
        int index =0;
        for (int i = 0; i < tempNums.length; i++) {
           if(tempNums[i] != -1){
               newNums[index] = nums[i];
               index++;
           }
        }
        System.out.println("newNums:"+Arrays.toString(newNums));

        for (int i = 0; i < nums.length-1; i++) {
            if(i < newNums.length){
                nums[i] = newNums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void arraysTest(){
        //多维数组
        /*
        *   二维数组，下边的相当于3行2列
        *   1  2
        *   2  3
        *   3  4
        * */
        int[][] arr = {{1,2},{2,3},{3,4}};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------");
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------");
        //2行3列
        int[][] arrays = new int[2][3];
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                arrays[i][j] = i+j;
            }
        }
        for (int[] ints : arrays) {
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }

    }
}
