package com.fastradius.homework.domain;

import lombok.*;

/**
 * Represents the REST response body for the 'Get Surface Area and Triangles' endpoint. Defines different properties about
 * the requesting shape/solid, e.g. surface area.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShapeRepresentation {

    private Double surfaceArea;
    private Long numberOfTriangles;
}
