package com.alkemy.ong;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication(scanBasePackages = {"test"} , exclude = JpaRepositoriesAutoConfiguration.class)
@EnableTransactionManagement

public class OngApplication {

	public static void main(String[] args) {
		SpringApplication.run(OngApplication.class, args);
	}
        
        @Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				.findAndRegisterModules();
	
}

}
