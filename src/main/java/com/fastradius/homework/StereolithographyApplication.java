package com.fastradius.homework;

import com.fastradius.homework.config.AppConfig;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class StereolithographyApplication extends Application<AppConfig> {

    public static void main(final String... args) throws Exception {
        new StereolithographyApplication().run(args);
    }

    @Override
    public void run(final AppConfig configuration, final Environment environment) throws Exception {

    }
}