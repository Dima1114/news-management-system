package by.news.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//application config class
@Configuration
//scanning all components except @EnableWebMvc
@ComponentScan(basePackages = "by.news"
        , excludeFilters = @ComponentScan.Filter
        (type = FilterType.ANNOTATION, value = EnableWebMvc.class))
public class RootConfig {

    //connect config file for using through Environment
    @Configuration
    @PropertySource("classpath:application.properties")
    static class AppProperties {
    }
}

