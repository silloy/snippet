package com.tarena.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JSONSerializerMap;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tarena.model.Emp;
import org.apache.commons.lang3.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author SuShaohua
 * @date 2016/8/2 14:52
 * @description
 */
public class demo {
    @Test
    public void strLength() {
        String str = "欢迎使用惠每云诊所【】";
        String strl = "版权归作者所有，任何形式转载请联系作者。\n" +
                "作者：dante(来自豆瓣)\n" +
                "来源：https://www.douban.com/note/574007281/\n" +
                "\n" +
                "我姥爷上了岁数，有一回认真地给我讲，他记着一些事，只分不清是现实还是梦境。我当时听了难受，不久却发现自己也如此，有时甚至连真实经历和道听途说也辨不分【惠每云诊所】";
        System.out.println(strl.length());
        System.out.println(str.length());
        String strExp = "13567899807";
        String regExp = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        String reg = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
        String re = "^[1][34578][0-9]{9}$";
        System.out.println(strExp.matches(re));
    }

    @Test
    public void parseHtml() throws ParserException {
        Parser parser = new Parser("http://www.baidu.com");
        NodeList list = parser.parse(null);
        Node node = list.elementAt(1);
        System.out.println(node);
        NodeList sublist = node.getChildren();
        if (null != sublist) {
            System.out.println(sublist.size());
        }
    }

