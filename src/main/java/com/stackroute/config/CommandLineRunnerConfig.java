package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:application.properties")
public class CommandLineRunnerConfig implements org.springframework.boot.CommandLineRunner {
    @Autowired
    TrackRepository trackRepository;
    @Autowired
    Environment environment;

    @Override
    public void run(String... args) {
        Track track = new Track(Integer.parseInt(environment.getProperty("trackId")), environment.getProperty("trackName"), environment.getProperty("comments"));
        trackRepository.save(track);
    }
}

