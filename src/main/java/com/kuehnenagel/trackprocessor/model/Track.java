package com.kuehnenagel.trackprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Track {
    private Long vesselId;
    private Long fromSeq;
    private Long toSeq;
    private String fromPort;
    private String toPort;
    private Long legDuration;
    private Integer count;
    private List<Coordinate> coordinates;

}
