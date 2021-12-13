package com.fastradius.homework.repository;

import com.fastradius.homework.domain.Facet;
import com.fastradius.homework.domain.Solid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.vecmath.Point3d;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class StlFileRepositoryImpl implements StlFileRepository {

    private static final String SOLID_START_BLOCK = "solid";
    private static final String FACET_START_BLOCK = "facet";
    private static final String FACET_END_BLOCK = "endfacet";
    private static final String VERTEX_LINE = "vertex";

    public Optional<Solid> parseStlFile(@NonNull final String fileName) {
        if (fileName.isEmpty()) {
            return Optional.empty();
        }
        try {
            final String fileLocation = String.format("src/main/resources/%s.stl", fileName);
            final BufferedReader reader = new BufferedReader(new FileReader(fileLocation));

            final Solid solid = new Solid();
            final List<Facet> facets = new ArrayList<>();

            String currentLine;
            Facet currentFacet = null;

            while ((currentLine = reader.readLine()) != null) {
                parseStlLine(currentLine, solid, facets, currentFacet);
            }
            solid.setFacets(facets);
            return Optional.of(solid);
        }
        catch (final FileNotFoundException ex) {
            log.error("Could not find the file '{}.stl'", fileName);
            return Optional.empty();
        }
        catch (final IOException ex) {
            log.error("Could not parse the file '{}.stl'", fileName);
            return Optional.empty();
        }
    }

    void parseStlLine(final String line, final Solid solid, final List<Facet> facets, Facet currentFacet) {
        if (line == null) return;

        final String[] currentLineValues = line.trim().split("\\s");

        if (SOLID_START_BLOCK.equalsIgnoreCase(currentLineValues[0])) {
            solid.setName(currentLineValues[1]);
        }
        else if (FACET_START_BLOCK.equalsIgnoreCase(currentLineValues[0])) {
            currentFacet = new Facet();
            final Point3d normal = new Point3d(
                    Integer.parseInt(currentLineValues[2]),
                    Integer.parseInt(currentLineValues[3]),
                    Integer.parseInt(currentLineValues[4])
            );
            currentFacet.setNormal(normal);
        }
        else if (VERTEX_LINE.equalsIgnoreCase(currentLineValues[0]) && currentFacet != null) {
            final Point3d vertex = new Point3d(
                    Integer.parseInt(currentLineValues[1]),
                    Integer.parseInt(currentLineValues[2]),
                    Integer.parseInt(currentLineValues[3])
            );
            if (isAVertex(currentFacet)) {
                currentFacet.setA(vertex);
            }
            else if (isBVertex(currentFacet)) {
                currentFacet.setB(vertex);
            }
            else {
                currentFacet.setC(vertex);
            }
        }
        else if (FACET_END_BLOCK.equalsIgnoreCase(currentLineValues[0])) {
            facets.add(currentFacet);
            currentFacet = null;
        }
    }

    private boolean isAVertex(@NonNull final Facet facet) {
        return facet.getA() == null && facet.getB() == null && facet.getC() == null;
    }

    private boolean isBVertex(@NonNull final Facet facet) {
        return facet.getA() != null && facet.getB() == null && facet.getC() == null;
    }
}
