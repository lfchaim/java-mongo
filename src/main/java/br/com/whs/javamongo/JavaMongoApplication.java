package br.com.whs.javamongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class JavaMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaMongoApplication.class, args);
	}

}
