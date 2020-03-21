//package com.bankapp.config;//package com.bankapp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import springfox.documentation.builders.*;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.SecurityConfiguration;
//import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
//@Configuration
//@EnableSwagger2
//public class ConfigurationSwagger extends WebMvcConfigurationSupport {
//
//    @Bean
//    public Docket productApi() {
////        return new Docket(DocumentationType.SWAGGER_2).select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build()
////                .securitySchemes(Arrays.asList(securityScheme()))
////                .securityContexts(Arrays.asList(securityContext()));
//
//         return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(metaData());
//    }
//    private ApiInfo metaData() {
//        return new ApiInfoBuilder()
//                .title("Spring Boot REST API")
//                .description("\"Spring bank application REST API \"")
//                .version("1.0.0")
//                .license("Apache License Version 2.0")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
//                .build();
//    }
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//}
//
////import com.google.common.collect.Lists;
////import io.swagger.annotations.Contact;
////import org.slf4j.Logger;
////import org.slf4j.LoggerFactory;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.context.annotation.Import;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
////import springfox.documentation.builders.PathSelectors;
////import springfox.documentation.service.*;
////import springfox.documentation.spi.DocumentationType;
////import springfox.documentation.spi.service.contexts.SecurityContext;
////import springfox.documentation.spring.web.plugins.Docket;
////import springfox.documentation.swagger2.annotations.EnableSwagger2;
////
////import java.awt.print.Pageable;
////import java.sql.Date;
////import java.util.ArrayList;
////import java.util.List;
////
////import static springfox.documentation.builders.PathSelectors.regex;
////
////@Configuration
////@EnableSwagger2
////@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
////public class ConfigurationSwagger  {
////
////    public static final String AUTHORIZATION_HEADER = "Authorization";
////    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
////
////    @Bean
////    public Docket swaggerSpringfoxDocket() {
////
////
////        List<VendorExtension> vext = new ArrayList<>();
////
////        Docket docket = new Docket(DocumentationType.SWAGGER_2);
////        docket.pathMapping("/");
////        docket.apiInfo(ApiInfo.DEFAULT);
////        docket.forCodeGeneration(true);
////        docket.genericModelSubstitutes(ResponseEntity.class);
////        docket.ignoredParameterTypes(Pageable.class);
////        docket.ignoredParameterTypes(java.sql.Date.class);
////        docket.directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class);
////        docket.directModelSubstitute(java.time.ZonedDateTime.class, Date.class);
////        docket.directModelSubstitute(java.time.LocalDateTime.class, Date.class);
////        docket.securityContexts(Lists.newArrayList(securityContext()));
////        docket.securitySchemes(Lists.newArrayList(apiKey()));
////        docket.useDefaultResponseMessages(false);
////
////        docket = docket.select()
////                .paths(regex(DEFAULT_INCLUDE_PATTERN))
////                .build();
////
////
////        return docket;
////    }
////
////
////    private ApiKey apiKey() {
////        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
////    }
////
////    private SecurityContext securityContext() {
////        return SecurityContext.builder()
////                .securityReferences(defaultAuth())
////                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
////                .build();
////    }
////
////    List<SecurityReference> defaultAuth() {
////        AuthorizationScope authorizationScope
////                = new AuthorizationScope("global", "accessEverything");
////        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
////        authorizationScopes[0] = authorizationScope;
////        return Lists.newArrayList(
////                new SecurityReference("JWT", authorizationScopes));
////    }
////}

package com.bankapp.config;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class ConfigurationSwagger {


    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Lists.newArrayList(securityContext()))
                .apiInfo(apiInfo());
    }

    @Bean
    SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(regex("/api/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Bank")
                .description("Mobile bank application")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("http://swagger.io/terms/")
                .version("1.0.0").contact(new Contact("","", "some@gmail.com"))
                .build();
    }
}