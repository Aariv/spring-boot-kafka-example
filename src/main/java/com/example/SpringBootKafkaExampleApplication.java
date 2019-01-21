package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKafkaExampleApplication implements CommandLineRunner {
	
	@Autowired
	private KafkaSender kafkaSender;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		for (int i = 0; i < 100; i++) {
			User user = new User("Ariv" + i, "00#" + i);
			kafkaSender.send(user);
		}
	}

}

