package com.techtonic.springdemo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtonic.springdemo.annotation.TimeBoxAnnotation;
import com.techtonic.springdemo.dao.ApplicationDao;

@Service
public class ApplicationService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationDao appDao;

	/**
	 * Service business logic to collate the items present in Repo
	 * 
	 * @author knish
	 */
	@TimeBoxAnnotation
	public void fetchItems() {

		try {

			List<String> items = appDao.fetchItems();

			items.forEach(item -> logger.info("Printing items :: {}", item));

		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			logger.error("ApplicationService :: fetchItems :: Exception :: ", e);
		}

	}
}
