package com.kuehnenagel.trackprocessor;

import com.kuehnenagel.trackprocessor.model.Track;
import com.kuehnenagel.trackprocessor.service.CsvReader;
import com.kuehnenagel.trackprocessor.service.CsvWriter;
import com.kuehnenagel.trackprocessor.service.TrackProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TrackProcessorApplication implements ApplicationRunner {
    private static final Log LOG = LogFactory.getLog(TrackProcessorApplication.class);
    
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
        List<String> params = args.getNonOptionArgs();
        if (params.size() < 4) {
            LOG.error("Usage: mvn spring-boot:run -Dspring-boot.run.arguments=\"<From port> <To port> <Input file path> <Output file path>\"");
            return;
        }

        String fromPort = params.get(0);
        String toPort = params.get(1);
        String inputFilePath = params.get(2);
        String outputFilePath = params.get(3);

        List<Track> tracks = csvReader.loadTracks(inputFilePath, fromPort, toPort);
        if (tracks == null) {
            LOG.info("Directions from " + fromPort + " to " + toPort + " has not been found!");
            return;
        }

        Track result = trackProcessor.findMostRepresentativeTrack(tracks);
        csvWriter.writeCsv(outputFilePath, List.of(result));
    }
}
