package com.smartai.configuration;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pgvector.PGvector;

@Component
public class PGvectorInitializer {

    private final DataSource dataSource;

    public PGvectorInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void registerPgvectorType() {
        try (Connection conn = dataSource.getConnection()) {
            PGvector.registerTypes(conn);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao registrar PGvector no JDBC", e);
        }
    }
}