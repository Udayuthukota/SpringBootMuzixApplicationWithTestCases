package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exception.TrackAlreadyExistsException;
import com.stackroute.exception.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class TrackServiceTest {
    private Track track ;

    @Mock
    private TrackRepository trackRepository;
    @InjectMocks
    private TrackServiceImpl trackService;

    List<Track> tracklist = null;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
         track = new Track(1,"Marana Mass","anirudh");
        tracklist = new ArrayList<>();
        tracklist.add(track);
    }

    @Test
    public void saveTrackSuccess() throws TrackAlreadyExistsException {
      when(trackRepository.save((Track)any())).thenReturn(track);
      Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(savedTrack,track);
        verify(trackRepository, times(1)).save(track);
    }

    @Test
    public void saveTrackFailure() throws TrackAlreadyExistsException{
     when(trackRepository.save((Track)any())).thenReturn(null);
     Track savedTrack = trackService.saveTrack(track);
     Assert.assertEquals(savedTrack,null);
    }

    //get all items in list
    @Test
    public void getAllTracksSuccess() {

        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(tracklist);
        List<Track> trackslist = trackService.getAllTracks();
        Assert.assertEquals(trackslist, tracklist);
    }

    @Test
    public void getTrackByIdSuceess() throws TrackNotFoundException {
        when(trackRepository.existsById(anyInt())).thenReturn(true);
        when(trackRepository.findById((anyInt()))).thenReturn(Optional.of(track));
        Optional<Track> getTrack = trackService.getTrackById(track.getTrackId());
        Assert.assertEquals(getTrack,Optional.of(track));
        verify(trackRepository,times(1)).findById(track.getTrackId());
    }

    @Test
    public void removeTrackByIdSuccess() throws TrackNotFoundException{
        when(trackRepository.existsById(anyInt())).thenReturn(true);

        boolean deleted = trackService.deleteTrackById(track.getTrackId());
        verify(trackRepository,times(1)).deleteById(track.getTrackId());
    }

//    @Test
//    public void updateComment()throws TrackNotFoundException{
//        when(trackRepository.existsById(anyInt())).thenReturn(true);
//        when(trackRepository.findById(anyInt()).get()).thenReturn(track);
//        when(trackRepository.save((any()))).thenReturn(track);
////        String comment = "jil jil jiga";
//        track.setComments("jil jil jiga");
//        Track updateTrack = trackService.updateComments(track.getComments(),track.getTrackId());
//        Assert.assertEquals(updateTrack,track);
//        verify(trackRepository,times(1)).save(track);
//    }


    @Test
    public void updateTrackTestFailure() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(null, savedTrack);

    }

    //Remove or Deleting by ID

    @Test
    public void removeTrackTestSuccess() throws TrackNotFoundException {
        when(trackRepository.existsById(anyInt())).thenReturn(true);
        when(trackRepository.findAll()).thenReturn(tracklist);
        boolean output = trackService.deleteTrackById(track.getTrackId());
        Assert.assertEquals(true, output);
        verify(trackRepository, times(1)).deleteById(1);
    }

    @Test(expected = TrackNotFoundException.class)
    public void removeTrackTestFailure() throws TrackNotFoundException {
        when(!trackRepository.existsById(5)).thenReturn(true);
        when(trackRepository.findAll()).thenReturn(tracklist);
        boolean output = trackService.deleteTrackById(track.getTrackId());
        Assert.assertEquals(tracklist, output);


       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/

    }


//    gettrackbyname

    @Test
    public void getTrackByNameTestSuccess() {

        //stubbing the mock to return specific data
        when(trackRepository.findByTrackName(track.getTrackName())).thenReturn(tracklist);
        List<Track> trackslist = trackService.findByTrackName(track.getTrackName());
        Assert.assertEquals(tracklist, trackslist);

    }
}