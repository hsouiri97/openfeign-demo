package com.example.openfeigndemo;

import com.example.openfeigndemo.client.JSONPlaceHolderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenfeignDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(OpenfeignDemoApplication.class, args);
	}

}
