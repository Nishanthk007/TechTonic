package com.techtonic.springdemo.dto;

import com.opencsv.bean.CsvBindByName;

public class Row {

	@CsvBindByName
	private String state;
	
	@CsvBindByName
	private String county;
	
	@CsvBindByName
	private String candidate;
	
	@CsvBindByName
	private String party;
	
	@CsvBindByName(column="total_votes")
	private int totalVotes;
	
	@CsvBindByName
	private boolean won;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	@Override
	public String toString() {
		return "Row [state=" + state + ", county=" + county + ", candidate=" + candidate + ", party=" + party
				+ ", totalVotes=" + totalVotes + ", won=" + won + "]";
	}
	
	
}
