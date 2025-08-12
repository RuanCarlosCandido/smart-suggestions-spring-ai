package com.smartai.etl.chunk;

import java.util.List;

import org.springframework.batch.item.support.ListItemReader;

public class SimpleItemReader extends ListItemReader<String> {
    public SimpleItemReader() {
        super(List.of("A", "B", "C")); // mock; depois substitua por CSV/DB/etc.
    }
}
