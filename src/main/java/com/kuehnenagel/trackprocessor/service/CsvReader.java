package com.kuehnenagel.trackprocessor.service;

import com.google.gson.Gson;
import com.kuehnenagel.trackprocessor.model.Coordinate;
import com.kuehnenagel.trackprocessor.model.Track;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CsvReader {
    private static final Log LOG = LogFactory.getLog(CsvReader.class);
    
    private static final Gson GSON = new Gson();

    private static final String VESSEL_ID = "id";
    private static final String FROM_SEQ = "from_seq";
    private static final String TO_SEQ = "to_seq";
    private static final String FROM_PORT = "from_port";
    private static final String TO_PORT = "to_port";
    private static final String LEG_DURATION = "leg_duration";
    private static final String COUNT = "count";
    private static final String POINTS = "points";

    public static final String[] HEADERS = {VESSEL_ID, FROM_SEQ, TO_SEQ, FROM_PORT, TO_PORT, LEG_DURATION, COUNT, POINTS };

    public List<Track> loadTracks(String filePath, String fromPort, String toPort) throws IOException {
        try (Reader in = new FileReader(filePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);

            return StreamSupport.stream(records.spliterator(), false)
                .filter(record -> fromPort.equals(record.get(FROM_PORT)) && toPort.equals(record.get(TO_PORT)))
                .map(record -> Track.builder()
                    .vesselId(record.get(VESSEL_ID))
                    .fromSeq(Long.parseLong(record.get(FROM_SEQ)))
                    .toSeq(Long.parseLong(record.get(TO_SEQ)))
                    .fromPort(record.get(FROM_PORT))
                    .toPort(record.get(TO_PORT))
                    .legDuration(Long.parseLong(record.get(LEG_DURATION)))
                    .count(Integer.parseInt(record.get(COUNT)))
                    .coordinates(parseCoordinates(record.get(POINTS)))
                    .build())
                .collect(Collectors.toList());
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
            return null;
        }
    }

    private List<Coordinate> parseCoordinates(String json) {
        List<List<Double>> points = GSON.fromJson(json, List.class);
        return points.stream()
            .map((list) -> Coordinate.builder().lat(list.get(0)).lon(list.get(1)).build())
            .collect(Collectors.toList());
    }
}
