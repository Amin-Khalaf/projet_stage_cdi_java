package com.umanteam.dadakar.admin.front;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DadakarFrontAdminApplication.class);
	}

//	@Bean
//	public ResourceBundleMessageSource messageSource() {
//		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
//		rb.setBasenames(new String[] { "validation" });
//		return rb;
//	}
}
