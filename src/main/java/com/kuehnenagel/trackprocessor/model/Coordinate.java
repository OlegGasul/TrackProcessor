package com.kuehnenagel.trackprocessor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Coordinate {
    private double lat;
    private double lon;
}
