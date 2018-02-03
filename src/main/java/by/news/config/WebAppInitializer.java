package by.news.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//class-initializer of web application
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //main config class
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    //web config class
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //application web context
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
