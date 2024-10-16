package com.liangzc.example;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.math.BigDecimal;
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


        System.out.println(128 >>> 3);
        System.out.println(128 >> 3);


        List list = new LinkedList();

        System.out.println("--------------------------------------");
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));


        String temp = "dsfdsfds#{organName},都是佛挡杀佛的#{resInstName}";
        System.out.println(tempReplace(temp));

    }

    public static String tempReplace(String temp) {
        return temp.replaceAll("#\\{organName}", "优家园").replaceAll("#\\{resInstName}", "lllll");
    }

    @Test
    public void subStringTest() {
        String date = "20220908000000";
        System.out.println(date.substring(0, 8));

        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void forTest() {

        for (int i = 0; i < 10; i++) {
            System.out.println("i当前数：" + i);

            for (int j = 0; j < 10; j++) {
                System.out.println("j当前数：" + j);
                if (j == 5) {
                    System.out.println("=====跳出======：" + j);
                    break;
                }
            }

            System.out.println("-----------------i结束：" + i);
        }
    }


    @Test
    public void replaceTest() {
        String temp = "dsfdsfds#{organName},都是佛挡杀佛的#{resInstName}";
        System.out.println(tempReplace(temp));
    }

    @Test
    public void StingConcatTest() {
        String rootPath = "";
        String rootPath2 = null;
        System.out.println(rootPath.concat("fileName").concat(".xlsx"));
        System.out.println(rootPath2.concat("fileName").concat(".xlsx"));

    }

    @Test
    public void logErrorTest() {
        for (int i = 10; i >= 0; i--) {
            try {
                if (i == 6) {
                    throw new Exception("111111");
                }
                System.out.println(i);
            } catch (Exception e) {
                logger.error("处理数据出错,值为：{}", i, e);
//                continue;
            }
        }

    }

    @Test
    public void sizeTest() {
        System.out.println(Integer.SIZE);
    }

    @Test
    public void fileTest() {

        String fileName = "D:/receive-file/DOWNLOAD_PATH/在线支付查询_20221121155215693199.xlsx";
        File file = new File(fileName);

        System.out.println("文件名：" + fileName);
        System.out.println("后缀下标位置：" + fileName.indexOf("."));
        System.out.println("文件名长度：" + fileName.length());
        System.out.println("文件是否存在:" + file.exists());
        if (!file.exists()) {
            fileName = fileName.substring(0, fileName.indexOf(".")).concat(".xls");
            System.out.println("新文件名：" + fileName);
            file = new File(fileName);
            System.out.println("文件是否存在" + file.exists());
        }


    }

    @Test
    public void addTest() {

        int sum = 0;

        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    @Test
    public void mathTest() {

        //Math.ceil:向上取整
        System.out.println((int) Math.ceil((double) 10 / (double) 3));

        //向下取整
        System.out.println(Math.floor((double) 10 / (double) 3));

    }

    @Test
    public void futureTest() throws InterruptedException {

        CompletableFuture.runAsync(() -> {
            try {
                calcTest();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        TimeUnit.SECONDS.sleep(1);

    }

    private void calcTest() {

        for (int i = 0; i < 10; i++) {
            try {
                int i2 = i % 2;
                System.out.println(i + "对2取模后的值：" + i2);
                if (i2 == 0) {
                    throw new RuntimeException("错误：" + i);
                }
                System.out.println(i);
            } catch (Exception e) {
                logger.error("错误呀！", e);
//                System.out.println("错误呀！,"+e.getMessage());
            }
        }
    }

    @Test
    public void randomTest() {

        System.out.println((int) ((Math.random() * 9 + 1) * 100000));


        System.out.println(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
    }

    @Test
    public void qumo() {

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

}
