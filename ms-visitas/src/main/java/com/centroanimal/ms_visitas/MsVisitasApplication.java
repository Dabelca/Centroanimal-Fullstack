package com.centroanimal.ms_visitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsVisitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsVisitasApplication.class, args);
	}

}
