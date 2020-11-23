package com.techtonic.springdemo;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ResourceUtils;

import com.techtonic.springdemo.dto.Row;
import com.techtonic.springdemo.service.CSVSearchService;
import com.techtonic.springdemo.utility.CSVParserUtility;

@SpringBootApplication
@EnableAsync
public class SpringDemoApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(SpringDemoApplication.class);

	@Autowired
	private CSVSearchService service;

	// Configurable Multi-thread task executor
	@Bean
	public TaskExecutor taskExecuorFactory() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(50);
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setThreadNamePrefix("Async-");
		return executor;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Row> rowsFromCsv = CSVParserUtility
				.parseCSVToRow(ResourceUtils.getFile("classpath:president_county_candidate.csv"));

		// Initiating 4 branches of asynchronous lookup calls
		CompletableFuture<List<String>> branch_1 = service.getCountiesPerCandidate("Joe Biden", rowsFromCsv);

		CompletableFuture<List<String>> branch_2 = service.getCountiesPerCandidate("Donald Trump", rowsFromCsv);
		
		CompletableFuture<List<String>> branch_3 = service.getCountiesPerCandidate("Jo Jorgensen", rowsFromCsv);
		
		CompletableFuture<List<String>> branch_4 = service.getCountiesPerCandidate("Howie Hawkins", rowsFromCsv); 
		// Join the results fetched by both branches
		CompletableFuture.allOf(branch_1, branch_2, branch_3, branch_4).join();

		// Printing results
		branch_1.get().forEach(county -> {
			if (null != county) {
				LOG.info("County won by Biden {} :: ", county);
			}
		});

		branch_2.get().forEach(county -> {
			if (null != county) {
				LOG.info("County won by Trump {} :: ", county);
			}
		});
		
		branch_3.get().forEach(county -> {
			if (null != county) {
				LOG.info("County won by Jorgensen {} :: ", county);
			}
		});
		
		branch_4.get().forEach(county -> {
			if (null != county) {
				LOG.info("County won by Hawkins {} :: ", county);
			}
		});
	}

}
