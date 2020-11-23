package com.techtonic.springdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.techtonic.springdemo.annotation.TimeBoxAnnotation;
import com.techtonic.springdemo.dto.Row;


@Service
public class CSVSearchService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@TimeBoxAnnotation
	@Async("taskExecuorFactory")
	public CompletableFuture<List<String>> getCountiesPerCandidate(String candidate, List<Row> fullList){
		
		log.info("Candidate name {} ",candidate);
		
		List<String> counties = new ArrayList<>();
		
		for(Row row : fullList) {
			
			if(candidate.equalsIgnoreCase(row.getCandidate())) {
				log.info("Candidate match for county {} ",row.getCounty());
				counties.add(row.getCounty());
			}
		}
		
		return CompletableFuture.completedFuture(counties);
	}
}
