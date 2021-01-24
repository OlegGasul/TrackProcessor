package com.kuehnenagel.trackprocessor.service;

import com.kuehnenagel.trackprocessor.model.Coordinate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeoToolsTest {

    @Autowired
    private GeoTools geoTools;

    @Test
    void shouldCalculateDistance() {
        assertEquals(142.4422913889643, geoTools.calculateDistance(
                List.of(new Coordinate(50.07808, 19.98008),
                        new Coordinate(50.07834, 19.98075),
                        new Coordinate(50.07873, 19.98180))));
    }

    @Test
    void shouldReturnZeroForEmptyRoute() {
        assertEquals(0.0, geoTools.calculateDistance(null));
        assertEquals(0.0, geoTools.calculateDistance(List.of(new Coordinate(50.07808, 19.98008))));
    }

}