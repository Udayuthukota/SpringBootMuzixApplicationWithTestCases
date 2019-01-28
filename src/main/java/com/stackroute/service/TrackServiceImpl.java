package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    /*
     * This method provide user a mean to add new track in the database.
     */
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException("TrackAlreadyExistsException");
        } else {
            Track savedTrack = trackRepository.save(track);
            return savedTrack;
        }
    }
    /*
     * This method provide user with all the track in the database.
     */

    @Override
    public List<Track> getAllTracks() {

        return trackRepository.findAll();
    }

    /*
     *This method provide track details of particular track Id.
     */
    @Override
    public Optional<Track> getTrackById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            Optional<Track> getTrack = trackRepository.findById(id);
            return getTrack;
        } else {
            throw new TrackNotFoundException("Track Not Found");
        }
    }

    /*
     * This method help user to update track comment of a particular track Id.
     */
    @Override
    public Track updateComments(String trackComments,int trackId)throws TrackNotFoundException {
        if(!trackRepository.existsById(trackId)){
            throw new TrackNotFoundException("Track to update not found");
        }
        Track updateTrack = trackRepository.findById(trackId).get();
        updateTrack.setTrackComments(trackComments);
        return trackRepository.save(updateTrack);
    }
    /*
     * This method help user to remove the track from database based on the input track Id.
     */
    @Override
    public boolean deleteTrackById(int id) throws TrackNotFoundException {
        if (trackRepository.existsById(id)) {
            trackRepository.deleteById(id);
            return true;
        } else {
            throw new TrackNotFoundException("Track Not Found");
        }
    }

    @Override
    public List<Track> findByTrackName(String trackName) {
        return trackRepository.findByTrackName(trackName);
    }
}
