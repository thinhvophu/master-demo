package com.thinhvp.masterdemo.configuration;

import com.thinhvp.masterdemo.service.async.impl.AsyncTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class BeansConfiguration {

	@Bean
	ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(25);
		executor.setMaxPoolSize(25);
		executor.setQueueCapacity(10000);
		executor.setThreadNamePrefix("progress_tracking_pool");
		executor.initialize();
		return executor;
	}

	@Bean
	AsyncTaskManager asyncTaskManager() {
		ThreadPoolTaskExecutor taskExecutor = taskExecutor();
		AsyncTaskManager asyncTaskManager = new AsyncTaskManager();
		asyncTaskManager.setTaskExecutor(taskExecutor);
		return asyncTaskManager;
	}
}
