//
//import influxDb.InfluxDBConnect;
//import org.influxdb.dto.QueryResult;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class InfluxdbDemoApplicationTests {
//
//    @Resource
//    InfluxDBConnect influxDBConnect;
//
//    @Test
//    public void contextLoads() {
//        System.out.println("Test Start");
//    }
//
//    @Test
//    public void testInsert() {
//        Map<String, String> tagsMap = new HashMap<>();
//        Map<String, Object> fieldsMap = new HashMap<>();
//        System.out.println("influxDB start time :" + System.currentTimeMillis());
//        int i = 0;
//        for (; ; ) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            tagsMap.put("user_id", String.valueOf(i % 10));
//            tagsMap.put("url", "http://www.baidu.com");
//            tagsMap.put("service_method", "testInsert" + (i % 5));
//            fieldsMap.put("count", i % 5);
//            influxDBConnect.insert("usage", tagsMap, fieldsMap);
//            i++;
//        }
//    }
//
//    @Test
//    public void testSelect() {
//        QueryResult query = influxDBConnect.query("select * from usage");
//        System.out.println(query.toString());
//
//    }
//
//}
