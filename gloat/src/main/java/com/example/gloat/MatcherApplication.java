package com.example.gloat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatcherApplication.class, args);
		log.info("Hey there! welcome to Adi's Matcher");
		log.info("let's find the best match for your job :)");
		log.info("Please send a Post request with specific job in order to get a match candidate.");
	}
}
