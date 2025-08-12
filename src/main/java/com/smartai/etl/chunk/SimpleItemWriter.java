package com.smartai.etl.chunk;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class SimpleItemWriter implements ItemWriter<String> {
    @Override
    public void write(Chunk<? extends String> chunk) {
        // saída mock
        List<? extends String> items = chunk.getItems();
        System.out.println("Gravando: " + items);
    }
}
