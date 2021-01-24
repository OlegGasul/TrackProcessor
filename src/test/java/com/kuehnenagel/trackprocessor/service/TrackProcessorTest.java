package com.kuehnenagel.trackprocessor.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

class TrackProcessorTest {

    @Autowired
    private CsvReader csvReader;

    @Test
    void shouldFindMostRepresentativeTrack() {

    }

    private File getFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(filename).getFile());
    }
}