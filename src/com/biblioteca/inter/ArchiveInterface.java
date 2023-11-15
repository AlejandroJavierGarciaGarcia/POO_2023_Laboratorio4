package com.biblioteca.inter;

import java.util.List;

public interface ArchiveInterface {

    void export(List<String[]> data, String fileName);

    List<String[]> read(String fileName);
}
