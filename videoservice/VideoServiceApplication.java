package com.example.videoservice;

import com.example.videoservice.model.Video;
import com.example.videoservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideoServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
	}

	@Autowired
	private VideoRepository videoRepository;

	@Override
	public void run(String... args) throws Exception {
		Video video = new Video();
		video.setName("Eng-101");
		video.setDuration(780);
		video.setDescription("A great welcome lecture for the starters! ");
		videoRepository.save(video);

		Video video2 = new Video();
		video2.setName("Eng-102");
		video2.setDuration(900);
		video2.setDescription("An intermediate lecture for English learners! ");
		videoRepository.save(video2);
	}
}
