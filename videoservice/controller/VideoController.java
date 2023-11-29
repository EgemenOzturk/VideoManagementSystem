package com.example.videoservice.controller;

import com.example.videoservice.exception.ResourceNotFoundException;
import com.example.videoservice.model.Video;
import com.example.videoservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping
    public List<Video> getAllVideos(){
        return videoRepository.findAll();
    }

    //Create a Video in the DB
    @PostMapping
    public Video createVideo(@RequestBody Video video){
        return videoRepository.save(video);
    }

    //Read/Retrieve a Video by ID
    @GetMapping("{id}")
    public ResponseEntity<Video> getVideoById( @PathVariable long id){
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video not exist with id: " + id));

        return ResponseEntity.ok(video);

    }

    //Update a Video
    @PutMapping("{id}")
    public ResponseEntity<Video> updateVideoById( @PathVariable long id, @RequestBody Video videoDetails){
        Video updatedVideo = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video does not exist with the ID: " + id));

        updatedVideo.setName(videoDetails.getName());
        updatedVideo.setDuration(videoDetails.getDuration());
        updatedVideo.setDescription(videoDetails.getDescription());

        videoRepository.save(updatedVideo);

        return ResponseEntity.ok(updatedVideo);
    }

    //Delete a Video
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteVideo(@PathVariable long id){

        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video does not exist with ID: " + id));

        videoRepository.delete(video);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
