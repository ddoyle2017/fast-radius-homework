package com.fastradius.homework.repository;

import com.fastradius.homework.domain.Solid;

import java.util.Optional;

public interface StlFileRepository {

    Optional<Solid> parseStlFile(String fileName);
}
