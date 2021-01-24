package com.kuehnenagel.trackprocessor.service;

import com.kuehnenagel.trackprocessor.model.Coordinate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeoTools {
    private static final int EARTH_RADIUS = 6371;

    public double calculateDistance(List<Coordinate> route) {
        if (route == null || route.size() < 2) {
            return 0;
        }

        double distance = 0;
        for (int i = 1; i < route.size(); i++) {
            distance += calculateDistance(route.get(i), route.get(i - 1));
        }

        return distance;
    }

    private double calculateDistance(Coordinate x, Coordinate y) {
        double latDistance = Math.toRadians(y.getLat() - x.getLat());
        double lonDistance = Math.toRadians(y.getLon() - x.getLon());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
            Math.cos(Math.toRadians(x.getLat())) * Math.cos(Math.toRadians(y.getLat())) *
            Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c * 1000;
    }

}
