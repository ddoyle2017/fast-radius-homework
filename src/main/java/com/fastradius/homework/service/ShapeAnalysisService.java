package com.fastradius.homework.service;

import com.fastradius.homework.domain.ShapeRepresentation;

/**
 * Defines an interface for analyzing data retrieved from an STL file
 */
public interface ShapeAnalysisService {
    ShapeRepresentation getSurfaceAreaAndTriangles(String filename);
}
