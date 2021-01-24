package com.kuehnenagel.trackprocessor;

import com.kuehnenagel.trackprocessor.model.Track;
import com.kuehnenagel.trackprocessor.service.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TrackProcessorApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(TrackProcessorApplication.class, args);
    }

    @Autowired
    private CsvReader csvReader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Track> tracks = csvReader.loadTracks("DEBRV_DEHAM_historical_routes.csv");
        int i = 0;
    }
}
