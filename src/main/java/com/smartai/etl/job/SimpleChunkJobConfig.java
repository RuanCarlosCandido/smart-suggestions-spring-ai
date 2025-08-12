package com.smartai.etl.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.smartai.etl.chunk.SimpleItemProcessor;
import com.smartai.etl.chunk.SimpleItemReader;
import com.smartai.etl.chunk.SimpleItemWriter;

@Configuration
public class SimpleChunkJobConfig {

    @Bean
    public Job simpleChunkJob(JobRepository jobRepository, Step simpleChunkStep) {
        return new JobBuilder("simpleChunkJob", jobRepository)
                .start(simpleChunkStep)
                .build();
    }

    @Bean
    public Step simpleChunkStep(JobRepository jobRepository,
                                PlatformTransactionManager transactionManager) {
        return new StepBuilder("simpleChunkStep", jobRepository)
                .<String, String>chunk(3, transactionManager)
                .reader(new SimpleItemReader())
                .processor(new SimpleItemProcessor())
                .writer(new SimpleItemWriter())
                .build();
    }
}
