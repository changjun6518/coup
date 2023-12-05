package com.jun.coup_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class CoupBackApplication {

	public static void main(String[] args) {
		Temp temp = new Temp();
		temp.game();
//		SpringApplication.run(CoupBackApplication.class, args);
	}

}
