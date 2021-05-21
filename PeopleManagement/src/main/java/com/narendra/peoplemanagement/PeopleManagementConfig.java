package com.narendra.peoplemanagement;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configures the third partly library related beans
 * @author Narendra Korrapati
 *
 */
@Configuration
public class PeopleManagementConfig {

	/**
	 * ModelMapper is used to convert DTO to Domain object.
	 * @return {@link ModelMapper}
	 */
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("com.narendra"))
				.build();
	}
}
