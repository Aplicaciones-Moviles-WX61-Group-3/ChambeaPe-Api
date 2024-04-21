package com.digitaldark.ChambeaPe_Api;

import com.digitaldark.ChambeaPe_Api.shared.service.impl.EmailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChambeaPeApiApplication {
	@Autowired
	EmailSenderServiceImpl emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(ChambeaPeApiApplication.class, args);
	}

}
