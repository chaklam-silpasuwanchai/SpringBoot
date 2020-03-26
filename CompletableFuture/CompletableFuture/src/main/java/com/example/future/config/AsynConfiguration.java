package com.example.future.config;

/* Credits: github.com/swathisprasad/spring-boot-completable-future */


import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsynConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsynConfiguration.class);

	@Bean(name = "taskExecutor")

	public Executor taskExecutor() {
		LOGGER.debug("Creating Async Task Executor");
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("CarThread-");
		executor.initialize();
		return executor;
	}
}
