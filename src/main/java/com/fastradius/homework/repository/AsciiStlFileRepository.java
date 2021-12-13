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

import static com.fastradius.homework.constant.StlConstants.*;

/**
 * An implementation of the StlFileRepository that is for reading and parsing ASCII STL files.
 */
@Slf4j
public class AsciiStlFileRepository implements StlFileRepository {

    private static final String WHITESPACE_REGEX = "\\s";

    public Optional<Solid> parseStlFile(@NonNull final String filename) {
        final String fileLocation = String.format("src/main/resources/%s.stl", filename);

        try (final BufferedReader reader = new BufferedReader(new FileReader(fileLocation))) {
            final Solid solid = new Solid();
            final List<Facet> facets = new ArrayList<>();

            decorateSolid(solid, facets, reader);
            solid.setFacets(facets);
            return Optional.of(solid);
        }
        catch (final FileNotFoundException ex) {
            log.error("Could not find the file '{}.stl'", filename);
            return Optional.empty();
        }
        catch (final IOException ex) {
            log.error("Could not parse the file '{}.stl'", filename);
            return Optional.empty();
        }
    }

    /**
     * Decorates the given solid and list of facets by parsing through an STL file via the buffered reader.
     * @param solid An object representing a shape described in an STL file
     * @param facets A list of facets, i.e. triangles, that create a mesh of the solid
     * @param reader An open connection to an STL file, ready to be read/parsed
     * @throws IOException If any errors occur when reading the STL file via the buffered reader.
     */
    void decorateSolid(@NonNull final Solid solid, @NonNull final List<Facet> facets, @NonNull final BufferedReader reader) throws IOException {
        String currentLine;
        Facet currentFacet = null;

        while ((currentLine = reader.readLine()) != null) {
            // Trimming the lead/trailing spaces ensures that the very first String in the array is always the line/block header, e.g.
            // 'vertex', 'endfacet', etc..
            final String[] currentLineValues = currentLine.trim().split(WHITESPACE_REGEX);

            if (SOLID_START_BLOCK.equalsIgnoreCase(currentLineValues[0])) {
                solid.setName(currentLineValues[1]);
            }
            else if (FACET_START_BLOCK.equalsIgnoreCase(currentLineValues[0])) {
                currentFacet = new Facet();
                final Point3d normal = new Point3d(
                        Double.parseDouble(currentLineValues[2]),
                        Double.parseDouble(currentLineValues[3]),
                        Double.parseDouble(currentLineValues[4])
                );
                currentFacet.setNormal(normal);
            }
            else if (VERTEX_LINE.equalsIgnoreCase(currentLineValues[0]) && currentFacet != null) {
                final Point3d vertex = new Point3d(
                        Double.parseDouble(currentLineValues[1]),
                        Double.parseDouble(currentLineValues[2]),
                        Double.parseDouble(currentLineValues[3])
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
    }

    // Because vertices are defined sequentially, we know what vertex we're currently on based on how many have been assigned so far
    private boolean isAVertex(@NonNull final Facet facet) {
        return facet.getA() == null && facet.getB() == null && facet.getC() == null;
    }

    private boolean isBVertex(@NonNull final Facet facet) {
        return facet.getA() != null && facet.getB() == null && facet.getC() == null;
    }
}
