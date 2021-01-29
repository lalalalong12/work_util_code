package com.ywltest.springdemo.service;

import com.ywltest.springdemo.SpringdemoApplicationTests;
import com.ywltest.springdemo.domain.dto.DataSendDto;
import com.ywltest.springdemo.drools.Person;
import com.ywltest.springdemo.kafka.KafkaProducer;



import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * <p>
 * ehcache缓存测试
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-16 16:58
 */
@Slf4j
public class UserServiceTest extends SpringdemoApplicationTests {

//    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    KafkaProducer kafkaProducer;


    @Test
    public void sendMessage(){

        DataSendDto dataSendDto = new DataSendDto();
        dataSendDto.setTopic("xxx-yyyy");
        dataSendDto.setContent("1111111111111111");
        kafkaProducer.send(dataSendDto);

    }
    private static KieContainer container = null;
    private KieSession statefulKieSession = null;

    /**
     * Title ruleStringTest
     * Param []
     * Return void
     * Exception
     * Description 基于字符串
     * Author 唐磊
     * Date 2019-07-06 14:27
     */
    @Test
    public void ruleStringTest() throws Exception {

        String myRule = "import com.ywltest.springdemo.drools.Person\n" +
                "\n" +
                "dialect  \"mvel\"\n" +
                "\n" +
                "rule \"age\"\n" +
                "    when\n" +
                "        $person : Person(age<16 || age>50)\n" +
                "    then\n" +
                "        System.out.println(\"这个人的年龄不符合要求！（基于动态加载）\");\n" +
                "end\n";

        KieHelper helper = new KieHelper();

        helper.addContent(myRule, ResourceType.DRL);

        KieSession ksession = helper.build().newKieSession();

        Person person = new Person();

        person.setAge(12);
        person.setName("Test");

        ksession.insert(person);

        ksession.fireAllRules();

        ksession.dispose();
    }

    @Test
    public void test() {

        KieServices kieServices = KieServices.Factory.get();
        container = kieServices.getKieClasspathContainer();
        statefulKieSession = container.newKieSession("all-rules");
        Person person = new Person();

        person.setAge(12);
        person.setName("Test");

        statefulKieSession.insert(person);
        statefulKieSession.fireAllRules();
        statefulKieSession.dispose();
    }

}

