package com.fastradius.homework.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.vecmath.Point3d;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class FacetTest {

    private static final Double FLOATING_POINT_DELTA = 0.1;

    @Test
    void getSurfaceArea_WhenAnyVerticesAreNull_ThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Facet().getSurfaceArea());
    }

    @Test
    void getSurfaceArea_Calculates3dSurfaceAreaCorrectly() {
        final Point3d normal = new Point3d(0, 0, 0);
        final Point3d a = new Point3d(1, 0, 0);
        final Point3d b = new Point3d(0, 1, 0);
        final Point3d c = new Point3d(0, 0, 1);

        final Facet underTest = new Facet(normal, a, b, c);
        assertEquals(0.87, underTest.getSurfaceArea(), FLOATING_POINT_DELTA);
    }
}
