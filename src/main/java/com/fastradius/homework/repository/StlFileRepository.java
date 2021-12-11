package com.fastradius.homework.repository;

import com.fastradius.homework.domain.Solid;

public interface StlFileRepository {

    Solid parseStlFile(String fileName);
}
