package net.java.Training_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "net.java.Training_management.repositories")

public class TrainingManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingManagementApplication.class, args);
	}

}
