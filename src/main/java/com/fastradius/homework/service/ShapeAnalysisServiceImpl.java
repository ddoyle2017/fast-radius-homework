package com.fastradius.homework.service;

import com.fastradius.homework.domain.Facet;
import com.fastradius.homework.domain.ShapeRepresentation;
import com.fastradius.homework.domain.Solid;
import com.fastradius.homework.repository.StlFileRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

/**
 * Represents different calculations done to a solid, as defined by an STL file.
 */
@Slf4j
@Service
public class ShapeAnalysisServiceImpl implements ShapeAnalysisService {

    private final StlFileRepository stlFileRepository;

    @Inject
    public ShapeAnalysisServiceImpl(@NonNull final StlFileRepository stlFileRepository) {
        this.stlFileRepository = stlFileRepository;
    }

    @Override
    public ShapeRepresentation getSurfaceAreaAndTriangles(@NonNull final String filename) {
        final Solid solid = stlFileRepository.parseStlFile(filename);

        final Double totalSurfaceArea = solid.getFacets().stream()
                .mapToDouble(Facet::getSurfaceArea)
                .sum();
        return ShapeRepresentation.builder()
                .numberOfTriangles((long)solid.getFacets().size())
                .surfaceArea(totalSurfaceArea)
                .build();
    }
}
