package com.scarretero.superhero.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author SERGIO
 * 
 *         the SwaggerConfig class
 *
 */
@Configuration
public class SwaggerConfig {

	Contact contact = new Contact("Sergio Carretero Peña", "https://github.com/sergiocarreterofisica/",
			"sergiocarreterofisica@gmail.com");

	@SuppressWarnings("rawtypes")
	List<VendorExtension> vendorExtensions = new ArrayList<VendorExtension>();

	ApiInfo apiInfo = new ApiInfo("SuperHeroes Maintenance REST Api", // Name
			"REST Api to manage the maintenance of a list of superheroes", // Description
			"0.0.1-SNAPSHOT", // Version
			"https://github.com/sergiocarreterofisica/capitole_superheroe", // Terms and conditions of the service
			contact, // Contact
			"Apache 2.0", // License
			null, // URL license
			vendorExtensions);

	@Bean
	public Docket apiDocker() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2).protocols(new HashSet<>(Arrays.asList("HTTP")))
				.apiInfo(apiInfo).select().apis(RequestHandlerSelectors.basePackage("com.scarretero.superhero"))
				.paths(PathSelectors.any()).build();

		return docket;

	}

	@Bean
	public LinkDiscoverers discovers() {

		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.of(plugins));

	}

}
