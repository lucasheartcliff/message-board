package com.messageboard.persistence;

import com.google.common.collect.ImmutableMap;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;

import java.util.Set;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
public class PersistenceServiceImpl implements PersistenceService {
  private final String JDBC_URL = "jdbc:mysql://localhost:3306/mbdb?autoReconnect=true&characterEncoding=utf-8&useTimezone=true&serverTimezone=UTC&createDatabaseIfNotExist=true";
  private final String USERNAME = "mb";
  private final String PASSWORD = "";

  @Override
  @Bean
  public EntityManagerFactory buildEntityManagerFactory() {
    return new HibernatePersistenceProvider()
        .createContainerEntityManagerFactory(
            buildPersistenceUnitInfo(),
            ImmutableMap.<String, Object>builder()
                .put(JPA_JDBC_DRIVER, "org.mariadb.jdbc.Driver")
                .put(JPA_JDBC_URL, JDBC_URL)
                .put(USER, USERNAME)
                .put(PASS, PASSWORD)
                .put(DIALECT, MySQL5Dialect.class)
                .put(HBM2DDL_AUTO, "update")
                .put(SHOW_SQL, true)
                .put(QUERY_STARTUP_CHECKING, false)
                .put(GENERATE_STATISTICS, false)
                .put(USE_REFLECTION_OPTIMIZER, false)
                .put(USE_SECOND_LEVEL_CACHE, false)
                .put(USE_QUERY_CACHE, false)
                .put(USE_STRUCTURED_CACHE, false)
                .put(STATEMENT_BATCH_SIZE, 20)
                .build());
  }

  private PersistenceUnitInfo buildPersistenceUnitInfo() {
    return new PersistenceUnitInfoImpl();
  }
}
