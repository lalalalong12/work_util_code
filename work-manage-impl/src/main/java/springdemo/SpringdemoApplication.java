package springdemo;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableEncryptableProperties
public class SpringdemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringdemoApplication.class, args);
    }

}
