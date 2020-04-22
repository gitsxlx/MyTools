package com.test.demo.newjava;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * java新特性
 *
 * @author WangChao
 * @date 2020/04/08
 */
@Slf4j
@Component
public class NewJavaTest {
    //@Autowired
    //private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void test(){
        /*Lambda 表达式语法格式
        (parameters) -> expression
        或
        (parameters) ->{ statements; }*/

        /*Lambda 表达式的标准格式：
        (参数类型 参数名) -> {
            方法体;
            return 返回值;
        }*/

        /*格式解释：
        小括号中的参数和之前方法的参数写法一样，可以写任意个参数，如果多个参数，要使用逗号隔开。
        -> 是一个运算符，表示指向性动作。
        大括号中的方法体以及 return 返回值的写法和之前方法的大括号中的写法一样。

        Lambda 表达式是函数式编程思想。
        函数式编程：可推导，就是可省略。*/

        var list = new ArrayList<String>();  // var 定义局部变量，自动推断类型，jdk10中增加
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.parallelStream().forEach(System.out::println);  // 流，并行输出，输出方式为方法引用，jdk8中增加
        var list1 = Arrays.asList("a1", "b1", "c1");
        list1.forEach(item-> System.out.println(item));  // lambda表达式输出，jdk8中增加

        System.out.println("**************************");

        /*方法引用共分为四类：
        1.类名::静态方法名
        2.对象::实例方法名
        3.类名::实例方法名
        4.类名::new*/
        var cars = Arrays.asList(new Car("car1", "1"), new Car("car2","2"));
        cars.forEach(Car::create); // 类名::静态方法名
        Car car = new Car();
        cars.forEach(car::update); // 对象::实例方法名
        cars.forEach(Car::query); // 类名::实例方法名

        GreetingService c = Car::new; // 类名::new
        System.out.println(c);
        Supplier<Car> supplier = Car::new; // 类名::new
        System.out.println(supplier);

        var maths = Arrays.asList(4.1, 2.4, 6.5, 1.9, -1.2, -2.6);
        maths.stream().forEach(item -> System.out.println(Math.round(item)));

        System.out.println("**************************");

        /*JDK9的新特性:
            List接口 Set接口 Map接口: 里边增加了一个静态方法of, 可以给集合一次性添加多个元素
            static <E> List<E> of (E ... elements)
            使用前提:
                 当集合中存储的元素的个数已经确定了, 不在改变是使用.
         注意:
            1. of方法只适用于 List接口 Set接口 Map接口, 不适用于接口的实现类
            2. of方法返回值是一个不能改变的集合, 集合不能再使用add, put方法添加元素, 会抛出异常
            3. Set接口和Map接口调用of方法的时候, 不能有重复的元素, 否则会抛出异常*/
        List<String> list2 = List.of("aa","bb","","cc","aa");  // 集合设值, List，Set 和 Map 接口创建集合方法，jdk9中增加
        //List<String> list2 = Arrays.asList("aa","bb","","cc","dd","","ee","cc","","cc","dd","");
        System.out.println(list2.stream().filter(item -> item.isEmpty()).count());  // 流，过滤空的并统计数量
        //System.out.println(list1.parallelStream().filter(item -> item.isEmpty()).count());  // 并行过滤空的并统计数量
        list2.stream().filter(item -> !item.isEmpty()).collect(Collectors.toList()).forEach(System.out::println);  // 过滤不为空的并转化为新的集合并输出
        String filters = list2.stream().filter(item -> !item.isEmpty()).collect(Collectors.joining(","));  // 过滤不为空的并合并成字符串
        System.out.println(filters);

        System.out.println("**************************");

        Random random = new Random();
        //random.ints().limit(5).forEach(System.out::println);  // 限制5个随机int型数据并输出
        random.ints().limit(6).sorted().forEach(System.out::println);  // 限制6个随机int型数据并升序输出

        System.out.println("**************************");

        List<Integer> numbers = Arrays.asList(2,4,3,2,1,3,5);
        List<Integer> seqNumbers = numbers.stream().map(item -> item*item).distinct().collect(Collectors.toList());  // 集合元素平方去重之后再转化为新集合
        seqNumbers.forEach(System.out::println);

        System.out.println("**************************");

        var nums = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = nums.stream().mapToInt((x) -> x).summaryStatistics();  // 统计
        System.out.println("列表中元素数量：" + stats.getCount() + "，列表中最大的数：" + stats.getMax() + ", 列表中最小的数：" + stats.getMin() + ", 列表中数之和：" + stats.getSum() + ", 列表中数的平均数：" + stats.getAverage());

        System.out.println("**************************");

        new Thread(() -> System.out.println("线程id：" + Thread.currentThread().getId() + " 启动了")).start();  // 创建线程

        System.out.println("**************************");

        java.util.Collections.sort(nums,(s1, s2) -> s1.compareTo(s2));  // 升序排序
        System.out.println(nums);

        System.out.println("**************************");

        // 函数式接口，jdk8中增加
        GreetingService greetService = message -> System.out.println("Hello " + message);
        greetService.sayMessage("abc");

        System.out.println("**************************");

        // 默认方法，jdk8中增加；私有方法，jdk9中增加
        GreetingService greeting = new Car();
        greeting.print();

        System.out.println("**************************");

        // Nashorn JavaScript 在jdk8中加入，又在jdk11中移除

        Optional<Integer> a = Optional.ofNullable(2);  // 设置允许为空的值，jdk8中增加
        System.out.println(a.isPresent());  // 判断值是否存在
        Integer b = a.orElse(Integer.valueOf(0));  // 如果存在返回他，否则返回默认值
        System.out.println(b);
        System.out.println(a.get());  // 获取值，值需要存在

