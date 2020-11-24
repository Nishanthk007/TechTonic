package com.techtonic.springdemo.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.techtonic.springdemo.dto.Row;

public class CSVParserUtility {

	public static final Logger LOG = LoggerFactory.getLogger(CSVParserUtility.class);

	/**
	 * Maps rows in a csv with the fields marked in the Row Class
	 * @param file
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static final List<Row> parseCSVToRow(File file) {

		if (null!=file) {

			List<Row> rows = null;

			try (CSVReader reader = new CSVReader(new FileReader(file))) {

				@SuppressWarnings("unchecked")
				CsvToBean<Row> csvToBean = new CsvToBeanBuilder(reader)
												.withType(Row.class)
												.withIgnoreLeadingWhiteSpace(true).build();
				
				rows = csvToBean.parse();
				
				return rows;
				
			} catch (FileNotFoundException e) {
				LOG.error("FileNotFound Exception caught :: parseCSVToRow :: ", e);
				
			} catch (IOException e) {
				LOG.error("IOException Exception caught :: parseCSVToRow :: ", e);
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
