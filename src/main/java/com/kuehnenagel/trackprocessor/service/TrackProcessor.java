package com.kuehnenagel.trackprocessor.service;

import com.kuehnenagel.trackprocessor.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackProcessor {

    @Autowired
    private GeoTools geoTools;

    public Track findMostRepresentativeTrack(List<Track> tracks) {
        // TODO
        // 1. Calculate a mean by the distance of each route from the list
        // 2. Find most close route for this distance

        return null;
    }

}
