package com.fastradius.homework.config;

import com.fastradius.homework.repository.StlFileRepository;
import com.fastradius.homework.repository.StlFileRepositoryImpl;
import com.fastradius.homework.service.ShapeAnalysisService;
import com.fastradius.homework.service.ShapeAnalysisServiceImpl;
import lombok.RequiredArgsConstructor;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

@RequiredArgsConstructor
public class ApplicationBinder extends AbstractBinder {

    /**
     * Binds classes used for dependency injection.
     */
    @Override
    protected void configure() {
        bind(StlFileRepositoryImpl.class).to(StlFileRepository.class);
        bind(ShapeAnalysisServiceImpl.class).to(ShapeAnalysisService.class);
    }
}
