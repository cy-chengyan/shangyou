package shangyou.api.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfigure {

    @Value("${swagger.host}")
    private String swaggerHost;

    @Bean
    public Docket ProductApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        if (!StringUtils.isEmpty(swaggerHost))
            docket = docket.host(swaggerHost);

        docket = docket.apiInfo(productApiInfo())
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/mp/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("shangyou.api.controller"))
                .build();

        return docket;
    }

    private ApiInfo productApiInfo() {
        ApiInfo apiInfo = new ApiInfo("赏邮接口文档",
                null,
                "1.0.1",
                "http://www.shangyou.com/",
                new Contact("Developer", null, "dev@shangyou.com"),
                "(C)Shangyou, 2018",
                null,
                new ArrayList<VendorExtension>());
        return apiInfo;
    }

}
