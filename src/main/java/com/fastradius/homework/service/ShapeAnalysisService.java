package com.fastradius.homework.service;

import com.fastradius.homework.domain.ShapeRepresentation;

public interface ShapeAnalysisService {
    ShapeRepresentation getSurfaceAreaAndTriangles(String filename);
}
