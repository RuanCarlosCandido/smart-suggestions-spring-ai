package com.smartai.etl.chunk;

import org.springframework.batch.item.ItemProcessor;

public class SimpleItemProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) {
        // transformação mock
        return item.toLowerCase();
    }
}
