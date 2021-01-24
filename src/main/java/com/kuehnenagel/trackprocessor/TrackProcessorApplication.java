package com.kuehnenagel.trackprocessor;

import com.kuehnenagel.trackprocessor.model.Track;
import com.kuehnenagel.trackprocessor.service.CsvReader;
import com.kuehnenagel.trackprocessor.service.CsvWriter;
import com.kuehnenagel.trackprocessor.service.TrackProcessor;
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

    @Autowired
    private CsvWriter csvWriter;

    @Autowired
    private TrackProcessor trackProcessor;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Track> tracks = csvReader.loadTracks("DEBRV_DEHAM_historical_routes.csv");

        Track result = trackProcessor.findMostRepresentativeTrack(tracks);

        csvWriter.writeCsv("result.csv", List.of(result));
    }
}
