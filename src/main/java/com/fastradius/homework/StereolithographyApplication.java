package com.fastradius.homework;

import com.fastradius.homework.config.AppConfig;
import com.fastradius.homework.config.ApplicationBinder;
import com.fastradius.homework.controller.SolidAnalyzerResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class StereolithographyApplication extends Application<AppConfig> {

    public static void main(final String... args) throws Exception {
        new StereolithographyApplication().run(args);
    }

    @Override
    public void run(final AppConfig configuration, final Environment environment) throws Exception {
        environment.jersey().register(SolidAnalyzerResource.class);
        environment.jersey().register(new ApplicationBinder());
    }
}