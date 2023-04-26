package com.shanghaishark.recipebuddy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.shanghaishark")
public class RecipebuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipebuddyApplication.class, args);
	}
}
