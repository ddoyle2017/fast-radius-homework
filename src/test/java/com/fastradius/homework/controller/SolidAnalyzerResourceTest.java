package com.fastradius.homework.controller;

import com.fastradius.homework.domain.ShapeRepresentation;
import com.fastradius.homework.service.ShapeAnalysisService;
import jdk.internal.joptsimple.internal.Strings;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SolidAnalyzerResourceTest {

    @Mock
    private ShapeAnalysisService shapeAnalysisService;
    @InjectMocks
    private SolidAnalyzerResource underTest;

    @Test
    void getShapeRepresentation_GivenNullFilename_ThrowsBadRequestException() {
        assertThrows(BadRequestException.class, () -> underTest.getShapeRepresentation(null));
    }

    @Test
    void getShapeRepresentation_GivenEmptyFilename_ThrowsBadRequestException() {
        assertThrows(BadRequestException.class, () -> underTest.getShapeRepresentation(Strings.EMPTY));
    }

    @Test
    void getShapeRepresentation_GivenValidFilename_ReturnsResponseWithShapePresentation() {
        final ShapeRepresentation responseBody = ShapeRepresentation.builder()
                .numberOfTriangles(5L)
                .surfaceArea(1.0)
                .build();
        when(shapeAnalysisService.getSurfaceAreaAndTriangles(anyString())).thenReturn(responseBody);

        final Response result = underTest.getShapeRepresentation("Moon");
        assertNotNull(result);
        assertEquals(HttpStatus.OK_200, result.getStatus());
        assertEquals(responseBody, result.getEntity());
    }
}
