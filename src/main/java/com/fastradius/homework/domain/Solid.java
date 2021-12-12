package com.fastradius.homework.domain;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solid {

    private String name;
    private List<Facet> facets;
}
