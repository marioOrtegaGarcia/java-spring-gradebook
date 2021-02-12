package com.mortegagarcia.gradebook.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	// Enables Springfox and Swagger Documentation Generation

	@Bean
	public Docket swaggerConfiguration() {

		SecurityReference securityReference = SecurityReference.builder()
				.reference("basicAuth")
				.scopes(new AuthorizationScope[0])
				.build();

		ArrayList<SecurityReference> reference = new ArrayList<>(1);
		reference.add(securityReference);

		ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
		securityContexts.add(SecurityContext.builder().securityReferences(reference).build());

		ArrayList<SecurityScheme> auth = new ArrayList<>(1);
		auth.add(new BasicAuth("basicAuth"));

		return new Docket(DocumentationType.SWAGGER_2)
				.securitySchemes(auth)
				.securityContexts(securityContexts)
				.select()
				.paths(PathSelectors.ant("/api/**"))
				.apis(RequestHandlerSelectors.basePackage("com.mortegagarcia"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Gradebook REST API",
				"Spring Boot REST Api for a School Grading System",
				"1.0",
				"",
				new Contact("Mario Ortega", "", "mario.j.ortega.garcia@gmail.com"),
				"",
				"",
				Collections.emptyList()
		);
	}

}
