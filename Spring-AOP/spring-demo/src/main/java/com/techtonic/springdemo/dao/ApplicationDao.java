package com.techtonic.springdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ApplicationDao {

	public List<String> fetchItems(){
		
		List<String> resList = new ArrayList<String>();
		
		resList.add("Item_1");
		resList.add("Item_2");
		resList.add("Item_3");
		resList.add("Item_4");
		resList.add("Item_5");
		
		return resList;
	}
}
