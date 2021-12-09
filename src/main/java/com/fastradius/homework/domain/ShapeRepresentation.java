package com.fastradius.homework.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ShapeRepresentation {

    private Long surfaceArea;
    private Long numberOfTriangles;
}
