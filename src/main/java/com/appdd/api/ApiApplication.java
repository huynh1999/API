package com.appdd.api;

import com.appdd.api.Controller.GroupUserController;
import com.appdd.api.Model.ThreadDelete;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
		Thread thread=new Thread(new ThreadDelete());
		thread.start();
	}

}
