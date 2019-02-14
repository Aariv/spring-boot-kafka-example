package com.example;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKafkaExampleApplication implements CommandLineRunner {
	
	@Autowired
	private KafkaSender<User> kafkaUserProducer;
	
//	@Autowired
//	private KafkaSender<Vote> kafkaVoteProducer;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int numberofRecords = 1000000;
		int batch = 10000;
		for (String string : args) {
			System.out.println("Input: " + string);
			numberofRecords = Integer.valueOf(string);
		}
		for (int i = 0; i < numberofRecords; i++) {
			User user = new User("Ariv" + i, "00#" + i);
			kafkaUserProducer.send(user);
			if(i % batch == 0) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("Waiting for a minute--- ");
			}
		}
		
//		for (int i = 0; i < 1000; i++) {
//			Vote vote = new Vote("Ariv" + i);
//			kafkaVoteProducer.send(vote);
//		}
	}

}

