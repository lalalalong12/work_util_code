package com.ywltest.springdemo.service;

import com.ywltest.springdemo.SpringdemoApplicationTests;
import com.ywltest.springdemo.domain.dto.DataSendDto;
import com.ywltest.springdemo.service.drools.Person;
import com.ywltest.springdemo.config.kafka.KafkaProducer;



import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.conf.MultithreadEvaluationOption;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;


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

        String myRule = "import com.ywltest.springdemo.service.drools.Person\n" +
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

        String kName = "drools";
        KieServices ks = helper.ks;
//
        helper.ks.newKieBaseConfiguration().setOption(MultithreadEvaluationOption.YES);
        // 创建kiemodule xml对应的class
        KieModuleModel kieModuleModel = helper.ks.newKieModuleModel();
        // 创建KieFileSystem虚拟文件系统
        // 添加具体的KieBase标签
        KieBaseModel kieBaseModel = kieModuleModel.newKieBaseModel(kName).
                // kie fileSystem 中资源文件的文件夹
                        addPackage(kName);
        // 设置流形式读取
        kieBaseModel.setEventProcessingMode(EventProcessingOption.STREAM);

        // <KieBase></KieBase>标签添加KieSession属性
        kieBaseModel.newKieSessionModel(kName);

        // 添加kiemodule.xml文件到虚拟文件系统
        String kieModuleModelXml = kieModuleModel.toXML();

        KieFileSystem kieFileSystem = helper.kfs.writeKModuleXML(kieModuleModelXml);


//        helper.addContent(myRule, ResourceType.DRL).build();

         helper.addContent(myRule, "001").build();
        KieBuilder kieBuilder = helper.ks.newKieBuilder(helper.kfs).buildAll();
        KieSession kieSession = helper.build().newKieSession();
//
//        KieSession ksession = helper.build().newKieSession();

//
//        helper.kfs.delete("src/main/resources/"+"001");
//        System.out.println("delete");

        KieSession drools = helper.getKieContainer().newKieSession();


        Person person = new Person();

        person.setAge(12);
        person.setName("Test");

        kieSession.insert(person);

        kieSession.fireAllRules();

        kieSession.dispose();
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