    @Test
    public void jsoup() {
        Document doc = Jsoup.parse("UTF-8", "http://www.dangdang.com");
        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
        }
    }

    @Test
    public void subString() {
        String date = "2016-09-09 09:09:00";
        System.out.println(date.indexOf("="));
        System.out.println(date.substring(0, date.length()));
        System.out.println(date.substring(0, date.indexOf(" ")));
        List<Integer> intList = Lists.newArrayList(12, 5, 6, 454);
//        String str = String.join(",",
//                intList.ConverterAll<String>(new Converter<Integer, String>(m -> m.toString())).ToArray());
    }

    @Test
    public void time() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        System.out.println(sdf.format(calendar.getTime()) + " 23:59:59");
        //calendar.set(Calendar.YEAR, 2015);
        //calendar.add(Calendar.MONTH, -3);
        System.out.println(sdf.format(calendar.getTime()) + " 00:00:00");
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    @Test
    public void number() {
        Number number = new Integer(0);
        double d = (double) number;
        System.out.println(d);
        Math.ceil(23d);
    }

    @Test
    public void option() {
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }

    @Test
    public void newTime() {
        //借鉴于joda-time
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println(date);
        System.out.println(dateFromClock);

        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(time);
        System.out.println(timeFromClock);

        // Get the local date/time
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);
        System.out.println(datetime);
        System.out.println(datetimeFromClock);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        String endDate = sdf.format(calendar.getTime());
        System.out.println(endDate + " 23:59:59");
        System.out.println(new Date());
    }

    @Test
    public void peek() {
        List<String> list = Lists.newArrayList("one", "two", "three", "four");
        list = list.stream()
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
        System.out.println(list);
        String str = "dff";
        str = Optional.ofNullable(str).orElse("de");
        System.out.println(str);
    }

    @Test
    public void limit() {
        List<Person> persons = new ArrayList();
        for (int i = 1; i <= 10000; i++) {
            Person person = new Person(i, "name" + i);
            persons.add(person);
        }
        List<String> personList2 = persons.stream().
                map(Person::getName).limit(10).skip(3).collect(Collectors.toList());
        System.out.println(personList2);
    }

    private class Person {
        public int no;
        private String name;

        public Person(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public String getName() {
            //System.out.println(name);
            return name;
        }
    }

    @Test
    public void Null() {
        String str = "";
        if (null == str)
            System.out.println("空字符串");
        else
            System.out.println("非空字符串");
    }

    @Test
    public void current() throws InterruptedException {
        long beginTime = System.currentTimeMillis();
        Thread.sleep(5000l);
        long endTime = System.currentTimeMillis();
        long consumeTime = endTime - beginTime;
        System.out.println(String.format("%s consume %d millis", "==========addPatinetRecordFirst", consumeTime));
    }

    @Test
    public void reflect() {
        Method[] fields = Emp.class.getSuperclass().getDeclaredMethods();
        for (Method f : fields) {
            System.out.println(f.getName());
        }
    }

    @Test
    public void testDate() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = sdf.parse(sdf.format(new Date()));
        calendar.setTime(currentDate);
        System.out.println(calendar);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(currentDay);
        System.out.println(Calendar.DAY_OF_MONTH);
        int uear = calendar.get(Calendar.YEAR);
        System.out.println(calendar.get(Calendar.YEAR));

        int age = 4;
        System.out.println(Calendar.getInstance().get(Calendar.YEAR));
    }

    @Test
    public void concat() {
        String a = null;
        String b = "abc";
        String c = "edf";
        System.out.println(a + b + c);
        System.out.println(StringUtils.isNoneBlank(Optional.ofNullable(a + b).orElse("")));
        System.out.println(new Date());
    }

    @Test
    public void flapMap() {
        Optional<String> lastName = Optional.of("last");
        Optional<String> firstName = Optional.of("first");
        Optional<String> fullName =
                lastName.flatMap(ln -> firstName.map(fn -> String.join(" ", ln, fn)));
        System.out.println(fullName.get());
        System.out.println(lastName.get());
    }

    @Test
    public void idcardno() {
        System.out.println("612324199003222017".substring(6, 10));
    }

    @Test
    public void guaua() {
        Map<String, Long> map = ImmutableMap.of("T", 1l);
        System.out.println(map.get("S"));
    }

    public static class User {
        private int id;
        @JSONField(serialize = false)
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void tesonJson() {
        User user = new User();
        user.setId(123);
        user.setName("毛头");

        JSONSerializerMap mapping = new JSONSerializerMap();
        mapping.put(User.class, new JavaBeanSerializer(User.class, Collections.singletonMap("id", "uid")));

        JSONSerializer serializer = new JSONSerializer(mapping);
        serializer.write(user);
        String jsonString = serializer.toString();

        Assert.assertEquals("{\"uid\":123}", jsonString);
    }

    private static class People {
        int age;
        String name;

        private People(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public void fastJsonUtil() {
        Person person = new Person(22, "Jack");

        ValueFilter valueFilter = new ValueFilter() {
            @Override
            public Object process(Object o, String propertyName, Object propertyValue) {
                if (propertyName.equals("name")) {
                    return new String("Foo");    //返回修改后的属性值对象
                }

                return propertyValue;
            }
        };

        PropertyFilter propertyFilter = new PropertyFilter() {
            @Override
            public boolean apply(Object o, String s, Object o1) {
                return false;
            }
        };


        NameFilter nameFilter = new NameFilter() {
            @Override
            public String process(Object o, String s, Object o1) {
                return null;
            }
        };


        String jsonString = JSON.toJSONString(person, valueFilter);
        System.out.println("jsonString is: " + jsonString);

    }


    public Gson gsonUtil() {
        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes arg0) {
                String name = arg0.getName();
                return "Smith".equals(name);
            }

            @Override
            public boolean shouldSkipClass(Class<?> arg0) {
                // TODO Auto-generated method stub
                return false;
            }
        }).create();
        return gson;
    }


    @Test
    public void json() {
        People people = new People(12, "Smith");
        Gson gson = gsonUtil();
        String p = gson.toJson(people);
        System.out.println(p);

        PropertyFilter filter = (source, name, value) -> {
            if ("Smith".equals(value))
                return false;
            return true;
        };

        String fastPeopleLambda = JSON.toJSONString(people, new PropertyFilter() {
            @Override
            public boolean apply(Object o, String s, Object o1) {
                return false;
            }
        });
        SerializeConfig config = new SerializeConfig();
        JSON.toJSONString(people, SerializeConfig.globalInstance);
        Thread thread = new Thread(() -> {
        });
    }

    public void newGson() {
        Gson g = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性
                .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.
                .setPrettyPrinting() //对json结果格式化.
                .setVersion(1.0)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
                //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
                //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.
                .create();
    }


    @Test
    public void uuid() {
        UUID uuid = UUID.randomUUID();
    }

    @Test
    public void ampTest() {
        String a = null;
        String b = null;
        if (null != a && b.length() > 0) {
            System.out.println("pass");
        }
        Long registerNo = 2l;
        System.out.println(Optional.ofNullable(registerNo).orElse(0l) + 1);
    }

    @Test
    public void buffertoString() {
        System.out.println(new String(new StringBuffer("http://garea.huimei.com/api/his/cdss")).replace("garea", "/v_2_0"));
        System.out.println(Integer.parseInt("009"));
        Double d = (double) 12;
        System.out.println(d);
    }

    @Test
    public void maplong() {
        Map<Long, String> map = Maps.newHashMap();
        map.put(2l, "l");
        System.out.println(map.get(3l));
        Emp emp = null;
        String a = "";
        System.out.println(StringUtils.isNotBlank(
                a + Optional.ofNullable(emp)
        ));
    }

    @Test
    public void test1() {
        Class cache = Integer.class.getDeclaredClasses()[0]; //1
        Field myCache = null; //2
        try {
            myCache = cache.getDeclaredField("cache");
            myCache.setAccessible(true);//3

            Integer[] newCache = (Integer[]) myCache.get(cache); //4
            newCache[132] = newCache[133]; //5

            int a = 2;
            int b = a + a;

            System.out.printf("%d + %d = %d", a, a, b); //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doublevo() {
        Double d = 98d;
        updateDoube(d);
        System.out.println(d);

        Double v1 = 1D;
        Double v2 = null;
        System.out.println("v1 + v2 : " + (v1 + v2));
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        double re = b1.add(b2).doubleValue();
        System.out.println("re: " + re);
    }

    private void updateDoube(Double d) {
        d = 9d;
    }

    @Test
    public void matchReg() {
        String phone = "17071400493";
        String phoneReg = "^[1][34578][0-9]{9}$";
        System.out.println(phone.matches(phoneReg));
        List<String> strList = Lists.newArrayList("a", "b", "c", "d");
        System.out.println(strList.subList(1, 3));

        BigDecimal d1 = BigDecimal.ZERO;
        BigDecimal d2 = BigDecimal.valueOf(3);
        d1.add(d2);
        System.out.println(d1.doubleValue());
    }


    public void main() {
        System.out.println("system.out.pringtln(\"git clone http://git.huimei-inc.com/his/his.git\")");
        System.out.print(Boolean.valueOf(true));
        System.out.print("Boolean: " + Boolean.FALSE);

        com.tarena.test.Person p = new com.tarena.test.Person().custom().setAge(0).setName("dd").build();
    }
}
