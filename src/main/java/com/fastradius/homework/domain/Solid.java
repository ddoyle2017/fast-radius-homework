package com.fastradius.homework.domain;

import lombok.*;

import java.util.List;

/**
 * Represents a solid as defined by an STL file.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solid {

    private String name;
    private List<Facet> facets;
}
