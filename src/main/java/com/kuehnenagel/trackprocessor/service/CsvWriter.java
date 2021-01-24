package com.kuehnenagel.trackprocessor.service;

import com.kuehnenagel.trackprocessor.model.Track;
import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CsvWriter {

    public void writeCsv(String filePath, List<Track> tracks) throws IOException {
        FileWriter out = new FileWriter(filePath);
        CSVFormat.DEFAULT.withHeader(CsvReader.HEADERS).print(out);
    }

}
