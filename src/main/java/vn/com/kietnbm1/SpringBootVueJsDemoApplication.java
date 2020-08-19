package vn.com.kietnbm1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootVueJsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVueJsDemoApplication.class, args);
	}

}
