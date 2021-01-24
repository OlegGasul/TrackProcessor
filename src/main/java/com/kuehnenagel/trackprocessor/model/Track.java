package com.kuehnenagel.trackprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class Track {
    private String vesselId;
    private Long fromSeq;
    private Long toSeq;
    private String fromPort;
    private String toPort;
    private Long legDuration;
    private Integer count;
    private List<Coordinate> coordinates;

}
