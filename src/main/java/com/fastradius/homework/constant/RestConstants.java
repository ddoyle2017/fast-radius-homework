package com.fastradius.homework.constant;

import lombok.experimental.UtilityClass;

/**
 * Defines constants used at a REST level, e.g. endpoint paths or query parameters
 */
@UtilityClass
public final class RestConstants {
    public static final String STL_API_PATH = "/stereolithography";
    public static final String SHAPE_REPRESENTATION_ENDPOINT = "/get-shape-representation";
    public static final String FILENAME_QUERY_PARAM = "filename";
}
