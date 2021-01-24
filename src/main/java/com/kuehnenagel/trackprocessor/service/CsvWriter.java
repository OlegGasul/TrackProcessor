package com.kuehnenagel.trackprocessor.service;

import com.google.gson.Gson;
import com.kuehnenagel.trackprocessor.model.Track;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CsvWriter {
    private static final Gson GSON = new Gson();

    public void writeCsv(String filePath, List<Track> tracks) throws IOException {
        FileWriter out = new FileWriter(filePath);
        CSVPrinter printer = CSVFormat.DEFAULT.withHeader(CsvReader.HEADERS).print(out);

        for (Track track: tracks) {
            Double[] pairs = track.getCoordinates().stream()
                .map(coordinate -> new Double[] { coordinate.getLat(), coordinate.getLon() })
                .toArray(Double[]::new);
            String points = GSON.toJson(pairs);

            printer.printRecord(
                track.getVesselId(), track.getFromSeq(), track.getToSeq(),
                track.getFromPort(), track.getToPort(),
                track.getLegDuration(), track.getCount(), points);
        }

    }

}
