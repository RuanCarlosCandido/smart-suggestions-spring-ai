package com.smartai.etl.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class HelloBatchJobConfig {

    @Bean
    public Job helloJob(JobRepository jobRepository, Step helloStep) {
        return new JobBuilder("helloJob", jobRepository)
                .start(helloStep)
                .build();
    }

    @Bean
    public Step helloStep(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager,
                          Tasklet helloTasklet) {
        return new StepBuilder("helloStep", jobRepository)
                .tasklet(helloTasklet, transactionManager)
                .build();
    }

    @Bean
    public Tasklet helloTasklet() {
        return (contribution, chunkContext) -> {
            // ponto de entrada para sua lógica de ETL inicial
            System.out.println("Hello, Batch! Executando configuração mínima…");
            return RepeatStatus.FINISHED;
        };
    }
}
