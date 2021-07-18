import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringdemoApplicationTests {

    @Test
    public void contextLoads() {

        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);

        int sum = 0;
        for (int i = 1;i<101;i++){
            sum= sum+i;
//            System.out.println(sum);
        }
        System.out.println(sum);


    }

}
