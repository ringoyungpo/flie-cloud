package io.github.ringoyungpo.filecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FileCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileCloudApplication.class, args);
	}

}
