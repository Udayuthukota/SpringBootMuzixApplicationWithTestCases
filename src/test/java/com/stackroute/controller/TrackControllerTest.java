package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TrackControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Track track;
    @MockBean
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;

    private List<Track> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
        track = new Track();
        track.setComments("Enrique song");
        track.setTrackId(1);
        track.setTrackName("Freak");
        list = new ArrayList();
        list.add(track);
    }

    @Test
    public void saveTrack() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/track")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
//        @Test
//        public void saveMuzixFailure() throws Exception {
//            when(muzixService.saveMuzix(any())).thenThrow(MuzixAlreadyExistsException.class);
//            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/muzix")
//                    .contentType(MediaType.APPLICATION_JSON).content(asJsonString(muzix)))
//                    .andExpect(MockMvcResultMatchers.status().isConflict())
//                    .andDo(MockMvcResultHandlers.print());
//        }

    @Test
    public void getAllTracks() throws Exception {
        when(trackService.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void getAllTracksFailure() throws Exception {
        when(trackService.getAllTracks()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tracks")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void updateComments() throws Exception {
        when(trackService.updateComments("Enrique Song",1)).thenReturn(track);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/{trackid}/{trackComments}")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void removeTrackById() throws Exception {
        when(trackService.deleteTrackById(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isGone())
                .andDo(MockMvcResultHandlers.print());

    }


    @Test
    public void trackByName() throws Exception {
        when(trackService.findByTrackName(any())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tracks/Freak")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void trackById() throws Exception {
        when(trackService.getTrackById(1)).thenReturn(Optional.of(track));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/track/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(track)))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andDo(MockMvcResultHandlers.print());

    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}