package com.fastradius.homework.repository;

import com.fastradius.homework.domain.Solid;

import java.util.Optional;

/**
 * Defines interface for CRUD operations on STL files.
 */
public interface StlFileRepository {
    Optional<Solid> parseStlFile(String fileName);
}
