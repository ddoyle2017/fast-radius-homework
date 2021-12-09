package com.fastradius.homework.service;

import com.fastradius.homework.repository.StlFileRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class ShapeAnalysisServiceImpl implements ShapeAnalysisService {

    private final StlFileRepository stlFileRepository;

    @Inject
    public ShapeAnalysisServiceImpl(@NonNull final StlFileRepository stlFileRepository) {
        this.stlFileRepository = stlFileRepository;
    }

    @Override
    public Long calculateSurfaceArea() {
        return null;
    }

    @Override
    public Long countNumberOfTriangles() {
        return null;
    }
}
