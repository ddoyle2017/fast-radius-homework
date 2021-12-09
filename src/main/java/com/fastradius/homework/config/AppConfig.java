package com.fastradius.homework.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AppConfig extends Configuration {

    @NotEmpty
    @JsonProperty
    private String appName;
}
