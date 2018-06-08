package com.sgmw.sales.app.sample.config;

import java.util.Arrays;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConditionalOnProperty(prefix = "spring.datasource", name = "url", havingValue = "")
public class DatabaseConfiguration {

	private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

	@Inject
	private Environment env;

	@Bean(destroyMethod = "close")
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public DataSource dataSource(DataSourceProperties dataSourceProperties) {
		log.debug("Configuring Datasource");
		if (dataSourceProperties.getUrl() == null) {
			log.error(
					"Your database connection pool configuration is incorrect! The application"
							+ " cannot start. Please check your Spring profile, current profiles are: {}",
					Arrays.toString(env.getActiveProfiles()));

			throw new ApplicationContextException("Database connection pool is not configured correctly");
		}
		HikariDataSource hikariDataSource = (HikariDataSource) DataSourceBuilder
				.create(dataSourceProperties.getClassLoader()).type(HikariDataSource.class)
				.driverClassName(dataSourceProperties.getDriverClassName()).url(dataSourceProperties.getUrl())
				.username(dataSourceProperties.getUsername()).password(dataSourceProperties.getPassword()).build();
		hikariDataSource.setMaximumPoolSize(50);
		hikariDataSource.setMinimumIdle(10);

		return hikariDataSource;
	}
}
