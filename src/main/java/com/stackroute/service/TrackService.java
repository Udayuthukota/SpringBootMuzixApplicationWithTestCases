package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;

import java.util.List;
import java.util.Optional;

/*
 * This is an interface to implement the Track class of domain package.
 */
public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public List<Track> getAllTracks();
    public Optional<Track> getTrackById(int id) throws TrackNotFoundException;
    public Track updateComments(Track track) throws TrackNotFoundException;
    public void deleteTrackById(int id) throws TrackNotFoundException;
    List<Track> findByTrackName(String trackName);
}
