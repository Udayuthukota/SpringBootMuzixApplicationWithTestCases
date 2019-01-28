package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track;

    @Before
    public void setUp() throws Exception {
        track = new Track();
        track.setTrackId(3);
        track.setTrackName("rechipodam brother");
        track.setComments("Dsp");
    }

    @After
    public void tearDown() throws Exception {
        trackRepository.deleteAll();
    }

    @Test
    public void testsaveTrack() {
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(3, fetchTrack.getTrackId());

    }

    @Test
    public void testSaveTrackFailure() {
        Track testTrack = new Track(5, "Mahesh", "By manisharma");
        trackRepository.save(track);
        Assert.assertNotSame(testTrack, track);
    }
/*
    get all muzic
     */

    @Test
    public void testGetAllTracks() {
        Track testTrack = new Track(6, "jeniffer lawrence", "By manisharma");
        Track testTrack1 = new Track(7, "Peniviti", "By penchaldas");
        trackRepository.save(testTrack);
        trackRepository.save(testTrack1);
        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("jeniffer lawrence", list.get(0).getTrackName());
    }

    /*
    delete by name
     */
    @Test
    public void testdeleteTrack() {
        trackRepository.save(track);
        trackRepository.deleteById(1);
        Assert.assertEquals(false, trackRepository.existsById(1));
    }


    /*
    find by Track name
     */
    @Test
    public void testfindByTrackName() {
        Track u1 = new Track(4, "HeartBeat", "Enrique Songs");
        trackRepository.save(u1);

        List<Track> list = trackRepository.findByTrackName("HeartBeat");
        Assert.assertEquals("HeartBeat", list.get(0).getTrackName());
    }
}
