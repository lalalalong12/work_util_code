package common.util;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: work_util_code
 * @Package: com.ywltest.springdemo.mqtt.config
 * @ClassName: SwaggerConfiguration
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2021/3/31 16:42
 * @Version: 1.0
 */
@Configuration
@ConditionalOnClass({EnableSwagger2.class})
@EnableSwagger2
@EnableSwaggerBootstrapUI
//@Profile({"dev", "test"})
public class SwaggerConfiguration {

    public SwaggerConfiguration() {
    }

    @Bean({"defaultSwaggerApi"})
    public Docket defaultSwaggerApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList();
        tokenPar.name("auth").description("携带令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build().globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title("ENC BASE FRAMEWORK RESTFUL APIS").description("#ENC-BASE framework RESTful APIs").termsOfServiceUrl("http://#/").version("1.0").build();
    }
}
