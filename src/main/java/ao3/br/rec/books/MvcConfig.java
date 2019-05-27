package ao3.br.rec.books;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
//        registry
//        .addResourceHandler("/**")
//        .addResourceLocations("classpath:/home/jcbf/");    
//        registry
//        .addResourceHandler("*.js")
//        .addResourceLocations("file:/home/jcbf/js/");    
//        registry
//        .addResourceHandler("*.css")
//        .addResourceLocations("file:/home/jcbf/css/");    
    }
}