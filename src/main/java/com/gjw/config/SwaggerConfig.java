package com.gjw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //多个分组new多个Docket
    @Bean
    public Docket docket(Environment environment) {
        boolean profiles = environment.acceptsProfiles(Profiles.of("dev"));

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).
                        groupName("Gjw").
                        enable(profiles).
                        select().apis(RequestHandlerSelectors.basePackage("com.gjw.controller")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "SwaggerApi-Gjw", "this is description", "1.0",
                "urn:tos", new Contact("Gjw", "www.baidu.com", "1258046117@qq.com"), "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList());
    }

}
