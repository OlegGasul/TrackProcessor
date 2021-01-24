package com.kuehnenagel.trackprocessor.service;

import com.kuehnenagel.trackprocessor.model.Coordinate;
import com.kuehnenagel.trackprocessor.model.Track;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class TrackProcessorTest {

    @Autowired
    private GeoTools geoTools;

    @Autowired
    private TrackProcessor trackProcessor;

    @Test
    void shouldFindMostRepresentativeTrack() {
        // TODO implement
    }

    private Track createTrack(Long fromSeq, Long toSeq, List<Coordinate> coordinates) {
        return Track.builder()
            .vesselId("ID")
            .fromSeq(fromSeq)
            .toSeq(toSeq)
            .fromPort("A")
            .toPort("B")
            .coordinates(coordinates)
            .build();
    }
}