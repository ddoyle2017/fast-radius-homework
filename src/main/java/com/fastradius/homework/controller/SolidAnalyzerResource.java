package com.fastradius.homework.controller;

import com.fastradius.homework.domain.ShapeRepresentation;
import com.fastradius.homework.service.ShapeAnalysisService;
import lombok.NonNull;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Object getSurfaceAreaOfShape(@QueryParam("filename") final String filename) {
        final ShapeRepresentation shapeRepresentation = shapeAnalysisService.getSurfaceAreaAndTriangles(filename);
        return Response.ok(shapeRepresentation);
    }
}
