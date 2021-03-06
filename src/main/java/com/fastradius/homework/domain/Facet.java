package com.fastradius.homework.domain;

import lombok.*;

import javax.vecmath.Point3d;

/**
 * Represents a single facet from an STL file. Each facet has 3 vertices which define a triangle of the solid.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facet {
    private Point3d normal;
    private Point3d a;
    private Point3d b;
    private Point3d c;

    /**
     * Calculates the surface area of this facet triangle, using the vertices it was initialized with
     * @return A Double representing the surface area for this facet.
     */
    public Double getSurfaceArea() {
        if (a == null || b == null || c == null) {
            throw new IllegalArgumentException("None of the 3 vertices can be null when calculating the surface area.");
        }
        // TODO In cases with millions of triangles, look into whether the division performance boost from bit shifting worth it
        final double distanceAtoB = a.distance(b);
        final double distanceAtoC = a.distance(c);
        final double distanceBtoC = b.distance(c);
        final double surface = (distanceAtoB + distanceAtoC + distanceBtoC) / 2;
        return Math.abs(Math.sqrt(surface * (surface - distanceAtoB) * (surface - distanceAtoC) * (surface - distanceBtoC)));
    }
}
