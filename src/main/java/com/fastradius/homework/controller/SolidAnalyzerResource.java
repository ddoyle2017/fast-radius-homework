package com.fastradius.homework.controller;

import com.fastradius.homework.domain.ShapeRepresentation;
import com.fastradius.homework.service.ShapeAnalysisService;
import lombok.NonNull;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.fastradius.homework.constant.RestConstants.*;

/**
 * Represents all endpoints for the Stereolithography API
 */
@Path(STL_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class SolidAnalyzerResource {

    private final ShapeAnalysisService shapeAnalysisService;

    @Inject
    public SolidAnalyzerResource(@NonNull final ShapeAnalysisService shapeAnalysisService) {
        this.shapeAnalysisService = shapeAnalysisService;
    }

    /**
     * An endpoint for getting the shape representation for the given filename, as defined by an STL file. This
     * endpoint will return the number of triangles and the total surface area
     * @param filename The name of an STL file
     * @return A JSON response with the surface area and number of triangles
     */
    @GET
    @Path(SHAPE_REPRESENTATION_ENDPOINT)
    public Response getShapeRepresentation(@QueryParam(FILENAME_QUERY_PARAM) final String filename) {
        if (filename == null || filename.isEmpty()) {
            throw new BadRequestException("Filename must not be null or empty");
        }
        final ShapeRepresentation shapeRepresentation = shapeAnalysisService.getSurfaceAreaAndTriangles(filename);
        return Response.ok(shapeRepresentation).build();
    }
}