        System.out.println("**************************");

        // 日期时间api，jdk8中增加
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        System.out.println(ldt.getYear() + "-" + ldt.getMonth().getValue() + "-" + ldt.getDayOfMonth() + " " + ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond());
        System.out.println(ldt.withDayOfMonth(6).withYear(2018));

        LocalDate ld = ldt.toLocalDate();
        System.out.println(ld);
        LocalDate ld1 = LocalDate.now();
        System.out.println(ld1);
        System.out.println(LocalDate.of(2014, Month.DECEMBER, 12));
        System.out.println(LocalTime.parse("15:16:23"));

        // LocalDateTime转换为Date
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("当前时区：" + zoneId);
        ZonedDateTime zdt = ldt.atZone(zoneId);
        Instant instant = zdt.toInstant();
        Date date = Date.from(instant);
        System.out.println(date);

        // Date转换为LocalDateTime
        Date date1 = new Date();
        Instant instant1 = date1.toInstant();  // 表示时刻，不直接对应年月日信息，需要通过时区转换
        ZoneId zoneId1 = ZoneId.systemDefault();  // 表示时区
        ZonedDateTime zdt1 = instant1.atZone(zoneId1);  // 表示特定时区的日期和时间
        LocalDateTime ldt1 = zdt1.toLocalDateTime();  // 表示与时区无关的日期和时间信息，不直接对应时刻，需要通过时区转换
        System.out.println(ldt1);

        System.out.println("Date转换为LocalDateTime:" + date2LocalDateTime(new Date()));
        System.out.println("LocalDateTime转换为Date:" + localDateTime2Date(LocalDateTime.now()));

        System.out.println("**************************");

        // Base64，jdk8中增加，三种方式：getEncoder()，getMimeEncoder()，getUrlEncoder()
        String encode = base64Encoded("hello?java11");
        System.out.println("base64加密之后：" + encode);
        String decode = base64Decoded(encode);
        System.out.println("base64解密之后：" + decode);

        System.out.println("**************************");

        // 新增进程api，jdk9中增加
          /*ProcessBuilder pb = new ProcessBuilder("notepad.exe");
          try {
              // 打开记事本
              Process p = pb.start();
              System.out.printf("Process ID : %s%n", p.pid());
              String np = "Not Present";
              ProcessHandle.Info info = p.info();
              System.out.printf("Command name : %s%n", info.command().orElse(np));
              System.out.printf("Command line : %s%n", info.commandLine().orElse(np));

              System.out.printf("Start time: %s%n",
                      info.startInstant().map(i -> i.atZone(ZoneId.systemDefault())
                              .toLocalDateTime().toString()).orElse(np));

              System.out.printf("Arguments : %s%n",
                      info.arguments().map(c -> Stream.of(c).collect(
                              Collectors.joining(" "))).orElse(np));

              System.out.printf("User : %s%n", info.user().orElse(np));
          } catch (IOException e) {
              e.printStackTrace();
          }*/

        System.out.println("**************************");

        // jdk11中Http Client API 进行了标准化，通过 CompleteableFutures 提供异步非阻塞
          /*HttpClient client = HttpClient.newHttpClient();
          HttpRequest request = HttpRequest.newBuilder()
                  .uri(URI.create("http://openjdk.java.net/"))
                  .build();
          client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                  .thenApply(HttpResponse::body)
                  .thenAccept(System.out::println)
                  .join();*/
    }

    /**
     * redis测试
     */
    public void redisTest(){
      // 若不存在则设置并返回true，若存在则不设置并返回false
      //redisTemplate.opsForValue().setIfAbsent("a","b", 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * LocalDateTime转换为Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime){
      return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转换为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date){
      return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Base64加密
     * @param encode
     * @return
     */
    public static String base64Encoded(String encode){
      return Base64.getEncoder().encodeToString(encode.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64解密
     * @param decode
     * @return
     */
    public static String base64Decoded(String decode){
      return new String(Base64.getDecoder().decode(decode), StandardCharsets.UTF_8);
    }
}

@FunctionalInterface //函数接口，jdk1.8增加，接口中有且仅有一个需要被重写的抽象方法
interface GreetingService {
    String MYSQL = "MySql_Database";

    void sayMessage(String message);

    // 默认方法，jdk1.8增加
    default void print(){
        System.out.println("我是一辆车!");
        log(MYSQL, "INFO");
    }
    // 静态方法，jdk1.8增加
    static void blowHorn(){
      System.out.println("按喇叭!!!");
    }
    // 私有方法，jdk1.9增加
    private void log(String message, String prefix) {
        getConnection();
        System.out.println("Log Message : " + prefix);
        closeConnection();
    }
    // 私有静态方法，jdk1.9增加
    private static void getConnection() {
      System.out.println("Open Database connection");
    }
    // 私有静态方法，jdk1.9增加
    private static void closeConnection() {
      System.out.println("Close Database connection");
    }

}

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
class Car implements GreetingService{

    private String name;
    private String color;

    public Car(String name) {
    }

    @Override
    public void sayMessage(String message) {

    }

    @Override
    public void print() {
        GreetingService.super.print();
        GreetingService.blowHorn();
        System.out.println("我是一辆汽车!");
    }

    public static void create(Car car) {
        System.out.println("类名::静态方法名 " + car.getName() + car.getColor());
    }

    public String update(Car car) {
        System.out.println("对象::实例方法名 " + car.getName() + car.getColor());
        return car.getName();
    }

    public String query() {
        System.out.println("类名::实例方法名 ");
        return "类名::实例方法名 ";
    }

}
