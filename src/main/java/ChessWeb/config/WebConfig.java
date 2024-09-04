package ChessWeb.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(false);

        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/icons/")
                .setCachePeriod(3600)
                .resourceChain(true);
    }


}