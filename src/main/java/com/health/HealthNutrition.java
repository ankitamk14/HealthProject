package com.health;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.health.controller.ControllerHealth;
import com.health.controller.controllerContributer;
import com.health.service.UserService;


@SpringBootApplication
public class HealthNutrition extends org.springframework.boot.web.support.SpringBootServletInitializer implements CommandLineRunner {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(HealthNutrition.class);
		
		
		
	}



	@Autowired
	private UserService userService;

	public static void main(String[] args)  {



		SpringApplication.run(HealthNutrition.class, args);


		new File(ControllerHealth.uploadDirectory).mkdir();
		new File(ControllerHealth.uploadDirectoryConsaltant).mkdir();
		new File(ControllerHealth.uploadDirectorOutLine).mkdir();
		new File(ControllerHealth.uploadDirectorScript).mkdir();
		new File(ControllerHealth.uploadDirectorTimeScript).mkdir();
		new File(ControllerHealth.uploadDirectorVideo).mkdir();

		new File(ControllerHealth.uploadMasterTrainer).mkdir();
		new File(ControllerHealth.uploadMasterTrainerPhoto).mkdir();
		new File(ControllerHealth.uploadQusetion).mkdir();
		new File(controllerContributer.uploadDirectoryCreation).mkdirs();
		new File(controllerContributer.uploadDirectoryCreationScripts).mkdirs();
		new File(controllerContributer.uploadDirectoryCreationVideo).mkdirs();
		new File(ControllerHealth.uploadDirectoryFeedback).mkdirs();


	}
	
	
	
	@Override
	public void run(String... args) throws Exception {

		/*
		 *
		 *
		 * User user1 = new User();
		 *
		 * user1.setFirstName("John"); user1.setLastName("Adams");
		 * user1.setUsername("kishor");
		 * user1.setPassword(SecurityUtility.passwordEncoder().encode("kishor"));
		 * user1.setEmail("JAdams@gmail.com"); Set<UserRole> userRoles = new
		 * HashSet<>();
		 *
		 *
		 *
		 * Role role1= new Role(); role1.setRoleId(1); role1.setName("ROLE_USER");
		 *
		 * userRoles.add(new UserRole(user1, role1));
		 *
		 * userService.createUser(user1, userRoles);
		 *
		 */



	}
}