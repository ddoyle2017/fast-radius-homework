package com.fastradius.homework.controller;

import com.fastradius.homework.domain.ShapeRepresentation;
import com.fastradius.homework.service.ShapeAnalysisService;
import lombok.NonNull;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.fastradius.homework.constant.RestConstants.FILENAME_QUERY_PARAM;
import static com.fastradius.homework.constant.RestConstants.STL_API_PATH;

@Path(STL_API_PATH)
@Produces(MediaType.APPLICATION_JSON)
public class SolidAnalyzerResource {

    private final ShapeAnalysisService shapeAnalysisService;

    @Inject
    public SolidAnalyzerResource(@NonNull final ShapeAnalysisService shapeAnalysisService) {
        this.shapeAnalysisService = shapeAnalysisService;
    }

    @GET
    @Path("/get-surface-area")
    public Object getSurfaceAreaOfShape(@QueryParam(FILENAME_QUERY_PARAM) final String filename) {
        if (filename == null || filename.isEmpty()) {
            throw new BadRequestException("Filename must not be null or empty");
        }
        final ShapeRepresentation shapeRepresentation = shapeAnalysisService.getSurfaceAreaAndTriangles(filename);
        return Response.ok(shapeRepresentation).build();
    }
}
