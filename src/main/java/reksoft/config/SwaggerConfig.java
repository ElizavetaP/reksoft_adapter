package reksoft.config;

import com.fasterxml.classmate.TypeResolver;
import reksoft.exception.CustomErrorResponse;
import reksoft.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    private static final String BASE_CONTROLLER_PACKAGE = "reksoft.controller";

    private static final List<ResponseMessage> responsesPOST = Arrays.asList(
            new ResponseMessageBuilder().code(200).message("Success").build(),
            new ResponseMessageBuilder().code(400).message("Client error\n" +
                    " Possible error codes:\n" +
                    " <ul>" +
                    " <li>" + ErrorCode.CODE_INVALID_INPUT_DATA + ": Invalid input data</li>\n" +
                    "</ul>").responseModel(new ModelRef("CustomErrorResponse")).build());



    /**
     * TypeResolver for add our custom error response class to Swagger
     */
    @Autowired
    private final TypeResolver typeResolver = new TypeResolver();


    @Bean
    public Docket productApi10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_CONTROLLER_PACKAGE))
                .paths(regex("/v1.0/.*"))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, responsesPOST)
                .additionalModels(typeResolver.resolve(CustomErrorResponse.class))
                .apiInfo(new ApiInfoBuilder().version("1.0").title("API").description("Documentation API").build());
    }
}
