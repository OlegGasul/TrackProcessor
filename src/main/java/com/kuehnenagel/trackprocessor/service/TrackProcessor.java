package com.kuehnenagel.trackprocessor.service;

import com.kuehnenagel.trackprocessor.model.Track;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrackProcessor {
    private static final double PERCENTAGE = 0.1; 

    @Autowired
    private GeoTools geoTools;

    public Track findMostRepresentativeTrack(List<Track> tracks) {
        Map<Track, Double> distanceMap = tracks.stream()
            .collect(Collectors.toMap(track -> track, track -> geoTools.calculateDistance(track.getCoordinates())));
        double[] distances = ArrayUtils.toPrimitive(distanceMap.values().toArray(Double[]::new));
        
        double mean = new Mean().evaluate(distances);
        double deviation = new StandardDeviation().evaluate(distances);

        double min = mean - deviation * PERCENTAGE;
        double max = mean + deviation * PERCENTAGE;
        
        List<Track> filtered = tracks.stream().filter(track -> {
            Double distance = distanceMap.get(track);
            return distance >= min && distance <= max;
        }).collect(Collectors.toList());

        double filteredAverageDistance = filtered.stream().mapToDouble(track -> distanceMap.get(track)).average().orElse(0.0);
        
        return filtered.stream()
            .min(Comparator.comparingDouble(track -> distanceMap.get(track) - filteredAverageDistance))
            .orElse(null);
    }

}
