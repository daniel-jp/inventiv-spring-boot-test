package com.inventivtest.dreamCaseApp;

import com.inventivtest.dreamCaseApp.dto.userDTO.RegisterDTO;
import com.inventivtest.dreamCaseApp.entities.user.UserRole;
import com.inventivtest.dreamCaseApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DreamCaseAppApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(DreamCaseAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userService.userRegister(new RegisterDTO(null,"Admin","1234", UserRole.ADMIN));

	}
}
