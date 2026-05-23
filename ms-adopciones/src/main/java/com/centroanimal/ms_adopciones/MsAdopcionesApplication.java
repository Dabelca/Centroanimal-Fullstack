package com.centroanimal.ms_adopciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsAdopcionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAdopcionesApplication.class, args);
	}

}
