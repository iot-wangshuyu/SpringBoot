package com.shuyu.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/**
     * SpringBootĬ���Ѿ���classpath:/META-INF/resources/��classpath:/META-INF/resources/webjars/ӳ��
     * ���Ը÷�������Ҫ��д�������SpringMVC�У�������Ҫ��д���壨��û�г��ԣ�
     * ��д�÷�����Ҫ extends WebMvcConfigurerAdapter
     * 
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }

    /**
     * ���Զ������飬���籾���ж����test��demo���ֿ���
     * ������ҳ��Ϳ��Կ���Ч���ˣ� 
     *
     */
//    @Bean
//    public Docket testApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("test")
//                .genericModelSubstitutes(DeferredResult.class)
////                .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(true)
//                .pathMapping("/")// base�����յ��ýӿں���pathsƴ����һ��
//                .select()
//                .paths(or(regex("/api/.*")))//���˵Ľӿ�
//                .build()
//                .apiInfo(testApiInfo());
//    }
//
//    @Bean
//    public Docket demoApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("demo")
//                .genericModelSubstitutes(DeferredResult.class)
////              .genericModelSubstitutes(ResponseEntity.class)
//                .useDefaultResponseMessages(false)
//                .forCodeGeneration(false)
//                .pathMapping("/")
//                .select()
//                .paths(or(regex("/demo/.*")))//���˵Ľӿ�
//                .build()
//                .apiInfo(demoApiInfo());
//    }
//
//    private ApiInfo testApiInfo() {
//        return new ApiInfoBuilder()
//            .title("Electronic Health Record(EHR) Platform API")//�����
//            .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//��ϸ����
//            .version("1.0")//�汾
//            .termsOfServiceUrl("NO terms of service")
//            .contact(new Contact("����", "http://blog.csdn.net/catoop", "365384722@qq.com"))//����
//            .license("The Apache License, Version 2.0")
//            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//            .build();
//    }

//    private ApiInfo demoApiInfo() {
//        return new ApiInfoBuilder()
//            .title("Electronic Health Record(EHR) Platform API")//�����
//            .description("EHR Platform's REST API, all the applications could access the Object model data via JSON.")//��ϸ����
//            .version("1.0")//�汾
//            .termsOfServiceUrl("NO terms of service")
//            .contact(new Contact("С��", "http://blog.csdn.net/catoop", "365384722@qq.com"))//����
//            .license("The Apache License, Version 2.0")
//            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
//            .build();
//
////        return apiInfo;
//    }
    
    
    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                if (declaringClass == BasicErrorController.class)// �ų�
                    return false;
                if(declaringClass.isAnnotationPresent(RestController.class)) // ��ע�����
                    return true;
                if(input.isAnnotatedWith(ResponseBody.class)) // ��ע��ķ���
                    return true;
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("SpringBootѧϰ")//�����
            .version("1.0")//�汾
            .build();
    }

}
