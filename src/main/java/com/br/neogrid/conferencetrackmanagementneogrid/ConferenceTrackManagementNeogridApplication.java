package com.br.neogrid.conferencetrackmanagementneogrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConferenceTrackManagementNeogridApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConferenceTrackManagementNeogridApplication.class, args);
	}

}

