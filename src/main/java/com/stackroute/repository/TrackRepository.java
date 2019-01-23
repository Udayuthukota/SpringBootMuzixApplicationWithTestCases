package com.stackroute.repository;


import com.stackroute.domain.Track;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * This is Track object repository interface to implement the track service in repository.
 */
@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {

    //@Query("select p from Track p where p.trackName = ?1")
    List<Track> findByTrackName(String trackName);
}