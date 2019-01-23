package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/*
 *This is controller class which create object of TrackService class and
 * consume the methods of it.
 */
@ControllerAdvice
@RestController
@RequestMapping("api/v1/")
@Api(value = "Track")
public class TrackController {
    /* It instantiate the TrackService class so as to consume the
     * method of the same
     */
    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    /*
     * This method provide user a mean to add new track in the database.
     */
    @ApiOperation(value = "Save Tracks")
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        return responseEntity;
    }
    /*
     * This method provide user with all the track in the database.
     */
    @ApiOperation(value = "Get All Tracks")
    @GetMapping("tracks")
    public ResponseEntity<?> getAllTrack() {

        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.FOUND);
    }
    /*
     *This method provide track details to the user of particular track Id.
     */
    @ApiOperation(value = "Get Track By Id")
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {

        return new ResponseEntity<Optional<Track>>(trackService.getTrackById(id), HttpStatus.FOUND);
    }
    @ApiOperation(value = "Find Track By Name")
    @GetMapping("tracks/{name}")
    public ResponseEntity<?> findTrackByTrackName(@PathVariable String name) {
        return new ResponseEntity<List<Track>>(trackService.findByTrackName(name), HttpStatus.FOUND);
    }
    /*
     * This method help user to update track comment of a particular track Id.
     */
    @ApiOperation(value = "Update Comments")
    @PutMapping("track")
    public ResponseEntity<?> updateComments(@RequestBody Track track) throws TrackNotFoundException {
        return new ResponseEntity<Track>(trackService.updateComments(track), HttpStatus.OK);
    }
    /*
     * This method help user to remove the track from database based on the input track Id.
     */
    @ApiOperation(value = "Delete Tracks By Id")
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> removeTrack(@PathVariable int id) throws TrackNotFoundException {
        trackService.deleteTrackById(id);
        return new ResponseEntity<String>("removed", HttpStatus.GONE);
    }
}
