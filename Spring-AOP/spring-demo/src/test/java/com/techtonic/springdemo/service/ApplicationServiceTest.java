package com.techtonic.springdemo.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationServiceTest {

	@Autowired
	private ApplicationService appServ;
	
	@DisplayName("Running Test for PerformanceAspect")
	@Test
	public void fetchItems() {
		
		appServ.fetchItems();
		
	}
}
