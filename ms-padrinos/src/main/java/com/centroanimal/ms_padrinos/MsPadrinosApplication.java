package com.centroanimal.ms_padrinos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsPadrinosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPadrinosApplication.class, args);
	}

}
