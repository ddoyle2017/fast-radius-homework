package com.fastradius.homework.service;

import com.fastradius.homework.domain.Facet;
import com.fastradius.homework.domain.ShapeRepresentation;
import com.fastradius.homework.domain.Solid;
import com.fastradius.homework.repository.StlFileRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

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
        final Optional<Solid> solid = stlFileRepository.parseStlFile(filename);
        if (!solid.isPresent()) {
            throw new NotFoundException(String.format("No STL file found for '%s'", filename));
        }

        final Double totalSurfaceArea = solid.get().getFacets().stream()
                .mapToDouble(Facet::getSurfaceArea)
                .sum();
        return ShapeRepresentation.builder()
                .numberOfTriangles((long)solid.get().getFacets().size())
                .surfaceArea(totalSurfaceArea)
                .build();
    }
}
