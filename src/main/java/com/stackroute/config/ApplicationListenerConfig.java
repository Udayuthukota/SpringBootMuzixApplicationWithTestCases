package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerConfig implements org.springframework.context.ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    TrackRepository trackRepository;
    @Value("2")
    private int trackId;
    @Value("default listener")
    private String trackName;
    @Value("default listener comments")
    private String comments;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Track track = new Track(trackId, trackName, comments);
        trackRepository.save(track);
    }

}
