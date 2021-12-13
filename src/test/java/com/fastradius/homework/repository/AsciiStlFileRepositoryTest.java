package com.fastradius.homework.repository;

import com.fastradius.homework.domain.Facet;
import com.fastradius.homework.domain.Solid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.vecmath.Point3d;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.fastradius.homework.constant.StlConstants.SOLID_START_BLOCK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AsciiStlFileRepositoryTest {

    @Mock
    private BufferedReader reader;
    @InjectMocks
    private AsciiStlFileRepository underTest;

    @Test
    void parseStlFile_GivenNullFilename_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> underTest.parseStlFile(null));
    }

    @Test
    void parseStlFile_GivenInvalidFilename_ReturnsEmptyOptional() {
        assertEquals(Optional.empty(), underTest.parseStlFile("dabiuwbdiuyawbdaiyuwd"));
    }


    @Test
    void decorateSolid_GivenNullSolid_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> underTest.decorateSolid(null, Collections.emptyList(), reader));
    }

    @Test
    void decorateSolid_GivenNullFacets_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> underTest.decorateSolid(new Solid(), null, reader));
    }

    @Test
    void decorateSolid_GivenNullBufferedReader_ThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> underTest.decorateSolid(new Solid(), Collections.emptyList(), null));
    }

    @Test
    void decorateSolid_WhenBufferedReaderEncountersEndOfFile_EndParsingFile() throws IOException {
        final Solid solid = new Solid();

        when(reader.readLine()).thenReturn(null);
        underTest.decorateSolid(solid, new ArrayList<>(), reader);

        assertNull(solid.getName());
        assertNull(solid.getFacets());
    }

    @Test
    void decorateSolid_WhenBufferedReaderEncountersSolidStartBlock_AddsNameToSolid() throws IOException {
        final Solid solid = new Solid();

        when(reader.readLine()).thenReturn(
                SOLID_START_BLOCK + " Moon",
                null,
                null
        );
        underTest.decorateSolid(solid, new ArrayList<>(), reader);

        assertEquals("Moon", solid.getName());
        assertNull(solid.getFacets());
    }

    @Test
    void decorateSolid_WhenBufferedReaderEncountersFacetBlocks_ConstructsFacetAndAddsToList() throws IOException {
        final Solid solid = new Solid();
        final List<Facet> facets = new ArrayList<>();

        when(reader.readLine()).thenReturn(
                "solid Moon",
                "  facet normal 0 0 0",
                "      outer loop",
                "          vertex 0 0 0",
                "          vertex 1 0 0",
                "          vertex 1 1 1",
                "      endloop",
                "  endfacet",
                null
        );
        underTest.decorateSolid(solid, facets, reader);

        assertEquals("Moon", solid.getName());
        assertFalse(facets.isEmpty());
        assertEquals(new Point3d(0, 0, 0), facets.get(0).getNormal());
        assertEquals(new Point3d(0, 0, 0), facets.get(0).getA());
        assertEquals(new Point3d(1, 0, 0), facets.get(0).getB());
        assertEquals(new Point3d(1, 1, 1), facets.get(0).getC());
    }
}
