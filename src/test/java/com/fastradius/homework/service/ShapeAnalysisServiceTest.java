package com.fastradius.homework.service;

import com.fastradius.homework.domain.Facet;
import com.fastradius.homework.domain.ShapeRepresentation;
import com.fastradius.homework.domain.Solid;
import com.fastradius.homework.repository.StlFileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.vecmath.Point3d;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShapeAnalysisServiceTest {

    @Mock
    private StlFileRepository stlFileRepository;
    @InjectMocks
    private ShapeAnalysisServiceImpl underTest;

    @Test
    void ShapeAnalysisService_GivenNullDependencies_ThrowsException() {

    }

    @Test
    void getSurfaceAreaAndTriangles_GivenNullFilename_ThrowsException() {

    }

    @Test
    void getSurfaceAreaAndTriangles_GivenValidFilename_ReturnsTotalSurfaceAreaAndNumberOfTriangles() {
        final Facet facetA = new Facet(
                new Point3d(1, 0, 0),
                new Point3d(0, 1, 0),
                new Point3d(0, 0, 1)
        );
        final Facet facetB = new Facet(
                new Point3d(2, 1, 1),
                new Point3d(1, 2, 1),
                new Point3d(1, 1, 2)
        );
        final Facet facetC = new Facet(
                new Point3d(3, 2, 2),
                new Point3d(2, 3, 2),
                new Point3d(2, 2, 3)
        );
        final Solid solid = new Solid(Arrays.asList(facetA, facetB, facetC));

        when(stlFileRepository.parseStlFile(anyString())).thenReturn(solid);

        final ShapeRepresentation result = underTest.getSurfaceAreaAndTriangles("Some shape");
        assertNotNull(result);
        assertNotNull(result.getNumberOfTriangles());
        assertEquals(3, result.getNumberOfTriangles());
        assertNotNull(result.getSurfaceArea());
        assertEquals(2.61, result.getSurfaceArea(), 0.1);
    }
}
