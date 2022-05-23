package examples.spmvc.lec9;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
	
	@Bean
	public MessageSource messageSource() {
		
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("label");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
}
