package ec.edu.app;

import java.nio.file.Paths;

import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class MvcConfig implements WebMvcConfigurer{

//	private final Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		// TODO Auto-generated method stub
//		WebMvcConfigurer.super.addResourceHandlers(registry);
//		String resourcesPath = Paths.get("uploads").toAbsolutePath().toUri().toString();
//		registry.addResourceHandler("/uploads/**")
//		.addResourceLocations(resourcesPath);
//		log.info("Resource Path: "+resourcesPath);
//	}
	
	
	
}
